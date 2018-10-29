package peumconf.springbootapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import peumconf.springbootapp.model.Incidence;
import peumconf.springbootapp.repository.IncidenceRepository;

@Service
public class IncidencesServiceImpl implements IncidencesService {

	@Autowired
	private IncidenceRepository incidenceRepository;

	@Override
	public void resolveIncidence(String id) {

		Optional<Incidence> incidence = incidenceRepository.findById(id);

		Incidence incidenceInstance = incidence.get();
		incidenceInstance.setResolved(true);
		incidenceRepository.save(incidenceInstance);

	}

	@Override
	public Iterable<Incidence> retrieveIncidencesOrderedByDate() {

		return incidenceRepository.findAll(new Sort(Sort.Direction.DESC, "start"));
	}

}
