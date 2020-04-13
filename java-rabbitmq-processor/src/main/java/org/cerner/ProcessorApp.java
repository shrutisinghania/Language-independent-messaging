package org.cerner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Processor.class)
@SpringBootApplication
public class ProcessorApp {

    private static Logger logger = LoggerFactory.getLogger(ProcessorApp.class);

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object transform(String name) {
		String greetName = "Hello, " + name + "!";

		logger.info("Input: " + name);
		logger.info("Output: " + greetName);

		return greetName;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApp.class, args);
	}
}