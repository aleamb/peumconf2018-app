package peumconf.springbootapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import peumconf.springbootapp.model.Incidence;
import peumconf.springbootapp.services.IncidencesService;

@RestController
public class RestIncidencesController {

	@Autowired
	private IncidencesService incidencesService;

	@GetMapping("/incidences2")
	public Iterable<Incidence> getAll() {

		return incidencesService.retrieveIncidencesOrderedByDate();

	}

	@CrossOrigin
	@PutMapping(value = "/incidences2/{uuid}")
	public void resolveIncidence(@PathVariable String uuid) {

		incidencesService.resolveIncidence(uuid);

	}
}
