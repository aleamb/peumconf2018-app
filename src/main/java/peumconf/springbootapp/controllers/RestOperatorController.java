package peumconf.springbootapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import peumconf.springbootapp.model.Operator;
import peumconf.springbootapp.repository.OperatorRepository;


@RestController
public class RestOperatorController {

	@Autowired
	private OperatorRepository operatorRepository;

	@GetMapping("/operators2")
	public Iterable<Operator> getAll() {
		return operatorRepository.findAll();
	}
}
