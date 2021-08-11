package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {
	private static Connection conexion;

	public Connection getConexion() {
		return conexion;
	}

	// Metodo para abrir la conexion
	public void Connect() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.45:3306?useTimezone=true&serverTimezone=UTC",
					"remote", "PasswordRoot8?");
			System.out.println("Servidor conectado.");

		} catch (SQLException | ClassNotFoundException ex) {

			System.out.println("No se pudo conectar con la BD.");
			System.out.println(ex);

		}

	}

	// Metodo para cerrar la conexion
	public void cerrarConnec() {
		try {
			conexion.close();
			System.out.println("Conexión finalizada.");

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
			System.out.println("Error, cerrando conexión.");

		}
	}

	public String manipularBD(String nombre, String accion, String query) {
		try {
			String QueryDB = "USE ud22";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(QueryDB);
			Statement st = conexion.createStatement();
			st.executeUpdate(query);
			return ("Datos " + nombre + " correctamente");
		} catch (SQLException e) {
			return ("Error al " + accion + " datos");
		}
	}

	public String Select(String accion, String query) {
		String resultado = "";

		try {
			String sql = "Select * from cliente where id = ? ";
			String QueryDB = "USE ud22";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(QueryDB);
			PreparedStatement consulta = conexion.prepareStatement(sql);
			consulta.setInt(1, Integer.parseInt(query));
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				resultado += res.getString("id") + " ";
				resultado += res.getString("nombre") + " ";
				resultado += res.getString("apellido") + " ";
				resultado += res.getString("direccion") + " ";
				resultado += res.getString("dni") + " ";
				resultado += res.getString("fecha") + " ";
			}
			res.close();
			return resultado;
		} catch (SQLException e) {
			System.out.println(e);
			return "Error al " + accion + " datos";
		}
	}
}
