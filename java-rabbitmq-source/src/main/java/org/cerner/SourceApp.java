package org.cerner;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static final String[] NAMES = { "Shruti", "Lokkhi", "Ishani", "Rohit" };

	@Autowired
	private Random random;

	@Bean
	public Random getRandom() {
		return new Random();
	}

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<String> timeMessageSource() {
		String name = NAMES[random.nextInt(4)];
		System.out.println("Sending: " + name);
		return () -> MessageBuilder.withPayload(name).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SourceApp.class, args);
	}
}
