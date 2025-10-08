package ceu.dam.ad.users.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import ceu.dam.ad.users.dao.UserDao;
import ceu.dam.ad.users.model.User;

public class UserServiceImpl extends Service implements UserService {

	UserDao uDao = new UserDao();

	@Override
	public User createUser(User user) throws DuplicateUserException, UserException {

		try (Connection conn = abrirConexionUsuarios()) {
			/*
			 * 1. Verificar que no existe usuario con ese email ni ese username. En caso
			 * contrario, lanzar DuplicateUserException 2. Registrar el usuario en BBDD
			 * completando su fecha de alta y cifrando su password con SHA3-256 3. Devolver
			 * el usuario con todos sus datos (incluyendo el ID) 4. Si hay algún error,
			 * lanzará UserException con el origen
			 */

			if (uDao.getByEmail(conn, user.getEmail()) == null
					&& uDao.getByUserName(conn, user.getUsername()) == null) {

				/*Aumentamos el tamaño a la columna para que quepa la password cifrada La
				 * ciframos y actualizamos en el usuario antes de añadirlo a la BBDD
				 */
				String pass = DigestUtils.sha3_256Hex(user.getPassword());
				user.setPassword(pass);
				try {
					Long id = uDao.insert(conn, user);
					user.setId(id);
					return user;
				} catch (SQLException e) {
					throw new UserException(e);
				}
			}
			throw new DuplicateUserException();

		} catch (SQLException e) {
			// Aquí poner el log sobre los errores
			e.printStackTrace();
		}
		return user;

	}
	/** Recibe el id de un usuario, el password antiguo y el nuevo. Los dos sin cifrar. El servicio tendrá que:
	 * 1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
	 * 2. Verificar que la nueva password no es igual a la antigua. Si lo es, lanzar UserUnauthorizedException
	 * 3. Verificar que la password antigua es correcta. Si no lo es, lanzar UserUnauthorizedException
	 * 4. Actualizar el nuevo password en el usuario cifrándolo previamente.
	 * 5. Si hay algún error, lanzará UserException con el origen
	 */
	@Override
	public void changePassword(Long idUser, String oldPassword, String newPassword)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		
		
		
	}

	@Override
	public User login(String login, String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		return null;

	}

	@Override
	public User getUser(Long idUser) throws UserNotFoundException, UserException {
		return null;
	}

}
