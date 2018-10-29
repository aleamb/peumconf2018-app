package peumconf.springbootapp.services;

import peumconf.springbootapp.model.Incidence;

public interface IncidencesService {

	void resolveIncidence(String id);
	
	Iterable<Incidence> retrieveIncidencesOrderedByDate();
}
