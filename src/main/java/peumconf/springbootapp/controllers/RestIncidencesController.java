package peumconf.springbootapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import peumconf.springbootapp.model.Incidence;
import peumconf.springbootapp.repository.IncidenceRepository;

@RestController
public class RestIncidencesController {

	@Autowired
	private IncidenceRepository incidenceRepository;

	@GetMapping("/incidences2")
	public Iterable<Incidence> getAll() {

		return incidenceRepository.findAll();

	}
}
