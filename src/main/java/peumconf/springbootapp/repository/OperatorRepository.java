package peumconf.springbootapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import peumconf.springbootapp.model.Operator;

@Repository("operatorRepository")
public interface OperatorRepository extends CrudRepository<Operator, Long> {

}
