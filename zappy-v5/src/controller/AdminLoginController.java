package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;


/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u=request.getParameter("aid");		//holds value of login id from jsp page
		String p=request.getParameter("apass");		//holds value of password from jsp page
		AdminDao ald=new AdminDao();
		int y=ald.check(u,p);						//check(u,p) checks for credentials of admin if it is >0 means credentials are true
		if(y==1)
		{
			HttpSession ss=request.getSession();	
			ss.setAttribute("admin",u);				//used to set value of object of session
			response.sendRedirect("AdminHome.jsp");
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginstatus","Enter Valid User id and Password!!!");
			rd.forward(request,response);
		}
		}
	}


