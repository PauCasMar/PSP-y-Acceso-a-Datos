package ceu.dam.ad.tema3.ejercicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.test.TestEj1;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.test.TestEj2;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.test.TestEj3;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.test.TestEj4;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.test.TestEj5;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		TestEj1 test1 = context.getBean(TestEj1.class);
		//test1.test();
				
		TestEj2 test2 = context.getBean(TestEj2.class);
	//	test2.test();
		
		TestEj3 test3 = context.getBean(TestEj3.class);
	//	test3.test();
		
		TestEj4 test4 = context.getBean(TestEj4.class);
	//	test4.test();
		
		TestEj5 test5 = context.getBean(TestEj5.class);
		test5.test();
		
	}

}
