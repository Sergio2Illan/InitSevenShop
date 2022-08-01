package com.initseven.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.initseven.dao.impl.UsuarioDAOImpl;
import com.initseven.model.Usuario;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = request.getParameter("user").toString() != null && !request.getParameter("user").toString().isBlank() && !request.getParameter("user").toString().isEmpty() ? request.getParameter("user").toString() : "Anonymous";
		String password = request.getParameter("password").toString() != null && !request.getParameter("password").toString().isBlank() && !request.getParameter("password").toString().isEmpty() ? request.getParameter("password").toString() : "123";
		
		boolean encontrado = buscarUserByPassword(user, password);
		
		if(encontrado) {
			session.setAttribute("logado", true);
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("error_login.jsp");
		}
	}
	
	private boolean buscarUserByPassword(String user, String password) {
		boolean encontrado = false;
		
		UsuarioDAOImpl impl = new UsuarioDAOImpl();
		
		String pass = DigestUtils.md5Hex(password);
		Usuario usu = impl.getUsuarioByUserAndPassword(user, pass);
		System.out.println("=====> "+user+" ---- "+pass);
		System.out.println("=====> "+usu.getEmail()+" ---- "+usu.getPassword());
		
		if(usu != null) {
			
			encontrado = usu.getEmail().equals(user) && pass.equals(pass)? true : false;
		}
		
		return encontrado;
	}

}
