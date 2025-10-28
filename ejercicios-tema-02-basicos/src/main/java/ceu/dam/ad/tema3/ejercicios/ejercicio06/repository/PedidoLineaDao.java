package ceu.dam.ad.tema3.ejercicios.ejercicio06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.PedidoLinea;

public class PedidoLineaDao {

	public void insertar(Connection conn, PedidoLinea linea) throws SQLException {
		String sql = "insert into pedidos_lineas (id_pedido, numero_linea, articulo, precio) values (?,?,?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, linea.getIdPedido());
			stmt.setInt(2, linea.getNumLinea());
			stmt.setString(3, linea.getArticulo());
			stmt.setBigDecimal(4, linea.getPrecio());
			stmt.execute();
		}
	}
	
	
	public List<PedidoLinea> consultar(Connection conn, Long idPedido) throws SQLException {
		String sql = "SELECT * FROM PEDIDOS_LINEAS WHERE ID_PEDIDO = " + idPedido;
		try (PreparedStatement stmt = conn.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			List<PedidoLinea> lineas = new ArrayList<PedidoLinea>();
			while(rs.next()) {
				PedidoLinea linea = new PedidoLinea();
				linea.setArticulo(rs.getString("articulo"));
				linea.setPrecio(rs.getBigDecimal("precio"));
				linea.setNumLinea(rs.getInt("numero_linea"));
				linea.setIdPedido(idPedido);
				lineas.add(linea);
			}
			return lineas;
		}
	}

}
