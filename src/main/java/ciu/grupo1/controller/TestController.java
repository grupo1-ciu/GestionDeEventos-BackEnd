package ciu.grupo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

	@GetMapping
	public String test() {
		return "Active";
	}

	@PostMapping("ping")
	public String ping() {
		return "pong";
	}
}
