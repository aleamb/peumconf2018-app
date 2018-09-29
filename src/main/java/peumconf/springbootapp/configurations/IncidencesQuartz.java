package peumconf.springbootapp.configurations;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IncidencesQuartz {

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob().ofType(GenerateIncidence.class).storeDurably().withIdentity("Poll Sync Job")
				.withDescription("Invoke synchronization job").build();
	}

	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(20).repeatForever();

		return TriggerBuilder.newTrigger().forJob(jobDetail()).withIdentity("sgvpPollSync")
				.withSchedule(scheduleBuilder).build();
	}

}
