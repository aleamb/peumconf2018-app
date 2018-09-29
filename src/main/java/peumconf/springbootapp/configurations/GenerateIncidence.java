package peumconf.springbootapp.configurations;

import java.util.Random;

import org.apache.commons.text.RandomStringGenerator;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import peumconf.springbootapp.model.Incidence;
import peumconf.springbootapp.model.Operator;

public class GenerateIncidence implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateIncidence.class);

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		Incidence incidence = new Incidence();

		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		String randomLetters = generator.generate(20);

		incidence.setTitle(randomLetters);
		incidence.setOperator(new Operator(generator.generate(7)));

		incidence.setType(Incidence.types[new Random().nextInt(10) % Incidence.types.length]);

		webSocket.convertAndSend("/topic/incidence", incidence);

		LOGGER.info("Incidencia enviada");

	}

}
