package in.nk.tech.ratelimit.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RateLimitConfig implements Filter {

	private static final int MAX_REQUEST = 5;

	private LoadingCache<String, Integer> requestCountPerIpAddress;

	private Set<String> urls;

	public RateLimitConfig() {
		super();
		requestCountPerIpAddress = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String s) throws Exception {
						return null;
					}
				});

		urls = new HashSet<>(Arrays.asList("/api/rate-limit", "/api/rate-limit/request"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String requestURI = httpServletRequest.getRequestURI();
		log.info("requestURI - {}", requestURI);

//		String requestPath = requestURI.substring(httpServletRequest.getContextPath().length());
//		log.info("requestPath - {}", requestPath);

		if (urls.contains(requestURI)) {
			String clienHashId = (StringUtils.isEmpty(httpServletRequest.getHeader("client-hash-id")))
					? requestURI + "/" + getClientIP(httpServletRequest)
					: requestURI + "/" + httpServletRequest.getHeader("client-hash-id");
			log.info("clienHashId - {}", clienHashId);
			if (isMaxRequestsForSecond(clienHashId)) {
				httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
				httpServletResponse.getWriter().print(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
						.body("RATE LIMIT EXCEEDED - TOO MANY REQUESTS").getBody());
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isMaxRequestsForSecond(String clienHashId) {
		int requsetCounts = 0;
		try {
			requsetCounts = requestCountPerIpAddress.get(clienHashId);

			if (requsetCounts > MAX_REQUEST) {
				log.error("REQUESTS EXCEEDED FOR IP {} ON {}", clienHashId, new Date());
				requestCountPerIpAddress.put(clienHashId, requsetCounts);
				return true;
			}
		} catch (Exception e) {
			requsetCounts = 0;
		}
		requsetCounts++;
		requestCountPerIpAddress.put(clienHashId, requsetCounts);
		return false;
	}

	private String getClientIP(HttpServletRequest httpServletRequest) {
		String ip = httpServletRequest.getHeader("X-Forwarded-For");

		if (ip == null) {
			ip = httpServletRequest.getRemoteAddr();
			return ip;
		}
		return ip.split(",")[0];
	}
}
