package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCarrito() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito"); 

		// Si no hay carrito, creamos uno
		if (carrito == null) {
			carrito = new HashMap<String, Integer>();
			request.getSession().setAttribute("carrito", carrito);
		}
		String producto = request.getParameter("producto");
		if (producto != null) {
			insertarEnCarrito(carrito, producto);
		}
		
		//Retornamos la vista con el parámetro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
	}

	private String carritoEnHTML(Map<String, Integer> carrito) {
		String carritoEnHTML = "";

		for (String key : carrito.keySet()) {
			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
		}
		return carritoEnHTML;
	}

	private void insertarEnCarrito(Map<String, Integer> carrito, String producto) {
		if (carrito.get(producto) == null) {
			carrito.put(producto, new Integer(1));
		} else {
			int numeroArtículos = (Integer) carrito.get(producto).intValue();
			carrito.put(producto, Integer.valueOf(numeroArtículos + 1));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
