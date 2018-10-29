package peumconf.springbootapp.services;


import java.util.Random;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peumconf.springbootapp.model.Operator;
import peumconf.springbootapp.repository.OperatorRepository;

@Service
public class OperatorsServiceImpl implements OperatorsService {

	@Autowired
	private OperatorRepository operatorRepository;

	private Random r = new Random(System.currentTimeMillis());

	@Override
	public Operator getRandomOperator() {

		Object[] operators = StreamSupport.stream(operatorRepository.findAll().spliterator(), true).toArray();
		return (Operator) operators[r.nextInt(operators.length)];
	}
}
