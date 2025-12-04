package com.fincompmock;

import com.fincompmock.expectation.FinCompEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinCompMockApplication {

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			System.err.println("Usage: java -jar <jar> <mockserver-host>:<port>");
			System.exit(1);
		}

		String hostAndPort = args[0];
		System.out.println("Starting MockServer client for -> " + hostAndPort);

		// Load default expectations
		FinCompEntry finCompEntry = new FinCompEntry((hostAndPort));
		finCompEntry.registerAll();

		SpringApplication.run(FinCompMockApplication.class, args);
	}
}
