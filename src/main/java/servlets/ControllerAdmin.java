package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.DaoAutor;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Socio;

/**
 * Servlet implementation class ControllerAdmin
 */

//@WebServlet("/controllerAdmin")
public class ControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Obtenemos la operacion que han seleccionado en el menu
		String operacion = request.getParameter("operacion");
		
		DaoAutor daoAutor = new DaoAutor();  //variable dao para autores 
		DaoSocio daoSocio = new DaoSocio();
		
		switch (operacion) {
		case "insertaautor": {
			//Lo primero sera crear el autor con los campos pasados por el form de la request
			String nombre = request.getParameter("nombre");
			String strFechaNacimiento = request.getParameter("fechaNacimiento");
			
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(strFechaNacimiento,formato);
			Date fechaEnSQL = java.sql.Date.valueOf(localDate);
			
			Autor nuevoAutor = new Autor();
			nuevoAutor.setNombre(nombre);
			nuevoAutor.setFechaNacimiento(fechaEnSQL);
			
			daoAutor = new DaoAutor();
			
			try {
				daoAutor.insertaAutor(nuevoAutor);
				
				//Ofrezco una respuesta
				request.setAttribute("confirmaroperacion", "Autor creado satisfactoriamente");
				request.getRequestDispatcher("admin/altaautor.jsp").forward(request, response); //redireccion
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					procesarError(request, response, e, "admin/altaautor.jsp");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					procesarError(request, response, e, "admin/altaautor.jsp");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
			
			break;
		}
		
		case "insertaSocio": {
			//Lo primero sera crear el autor con los campos pasados por el form de la request
			String nombre = request.getParameter("nombre");
			String email = request.getParameter("email");
			String direccion = request.getParameter("direccion");
			String password = request.getParameter("password");
			
			
			Socio nuevoSocio = new Socio();
			nuevoSocio.setNombre(nombre);
			nuevoSocio.setEmail(email);
			nuevoSocio.setDireccion(direccion);
			nuevoSocio.setClave(password);
			
			
			try {
				daoSocio.addSocio(nuevoSocio);
				
				System.out.println(nuevoSocio);
				//Ofrezco una respuesta
				Socio socioAgregado = new Socio();
				socioAgregado = daoSocio.getSocioByEmail(nuevoSocio.getEmail());
				request.setAttribute("confirmaroperacion", "Socio " + socioAgregado.getIdSocio() + " creado satisfactoriamente");
				request.getRequestDispatcher("admin/altasocio.jsp").forward(request, response); //redireccion
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					procesarError(request, response, e, "admin/altasocio.jsp");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
			
			break;
		}
		
		case "listadoSociosPaginado": {
			
			try {
				ArrayList<Socio> listaSocios = daoSocio.getSocio();
				System.out.println(listaSocios);
				
				request.setAttribute("listaSocios", listaSocios);
				request.getRequestDispatcher("admin/listadoSocios.jsp").forward(request, response); //redireccion
				
				//Copiado de Javi
				int totalRegistros = 0;
				int pagina = 0;
				int numregpag = 5;
				int paginamasalta = 0;
				List<Socio> listadoSocios = null;
				
				//Preguntar si tengo parametros en la request
				if (request.getParameter("pag") != null) {
					pagina = Integer.parseInt(request.getParameter("pag"));
				}

				if (request.getParameter("nrp") != null) {
					numregpag = Integer.parseInt(request.getParameter("nrp"));
				}
				
				//Averiguar cuantos registros hay.
				totalRegistros = daoSocio.getSocio().size();
				
				//Calcular cual es la ultima pagina
				paginamasalta = totalRegistros / numregpag;
				
				//Obtener listado de socios.
				listadoSocios = daoSocio.listadoSocios(pagina, numregpag);
				
				
				//Añadir todos los datos a la request para mandarselos a la vista
				request.setAttribute("totalRegistros", totalRegistros);
				request.setAttribute("pagina", pagina);
				request.setAttribute("numregpag", numregpag);
				request.setAttribute("paginamasalta", paginamasalta);
				request.setAttribute("listadoSocios", listadoSocios);
				
				request.getRequestDispatcher("admin/listadoSocios.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					procesarError(request, response, e, "admin/listadoSocios.jsp");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			break;
		}
		
		case "modificarSocio": {
			
			try {
				String nombre = request.getParameter("nombreBuscado");
				
				ArrayList<Socio> listaSocios = daoSocio.getSocio(nombre);
				
				request.setAttribute("listaSocios", listaSocios);
				request.getRequestDispatcher("admin/getsocio.jsp").forward(request, response); //redireccion
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					procesarError(request, response, e, "admin/listadoSocios.jsp");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + operacion);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e, String url) throws Exception, IOException {
		String mensajeError = e.getMessage();
		request.setAttribute("error", mensajeError);
		
		if (url == null) {
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
