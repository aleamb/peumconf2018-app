package peumconf.springbootapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitorController {

	@GetMapping("/")
	public String monitor() {
		return "monitor.html";
	}

}
