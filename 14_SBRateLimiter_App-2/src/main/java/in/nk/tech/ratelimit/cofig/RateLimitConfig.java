package in.nk.tech.ratelimit.cofig;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bucket;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rate-limit")
@Slf4j
public class RateLimitConfig implements CommandLineRunner {

	private Bucket bucket;

	@GetMapping("/token-generate")
	public ResponseEntity<String> generateToken() {
		// refill
//		Refill refill = Refill.greedy(5, Duration.ofMinutes(1));

		// bucket
		// Bucket4j.builder() -> Deprecated use Bucket
//		bucket = Bucket4j.builder().addLimit(Bandwidth.classic(5, refill)).build(); // 5TOKENS AT A 1 MINUTE

//		bucket = Bucket.builder().addLimit(Bandwidth.classic(5, refill)).build();
//		bucket = Bucket.builder().addLimit(Bandwidth.classic(5, refill)).withNanosecondPrecision().withSynchronizationStrategy(SynchronizationStrategy.SYNCHRONIZED).build();

		// Refil Styles
		// 1. Greedy - will not wait to generate whole bunch of tokens,
//		bucket = Bucket.builder().addLimit((limit -> limit.capacity(5).refillGreedy(10, Duration.ofMinutes(1)).initialTokens(3))).build();

		// 2. Intervally - will wait for entire time elapsed before regenerating the
		// token
//		bucket = Bucket.builder().addLimit((limit -> limit.capacity(5).refillIntervally(10, Duration.ofMinutes(1)))).build();

		// 3. IntervllyAligned - determines when first refill will be generated (based
		// on seconds, minutes, hours, day ...)
		Instant firstRefilTime = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES).plus(2, ChronoUnit.MINUTES)
				.toInstant();
		bucket = Bucket.builder()
				.addLimit(
						(limit -> limit.capacity(5).refillIntervallyAligned(10, Duration.ofMinutes(1), firstRefilTime)))
				.build();

//		bucket = Bucket.builder().addLimit((limit -> limit.capacity(10).refillGreedy(5, Duration.ofMinutes(1))))
//				.withNanosecondPrecision().withSynchronizationStrategy(SynchronizationStrategy.SYNCHRONIZED).build();

		log.info("Generated Successfuly!! token - {} ", bucket.toString(), HttpStatus.OK);
		return new ResponseEntity<String>("Generated Successfuly!! " + bucket.toString(), HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<String> get() {
		// consumption
		if (bucket.tryConsume(1)) {
//			System.out.println("---------------API Working successfully----------");
			log.info("API ALLOWED TO ACCESS");
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
//		System.out.println("---------------Number Of hits exceeded----------");
		log.info("RATE LIMIT EXCEEDED. PLEASE TRY AGAIN LATER");
		return new ResponseEntity<String>("TOO MANY REQUESTS", HttpStatus.TOO_MANY_REQUESTS);

	}

	@Override
	public void run(String... args) throws Exception {
		generateToken();
	}
}
