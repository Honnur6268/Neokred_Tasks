package in.nk.tech.ratelimit.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

	private static final String FALL_BACK_MSG = "To Many Requests for order";
	private static final String FALL_BACK_SERVICE_NAME = "order-service";
	@Autowired
	public RestTemplate restTemplate;

	@GetMapping("/get-order")
	@RateLimiter(name = FALL_BACK_SERVICE_NAME, fallbackMethod = "rateLimiterFallback")
	public ResponseEntity<String> createOrder() {
		String response = restTemplate.getForObject("http://localhost:8080/item/get-item", String.class);
		log.info("Call processing finished - {}", Thread.currentThread().getName());
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	public ResponseEntity<String> rateLimiterFallback(Exception e) {
		return new ResponseEntity<String>(FALL_BACK_MSG.toUpperCase(), HttpStatus.TOO_MANY_REQUESTS);

	}
}
