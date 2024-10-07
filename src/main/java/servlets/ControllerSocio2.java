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

import dao.DaoAutor;
import entidades.Autor;

/**
 * Servlet implementation class ControllerSocio2
 */
public class ControllerSocio2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerSocio2() {
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
		
		switch (operacion) {
		case "listarAutores": {
			//Lo primero sera crear el autor con los campos pasados por el form de la request
			
			try {
				ArrayList<Autor> listaAutores = daoAutor.listadoAutores();
				
				request.setAttribute("listaAutores", listaAutores);
				
				request.getRequestDispatcher("socios/listadoAutores.jsp").forward(request, response); //redireccion
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					procesarError(request, response, e, "socios/listadoAutores.jsp");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					procesarError(request, response, e, "socios/listadoAutores.jsp");
					
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
