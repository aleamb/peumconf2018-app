package peumconf.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import peumconf.springbootapp.model.Incidence;

@Repository("incidenceRepository")
public interface IncidenceRepository extends JpaRepository<Incidence, String> {

}
