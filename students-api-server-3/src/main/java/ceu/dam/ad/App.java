package ceu.dam.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ceu.dam.ad.repository.StudentRepository;
import ceu.dam.ad.test.Test;

@SpringBootApplication
public class App {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		Test test = context.getBean(Test.class);
		test.test();
	}

}
