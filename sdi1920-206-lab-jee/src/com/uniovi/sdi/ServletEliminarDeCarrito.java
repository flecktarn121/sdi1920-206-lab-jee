package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEliminarDeCarrito
 */
@WebServlet("/eliminarDeCarrito")
public class ServletEliminarDeCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEliminarDeCarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");

		// Si no hay carrito, creamos uno
		if (carrito == null) {
			carrito = new HashMap<String, Integer>();
			request.getSession().setAttribute("carrito", carrito);
		}
		String producto = request.getParameter("producto");
		if (producto != null) {
			eliminarDeCarrito(carrito, producto);
		}
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
	}

	private void eliminarDeCarrito(HashMap<String, Integer> carrito, String producto) {
		if (carrito.containsKey(producto) && carrito.get(producto) > 0) {
			int numeroDeArtículos = carrito.get(producto).intValue();
			if (numeroDeArtículos == 1) {
				carrito.remove(producto);
			} else {
				carrito.put(producto, new Integer(numeroDeArtículos - 1));
			}
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
