package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log =
			LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			repository.saveAll(List.of(
					new Customer(null, "Jack", "Bauer"),
					new Customer(null, "Chloe", "O'Brian"),
					new Customer(null, "Kim", "Bauer"),
					new Customer(null, "David", "Palmer"),
					new Customer(null, "Michelle", "Dessler")
			));

			log.info("Customers found with findAll():");
			log.info("--------------------------------");
			for(Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			Customer customer = repository.findById(1L).get();
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------");
			for(Customer bauer : repository
					.findByLastNameStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}
}
