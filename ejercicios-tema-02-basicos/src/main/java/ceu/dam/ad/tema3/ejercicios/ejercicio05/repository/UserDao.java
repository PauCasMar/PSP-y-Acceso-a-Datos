package ceu.dam.ad.tema3.ejercicios.ejercicio05.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ceu.dam.ad.tema3.ejercicios.ejercicio05.model.User;

public class UserDao {

	public Long insert(Connection conn, User user) throws SQLException{
		String sql = "insert into usuarios (username, password, email, fecha_alta, fecha_ult_login) values (?,?,?,?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setDate(4, Date.valueOf(user.getCreatedDate()));
			stmt.setDate(5, user.getLastLoginDate() == null ? null : Date.valueOf(user.getLastLoginDate()));
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getLong(1);
		}
		
	}
	
	public User getByEmail(Connection conn, String email) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE email = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, email);
			return getUser(stmt);
		}
	}

	
	public User getById(Connection conn, Long id) throws SQLException{
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setLong(1, id);
			return getUser(stmt);
		}
	}


	
	public User getByUserName(Connection conn, String userName) throws SQLException{
		String sql = "SELECT * FROM usuarios WHERE username = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, userName);
			return getUser(stmt);
		}
	}

	public Integer update(Connection conn, User user) throws SQLException{
		String sql = "update usuarios set username = ?, password = ?, email = ?, fecha_alta = ?, fecha_ult_login = ? where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql);){
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setDate(4, Date.valueOf(user.getCreatedDate()));
			stmt.setDate(5, Date.valueOf(user.getLastLoginDate()));
			stmt.setLong(6, user.getId());
			return stmt.executeUpdate();
		}
	}
	
	private User getUser(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			User u = new User();
			u.setId(rs.getLong("id"));
			u.setUsername(rs.getString("username"));
			u.setEmail(rs.getString("email"));
			u.setPassword(rs.getString("password"));
			u.setCreatedDate(rs.getDate("fecha_alta")==null ? null : rs.getDate("fecha_alta").toLocalDate());
			u.setLastLoginDate(rs.getDate("fecha_ult_login")==null ? null : rs.getDate("fecha_ult_login").toLocalDate());
			return u;
		}
		return null;
	}
}
