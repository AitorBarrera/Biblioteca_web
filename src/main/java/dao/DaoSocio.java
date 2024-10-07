package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexiones.Conexion;
import entidades.Socio;
import util.Hash;

public class DaoSocio {

	public DaoSocio() {
		super();
	}
	
	public void addSocio(Socio so) {
		Connection con = null;
		PreparedStatement st = null;
		Conexion miconex = new Conexion();
		
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String sql = "INSERT INTO Socio (idSocio, email, nombre, direccion, version) VALUES(S_SOCIO.NEXTVAL,?,?,?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, so.getEmail());
			st.setString(2, so.getNombre());
			st.setString(3, so.getDireccion());
			st.setInt(4, 1);
			st.execute();
			st.close();

			sql = "INSERT INTO Usuarios (usuario, clave) VALUES(?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, so.getEmail());
			st.setString(2, Hash.getHash(so.getClave(), "MD5"));
			st.execute();
			st.close();

			sql = "INSERT INTO Grupos (idgrupo, idusuario) VALUES(?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, "sociosbiblioteca");
			st.setString(2, so.getEmail());
			st.execute();
			st.close();
			
			con.commit();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				st.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public ArrayList<Socio> getSocio() {
		Connection con = null;
		PreparedStatement st = null;
		Conexion miconex = new Conexion();
		
		ArrayList<Socio> listaSocioes = new ArrayList<Socio>();
		
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String sql = "SELECT * FROM Socio ORDER BY idSocio ASC";
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Socio nuevoSocio = new Socio(rs.getInt("idSocio"),rs.getString("email"),rs.getString("nombre"),rs.getString("direccion"), rs.getInt("version"));
				listaSocioes.add(nuevoSocio);
			}
			
			return listaSocioes;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				st.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return listaSocioes;
		
	}
	
//	public ArrayList<Socio> listadoSocios(int pagina, int numregpag)
//			throws SQLException, Exception {
//
//		ArrayList<Socio> listasocios;
//		listasocios = new ArrayList<Socio>();
//		Connection con = null;
//		ResultSet rs = null;
//		PreparedStatement st = null;
//		try {
//			Conexion miconex = new Conexion();
//			con = miconex.getConexion();
//			String ordenSql = "SELECT IDSOCIO,EMAIL,NOMBRE,DIRECCION "
//					+ "FROM( SELECT ROWNUM FILA ,IDSOCIO,EMAIL,NOMBRE,DIRECCION "
//					+ "FROM( SELECT IDSOCIO,EMAIL,NOMBRE,DIRECCION "
//					+ "FROM SOCIO "
//					+ "ORDER BY NOMBRE))"
//					+ "WHERE FILA >=? AND FILA<=?";
//			System.out.println("La orden lanzada es: " + ordenSql);
//			st = con.prepareStatement(ordenSql);
//			st.setInt(1, (pagina * numregpag) + 1);
//			st.setInt(2, (pagina * numregpag) + numregpag);
//			rs = st.executeQuery();
//			while (rs.next()) {
//				Socio miSocio = new Socio();
//				miSocio.setIdSocio(rs.getInt("IDSOCIO"));
//				miSocio.setEmail(rs.getString("EMAIL"));
//				miSocio.setNombre(rs.getString("NOMBRE"));
//				miSocio.setDireccion(rs.getString("DIRECCION"));
//				listasocios.add(miSocio);
//			}
//		} catch (SQLException se) {
//			throw se;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			if (rs != null)
//				rs.close();
//			if (st != null)
//				st.close();
//			if (con != null)
//				con.close();
//
//		}
//		return listasocios;
//	}
	
	public List<Socio> listadoSocios(int pagina, int numregpag)
			throws SQLException, Exception {

		ArrayList<Socio> listasocios;
		listasocios = new ArrayList<Socio>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSql = "SELECT * FROM Socio";
			System.out.println("La orden lanzada es: " + ordenSql);
			st = con.prepareStatement(ordenSql);
			rs = st.executeQuery();
			while (rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdSocio(rs.getInt("IDSOCIO"));
				miSocio.setEmail(rs.getString("EMAIL"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				miSocio.setDireccion(rs.getString("DIRECCION"));
				listasocios.add(miSocio);
			}
			
			int primera = pagina * numregpag;
			
			if (primera > listasocios.size()) {
				primera = listasocios.size();
			}
			
			int ultima = pagina * numregpag + numregpag;
			
			if (ultima > listasocios.size()) {
				ultima = listasocios.size();
			}
			
			List<Socio> listaSociosRecortada = listasocios.subList(primera, ultima);
			
			return listaSociosRecortada;
			
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();

		}
	}
	
	public Socio getSocio(int idSocio){
		Connection con = null;
		PreparedStatement st = null;
		Conexion miconex = new Conexion();

		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String sql = "SELECT * FROM Socio WHERE idSocio= " + idSocio;
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Socio nuevoSocio = new Socio(rs.getInt("idSocio"),rs.getString("email"),rs.getString("nombre"),rs.getString("direccion"), rs.getInt("version"));
				return nuevoSocio;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				st.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public Socio getSocioByEmail(String email){
		Connection con = null;
		PreparedStatement st = null;
		Conexion miconex = new Conexion();

		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String sql = "SELECT * FROM Socio WHERE email= '" + email + "'";
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Socio nuevoSocio = new Socio(rs.getInt("idSocio"),rs.getString("email"),rs.getString("nombre"),rs.getString("direccion"), rs.getInt("version"));
				return nuevoSocio;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				st.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * Metodo para buscar socios por nombre.
	 * @return lista de socios con nombre que coincida
	 */
	public ArrayList<Socio> getSocio(String nombreBuscado) {
		Connection con = null;
		PreparedStatement st = null;
		Conexion miconex = new Conexion();
		
		ArrayList<Socio> listaSocios = new ArrayList<Socio>();
		
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String sql = "SELECT * FROM socio WHERE UPPER(TRIM(nombre)) LIKE UPPER(TRIM('%" + nombreBuscado.toUpperCase() + "%'))";
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Socio nuevoSocio = new Socio(rs.getInt("idSocio"),rs.getString("email"),rs.getString("nombre"),rs.getString("direccion"), rs.getInt("version"));
				listaSocios.add(nuevoSocio);
			}
			
			return listaSocios;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				st.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return listaSocios;
		
	}
	
	public void removeSocio(Socio a){
		Connection con = null;
		PreparedStatement st = null;
		Conexion miconex = new Conexion();
		
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String sql = "DELETE FROM Socio WHERE idSocio=" + a.getIdSocio() ;
			st = con.prepareStatement(sql);
			st.execute();
			con.commit();
			System.out.println("Se ha borrado el socio con id: " + a.getIdSocio());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			try {
				st.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateSocio(Socio so){
		Connection con = null;
		PreparedStatement st = null;
		Conexion miconex = new Conexion();
		
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String sql = "UPDATE Socio SET email = ?, nombre = ?, direccion = ?, version = ? WHERE idSocio = " + so.getIdSocio() ;
			st = con.prepareStatement(sql);
			st = con.prepareStatement(sql);
			st.setString(1, so.getEmail());
			st.setString(2, so.getNombre());
			st.setString(3, so.getDireccion());
			st.setInt(4, so.getVersion());
			st.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {

			try {
				st.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
