package ceu.dam.ad.users.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ceu.dam.ad.users.model.User;

public class UserDao {

	/** Debe insertar un usuario en BBDD. Devuelve el ID generado. */
	public Long insert(Connection conn, User user) throws SQLException {

		String sql = "INSERT UNTO USUARIOS VALUES(null,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getEmail());
		pstmt.setDate(4, Date.valueOf(user.getCreatedDate()));
		pstmt.setDate(5, Date.valueOf(user.getLastLoginDate()));

		pstmt.execute();

		ResultSet rs = pstmt.getGeneratedKeys();

		Long id = rs.getLong(1);
		return id;

	}

	/**
	 * Debe consultar un usuario por su email y devolverlo. Si no existe, devolverá
	 * null
	 */
	public User getByEmail(Connection conn, String email) throws SQLException {
		String sql = "SELECT * FROM USUARIOS WHERE EMAIL = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
		return	datosUsuario(rs);
		}
		
		return null;
	}

	/**
	 * Debe consultar un usuario por su ID y devolverlo. Si no existe, devolverá
	 * null. NOTA: no dupliques código
	 */
	public User getById(Connection conn, Long id) throws SQLException {
		String sql = "SELECT * FROM USUARIOS WHERE ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return	datosUsuario(rs);
		}	
		
		return null;
	}


	
	
	/**
	 * Debe consultar un usuario por su username y devolverlo. Si no existe, devolverá
	 * null. NOTA: no dupliques código
	 */
	public User getByUserName(Connection conn, String userName) throws SQLException {
		String sql = "SELECT * FROM USUARIOS WHERE username = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return	datosUsuario(rs);
		}	
		
		return null;
	}

	/**
	 * Debe actualizar todos los datos de un usuario y devolver el número de
	 * registros actualizados.
	 */
	public Integer update(Connection conn, User user) throws SQLException {
		String sql = "update usuarios set username = ?, password = ?, email = ?, fecha_alta = ?, fecha_ult_login = ? where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
				
		stmt.setLong(6, user.getId());
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getEmail());
		stmt.setDate(4, Date.valueOf(user.getCreatedDate()));
		stmt.setDate(5, Date.valueOf(user.getLastLoginDate()));

		return stmt.executeUpdate();
		
	}
	
	
	//Metodo para completar datos de usuario
	private User datosUsuario (ResultSet rs) throws SQLException {
		User usuario = new User();
		usuario.setUsername(rs.getString("username"));
		usuario.setPassword(rs.getString("password"));
		usuario.setEmail(rs.getString("email"));
		Date fechaAlta = rs.getDate("fecha_alta");
		usuario.setCreatedDate(fechaAlta.toLocalDate());
		Date lastLog = rs.getDate("fecha_ult_login");
		usuario.setCreatedDate(lastLog.toLocalDate());
		usuario.setId(rs.getLong("id"));
		
		return usuario;
		
	}

}
