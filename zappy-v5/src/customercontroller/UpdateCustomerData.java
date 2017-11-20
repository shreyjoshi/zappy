package customercontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Customer;
import dao.CustomerDao;

/**
 * Servlet implementation class UpdateCustomerData
 */
@WebServlet("/UpdateCustomerData")
public class UpdateCustomerData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomerData() {
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
		//updates the profile of a user
		String cid=request.getParameter("cid");
		String cname=request.getParameter("cname");
		String cadd=request.getParameter("cadd");
		String cpass=request.getParameter("cpass");
		String cmob=request.getParameter("cmob");
		Customer c=new Customer(cid,cname,cpass,cmob,cadd);
		CustomerDao cd=new CustomerDao();
		int y=cd.updateCustomerData(c);//this methods inserts the updated data in the database
		if(y!=0) {
			RequestDispatcher rd=request.getRequestDispatcher("customerHome.jsp");
			request.setAttribute("profileupdated", "your profile has been updated");
			rd.forward(request, response);
		}
	}

}
