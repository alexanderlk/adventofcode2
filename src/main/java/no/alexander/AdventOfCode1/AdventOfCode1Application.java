package no.alexander.AdventOfCode1;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventOfCode1Application implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(AdventOfCode1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(AdventOfCode1Application.class, args);
	}

	@Override
	public void run(String... args) throws URISyntaxException, IOException {
		LOG.info("Hello world!");
		
		URL input = getClass().getClassLoader().getResource("input.txt");
		File file = new File(input.toURI());
		
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
		partOne(lines);
		partTwo(lines);
	}
	
	private void partOne(List<String> lines) {
		int valid = 0;
		
		for (String line : lines) {
			Line l = new Line(line);
			
			int count = countLetter(l.character, l.password);
			if (count >= l.lower && count <= l.upper) {
				valid++;
			}
		}
		LOG.info("Valid=" + valid);
	}
	
	private int countLetter(char letter, String s) {
		var count = 0;
		for (var i = 0; i < s.length(); i++) {
			if (s.charAt(i) == letter) {
				count++;
			}
		}
		return count;
	}
	
	
	private void partTwo(List<String> lines) {
		var valid = 0;
		
		
		for (String line : lines) {
			Line l = new Line(line);
	
			if ( l.password.charAt(l.lower) == l.character ^ l.password.charAt(l.upper) == l.character) {
				valid++;
			}
		}
		LOG.info("Valid=" + valid);
	}
	
	private class Line {
		private int lower;
		private int upper;
		private char character;
		private String password;
		
		public Line(String input) {
			String[] parts = input.split(":");
			var rules = parts[0];
			password = parts[1];
			
			String[] ruleParts = rules.split(" ");
			var boundaries = ruleParts[0];
			character = ruleParts[1].charAt(0);
						
			String[] boundaryParts = boundaries.split("-");
			lower = Integer.valueOf(boundaryParts[0]);
			upper = Integer.valueOf(boundaryParts[1]);
		}
	}
}
