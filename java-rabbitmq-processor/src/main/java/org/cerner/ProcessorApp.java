package org.cerner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Processor.class)
@SpringBootApplication
public class ProcessorApp {

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object transform(String name) {
		String greetName = "Hello, " + name + "!";

		System.out.println("Input: " + name);
		System.out.println("Output: " + greetName);

		return greetName;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApp.class, args);
	}
}