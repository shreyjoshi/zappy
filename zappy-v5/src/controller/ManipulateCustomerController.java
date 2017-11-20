package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;

/**
 * Servlet implementation class ManipulateCustomerController
 */
@WebServlet("/ManipulateCustomerController")
public class ManipulateCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManipulateCustomerController() {
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
		String cid=request.getParameter("cid");			//gets customer id 
		String y=request.getParameter("op");			//gets type of operation to be performed 

		if(y.equals("update"))
			{
//			System.out.println("hello update");
				RequestDispatcher rd=request.getRequestDispatcher("CustomerUpdateController");
				request.setAttribute("cid",cid );
				rd.forward(request, response);
			}
		else {
				//System.out.println("hello cutomer delete");
				CustomerDao cd=new CustomerDao();
				int z=cd.deleteCustomer(cid);				//deletes the customer
				if(z!=0)
				{
					response.sendRedirect("ViewAllCustomerController");
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("viewCustomer.jsp");
					request.setAttribute("cid",cid+"can't be updated" );
					rd.forward(request, response);
				}
			}
		}
	}


