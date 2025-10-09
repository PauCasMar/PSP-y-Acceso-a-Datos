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
			do {
				switch (num) {

				case 1:
					User usuario = new User();
					usuario.setUsername("Pepe");
					usuario.setEmail("email@pepe.com");
					usuario.setPassword("pepemola");

					userVicio.createUser(usuario);
					System.out.println("Usuario introducido correctamente");
					break;
				case 2:
					User usuario2 = new User();
					String oldPass = usuario2.getPassword();
					String newPass = "vayaPassRara2020";
					Long id = (long) 1;

					userVicio.changePassword(id, oldPass, newPass);
					break;

				case 3:
					break;

				case 4:
					break;
				case 5:
					break;
				default:
					System.out.println("Opción incorrecta");
				}

			} while (num < 5);
		} catch (Exception e) {

		};
		sc.close();
	}

}
