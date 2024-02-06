package in.nk.tech.ratelimit.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

	@GetMapping("/get-item")
	public String getItem() {
		log.info("Item call started...");
		return "Item - Realme 5 Pro";
	}
}
