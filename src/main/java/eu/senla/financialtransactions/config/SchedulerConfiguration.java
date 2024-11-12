package eu.senla.financialtransactions.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static eu.senla.financialtransactions.constant.AppConstants.SCHEDULER_ENABLED;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = SCHEDULER_ENABLED, matchIfMissing = true)
public class SchedulerConfiguration {
}
