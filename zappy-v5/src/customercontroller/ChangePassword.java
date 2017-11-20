package customercontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		//this controller is used to change admin password
		String cid=request.getParameter("cid");
		String newpassword=request.getParameter("newpassword");
		String oldpassword=request.getParameter("oldpassword");
		AdminDao ad=new AdminDao();
		int x=ad.changePassword(cid,newpassword,oldpassword);//this method changes the password of admin
		if(x!=0) {
			RequestDispatcher rd=request.getRequestDispatcher("AdminHome.jsp");//redirects control to the jsp page along with data
			request.setAttribute("changepasswordmsg", "your password changed");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("AdminHome.jsp");
			request.setAttribute("changepasswordmsg", "password change process failed");
			rd.forward(request, response);
		}
	}

}
