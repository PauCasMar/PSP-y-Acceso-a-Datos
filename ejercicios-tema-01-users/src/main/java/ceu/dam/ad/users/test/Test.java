package ceu.dam.ad.users.test;

import java.util.Scanner;
import ceu.dam.ad.users.model.User;
import ceu.dam.ad.users.services.UserServiceImpl;

public class Test {

	UserServiceImpl userVicio = new UserServiceImpl();

	public static void main(String[] args) {
		Test test = new Test();
		test.testService();
	}

	private void testService() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Bienvenido a la consola de BBDD de usuarios, ¿qué quieres hacer?");
		System.out.println(" 1- Crear usuario \n 2- Cambiar contraseña\n 3- Login \n 4- Consultar usuario");
		Integer num = sc.nextInt();
		try {

			switch (num) {

			case 1:
				//Crear usuario
				User usuario = new User();
				usuario.setUsername("Manolo");
				usuario.setEmail("email@mauel.com");
				usuario.setPassword("manumola");
				userVicio.createUser(usuario);
					
				break;
			case 2:
				//Cambiar contraseña
				User usuario2 = new User();
				Long id = (long) 2;
				usuario2 = userVicio.getUser(id);
				String oldPass = usuario2.getPassword();
				String newPass = "fadssas";
				userVicio.changePassword(id, oldPass, newPass);

				break;

			case 3:
				//Login y actualizar fecha ultimo login
				User user3 = new User();
				user3.setUsername("Laura");
				user3.setPassword("fadssas");
				userVicio.login(user3.getUsername(), user3.getPassword());
							
				break;

			case 4:
				//Info de usuario
				Long id2 = (long) 3;
				User user4= userVicio.getUser(id2);
				System.out.println(user4);				
				
				break;
			
			default:
				System.out.println("Opción incorrecta");
			}

		} catch (Exception e) {
			e.printStackTrace();
		};
		sc.close();
	}

}

