package ceu.dam.ad.users.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.users.dao.UserDao;
import ceu.dam.ad.users.model.User;

public class UserServiceImpl extends Service implements UserService {

	UserDao uDao = new UserDao();
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) throws DuplicateUserException, UserException {

		try (Connection conn = abrirConexionUsuarios()) {
			/** 1. Verificar que no existe usuario con ese email ni ese username. En caso
			 * contrario, lanzar DuplicateUserException  */
			if (uDao.getByEmail(conn, user.getEmail()) == null
					&& uDao.getByUserName(conn, user.getUsername()) == null) {
			/*2. Registrar el usuario en BBDD
			 * completando su fecha de alta y cifrando su password con SHA3-256*/
				String pass = DigestUtils.sha3_256Hex(user.getPassword());
				user.setPassword(pass);
				user.setCreatedDate(LocalDate.now());
				user.setLastLoginDate(null);
				try {
					Long id = uDao.insert(conn, user);
					System.out.println("Usuario creado correctamente");
			/*3. Devolver el usuario con todos sus datos (incluyendo el ID) */
					user.setId(id);
					return user;
				} catch (SQLException e) {
				/*4. Si hay algún error,
			 * lanzará UserException con el origen */
					throw new UserException("Error al guardar la contraseña cifrada");
				}
			}
		
			throw new DuplicateUserException("Este usuario ya existe");

		} catch (SQLException e) {
			
			// Aquí poner el log sobre los errores
			logger.error("Error al crear nuevo usuario", e);
			throw new UserException("Error al crear nuevo usuario");		}
		
	}
	/** Recibe el id de un usuario, el password antiguo y el nuevo. Los dos sin
	 * cifrar. El servicio tendrá que: 1. Si el usuario no existe con ese ID, lanzar
	 * UserNotFoundException 2. Verificar que la nueva password no es igual a la
	 * antigua. Si lo es, lanzar UserUnauthorizedException 3. Verificar que la
	 * password antigua es correcta. Si no lo es, lanzar UserUnauthorizedException
	 * 4. Actualizar el nuevo password en el usuario cifrándolo previamente. 5. Si
	 * hay algún error, lanzará UserException con el origen
	 */
	@Override
	public void changePassword(Long idUser, String oldPassword, String newPassword)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		try (Connection conn = abrirConexionUsuarios()) {
			// 1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
			User usuario = uDao.getById(conn, idUser);
			if (usuario == null) {
				throw new UserNotFoundException("El usuario introducido no existe en la BBDD");
			}
			/*2. Verificar que la nueva password no es igual a la antigua. Si lo es, lanzar UserUnauthorizedException*/
			if (oldPassword.equals(DigestUtils.sha3_256Hex(newPassword))) {
				throw new UserUnauthorizedException("La contraseña nueva es igual a la anterior");
			}
			/* 3. Verificar que la password antigua es correcta. Si no lo es, lanzar UserUnauthorizedException*/
			if (!oldPassword.equals(usuario.getPassword())) {
				throw new UserUnauthorizedException("La contraseña facilitada no coincide con la guardada");
			}
			/*4. Actualizar el nuevo password en el usuario cifrándolo previamente. 5. Si hay algún error, lanzará UserException con el origen*/
			try {
				usuario.setPassword(DigestUtils.sha3_256Hex(newPassword));
				uDao.update(conn, usuario);
				System.out.println("Contraseña cambiada correctamente");
				
				
			} catch (SQLException e) {
				throw new UserException("Error al cifrar e introducir la contraseña en la BBDD");
			}
		} catch (SQLException e) {
			throw new UserException("Error al cifrar e introducir la contraseña en la BBDD\"");
		}

	}

	@Override
	public User login(String login, String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {

		try (Connection conn = abrirConexionUsuarios()) {
			
			/* 1. Verificar que existe algún usuario con ese username o email. Si no es así,
			 * lanzar UserNotFoundException */
			User usuario = uDao.getByEmail(conn, login);
			/* 2. Verificar que password es correcta. Si no lo es, lanzar
			 * UserUnauthorizedException*/
			if (usuario== null) {
				usuario = uDao.getByUserName(conn, login);
			} if(usuario== null) {
				throw new UserNotFoundException("El usuario introducido no existe");
			}
			if (!usuario.getPassword().equals(DigestUtils.sha3_256Hex(password))) {
				throw new UserUnauthorizedException("Contraseña introducida incorrecta");
			} else {
				/* 3. Actualizamos fecha del último login. Si hay algún error aquí, registramos
				 * en el log, pero continuamos. */
				LocalDate hoy = LocalDate.now();
				usuario.setLastLoginDate(hoy);
				
				// registrar posible error en log
				try {
					uDao.update(conn, usuario);
					System.out.println("Usuario logueado correctamente");
					
				} catch (SQLException e) {
					logger.error("Error al actualizar fecha de login");
				}
				/* 4. Devolver el usuario con todos sus datos que ha realizado el login. */
				return usuario;
			}
			//5. Si hay algún error, lanzará UserException con el origen
		} catch (SQLException e) {
			throw new UserException("Ha ocurrido un problema durante el login");
		}

	}
	/** Recibe el id de un usuario. El servicio tendrá que:
	 * 1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
	 * 2. Devolver los datos completos del usuario
	 * 3. Si hay algún error, lanzará UserException con el origen
	 */
	@Override
	public User getUser(Long idUser) throws UserNotFoundException, UserException {
		try (Connection conn = abrirConexionUsuarios()) {
			User usuario = uDao.getById(conn, idUser);
			if(usuario ==null) {
				throw new UserNotFoundException("Usuario no encontrado con id introducido");
			}
			return usuario;
			
		} catch (SQLException e) {
			throw new UserException("Error al traer usuario");
		}
		
	}

}

}

