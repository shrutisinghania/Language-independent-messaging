package org.cerner;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(Source.class)
@SpringBootApplication
public class SourceApp {
	private static final String[] NAMES = {"Shruti", "Lokkhi", "Ishani", "Rohit"};
	private Random random = new Random();
	
	@Bean
	public Random getRandom() {
		return random;
	}
	
	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<String> timeMessageSource() {
		return () -> MessageBuilder.withPayload(NAMES[getRandom().nextInt(4)]).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SourceApp.class, args);
	}
}
