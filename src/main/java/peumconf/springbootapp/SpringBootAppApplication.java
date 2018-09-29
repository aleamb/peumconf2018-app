package peumconf.springbootapp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import peumconf.springbootapp.model.Incidence;
import peumconf.springbootapp.model.Operator;
import peumconf.springbootapp.repository.IncidenceRepository;
import peumconf.springbootapp.repository.OperatorRepository;

@SpringBootApplication
public class SpringBootAppApplication {

	private static final String QUERY_OPERATORS_CHECK = "SELECT * FROM OPERATORS";

	@Autowired
	private JdbcTemplate dbTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(OperatorRepository operatorRepository, IncidenceRepository incidenceRepository) {
		return (args) -> {
			if (CollectionUtils.isEmpty(dbTemplate.queryForList(QUERY_OPERATORS_CHECK))) {
				for (int op = 0; op < 20; op++)
					operatorRepository.save(new Operator("Operador" + op));
			}

			Operator op = operatorRepository.findById(1L).get();

			incidenceRepository.save(new Incidence("Incidencia 1", op, LocalDateTime.now(), Incidence.types[0]));
		};
	}
}
