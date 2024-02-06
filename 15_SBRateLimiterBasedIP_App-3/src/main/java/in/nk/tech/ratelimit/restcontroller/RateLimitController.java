package in.nk.tech.ratelimit.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rate-limit")
public class RateLimitController {

	@GetMapping
	public ResponseEntity<String> requestOne() {
		return ResponseEntity.ok("====Request Allowed to IP 1=====");
	}

	@GetMapping("/request")
	public ResponseEntity<String> requestTwo() {
		return ResponseEntity.ok("====Request Allowed to IP 2=====");
	}
}
