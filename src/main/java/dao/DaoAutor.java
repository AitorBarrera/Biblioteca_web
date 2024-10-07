package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexiones.Conexion;
import entidades.Autor;
import entidades.Socio;

public class DaoAutor {
	public DaoAutor() {

	}

	public ArrayList<Autor> listadoAutores() throws SQLException, Exception {
		ArrayList<Autor> listaautores;
		listaautores = new ArrayList<Autor>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT * FROM AUTOR ORDER BY idAutor Asc";
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Autor miAutor = new Autor();
				miAutor.setIdAutor(rs.getInt("IDAUTOR"));
				miAutor.setNombre(rs.getString("NOMBRE"));
				miAutor.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
				listaautores.add(miAutor);
			}
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
		return listaautores;
	}
/*******************************************************************************/	
/*
 *     Nuevo autor
 *     Procedimiento para dar de alta un autor mediante un PreparedStatement
 *
 ********************************************************************************/
	   public void insertaAutor(Autor a) throws SQLException, Exception {
	        Connection con=null;
	        PreparedStatement st=null;
	        try {
	            Conexion miconex = new Conexion();
	            con = miconex.getConexion();
	            con.setAutoCommit(false);
	            String ordenSQL = "INSERT INTO AUTOR VALUES(S_AUTOR.NEXTVAL,?,?)";
	            st = con.prepareStatement(ordenSQL);
	            st.setString(1, a.getNombre());
	            st.setDate(2,a.getFechaNacimiento());
	            st.executeUpdate();
	            con.commit();
	            st.close();
	            con.close();
	        } catch (SQLException se) {
	            throw se;
	        } catch (Exception e) {
	            throw e;
	        }
	        finally{
	         	if(st!=null)
	                st.close();
	         	if(con!=null)
	                con.close();
	        }
	    }
/**************************************************************************************/
	   public void addAutorPLSQL(Autor a) throws SQLException, Exception{
	        Connection con=null;
	        CallableStatement st=null;
	        try {
	            Conexion miconex = new Conexion();
	            con = miconex.getConexion();
	            String ordenSQL = "{call ADDAUTOR(?,?)}";
	            st = con.prepareCall(ordenSQL);
	            st.setString(1, a.getNombre());
	            st.setDate(2,a.getFechaNacimiento());
	            st.executeUpdate();
	            st.close();
	            con.close();
	        } catch (SQLException se) {
	            throw se;
	        } catch (Exception e) {
	            throw e;
	        }
	        finally{
	         	if(st!=null)
	                st.close();
	         	if(con!=null)
	                con.close();
	        }		   
		   
	   }
/**************************************************/
	public Autor findAutorById(int idautor)throws SQLException, Exception {
		Autor a=null;
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            String ordenSQL = "SELECT IDAUTOR,NOMBRE,FECHANACIMIENTO FROM AUTOR"+
            				  " WHERE IDAUTOR=?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, idautor);
            rs=st.executeQuery();  // no se pasa la orden como parámetro porque ya
            if(rs.next()) {        // lo hemos hecho aqui con.prepareStatement(ordenSQL);
            	a=new Autor();
            	a.setIdAutor(rs.getInt("IDAUTOR"));
            	a.setNombre(rs.getString("NOMBRE"));
            	a.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
            }
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        }
        finally{
         	if(st!=null)
                st.close();
         	if(con!=null)
                con.close();
        }
        return a;
	}
	
	
	public List<Autor> listadoAutoresPaginado(int pagina, int numregpag) throws SQLException, Exception {
		ArrayList<Autor> listaautores;
		listaautores = new ArrayList<Autor>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSql = "SELECT * FROM Autor ORDER BY idAutor ASC";
			System.out.println("La orden lanzada es: " + ordenSql);
			st = con.prepareStatement(ordenSql);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Autor miAutor = new Autor();
				miAutor.setIdAutor(rs.getInt("IDAutor"));
				miAutor.setNombre(rs.getString("NOMBRE"));
				miAutor.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
				listaautores.add(miAutor);
			}
			
			int primera = pagina * numregpag;
			
			if (primera > listaautores.size()) {
				primera = listaautores.size();
			}
			
			int ultima = pagina * numregpag + numregpag;
			
			if (ultima > listaautores.size()) {
				ultima = listaautores.size();
			}
			
			List<Autor> listaautoresRecortada = listaautores.subList(primera, ultima);
			System.out.println(listaautoresRecortada);
			
			return listaautoresRecortada;
			
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
//			if (rs != null)
//				rs.close();
//			if (st != null)
//				st.close();
//			if (con != null)
//				con.close();

		}
	}
}
