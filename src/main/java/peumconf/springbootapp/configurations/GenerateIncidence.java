package peumconf.springbootapp.configurations;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import peumconf.springbootapp.model.Incidence;
import peumconf.springbootapp.repository.IncidenceRepository;
import peumconf.springbootapp.services.OperatorsService;

public class GenerateIncidence implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateIncidence.class);

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Autowired
	private OperatorsService operatorsService;

	@Autowired
	private IncidenceRepository indicenceRepository;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		Incidence incidence = new Incidence();

		UUID id = UUID.randomUUID();

		incidence.setId(id.toString());

		// obetener los últimos 2 bytes para generar un nombre corto
		incidence.setTitle("Incidencia " + Long.toHexString(id.getLeastSignificantBits() & 0xffffL));

		incidence.setOperator(operatorsService.getRandomOperator());
		incidence.setType(Incidence.types[new Random().nextInt(10) % Incidence.types.length]);
		incidence.setStart(LocalDateTime.now());
		indicenceRepository.save(incidence);
		webSocket.convertAndSend("/topic/incidence", incidence);

		LOGGER.info("Incidencia enviada");

	}

}
