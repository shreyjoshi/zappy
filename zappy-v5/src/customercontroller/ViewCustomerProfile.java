package customercontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Customer;
import dao.CustomerDao;

/**
 * Servlet implementation class ViewCustomerProfile
 */
@WebServlet("/ViewCustomerProfile")
public class ViewCustomerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCustomerProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this controller returns a list with the details of user profile to a jsp page
		HttpSession ss=request.getSession();
		String cid=(String)ss.getAttribute("cid");
		//System.out.println(cid+" in viewCustomer");
		CustomerDao cd=new CustomerDao();
		ArrayList<Customer> list=cd.viewProfile(cid);
		RequestDispatcher rd=request.getRequestDispatcher("viewCustomerProfile.jsp");
		request.setAttribute("LIST",list);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
