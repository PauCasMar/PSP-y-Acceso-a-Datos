package ceu.dam.ad.ejerciciosTema2.avanzado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.service.SeriesService;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.test.Test1;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.test.Test2;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
				
		Test1 test1 = context.getBean(Test1.class);
		//test1.test();
		
		Test2 test2 = context.getBean(Test2.class);
		test2.test();
	}

}
