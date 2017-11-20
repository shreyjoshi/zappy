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
 * Servlet implementation class CancelOrder
 */
@WebServlet("/CancelOrder")
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrder() {
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
		//this controller is used to cancel an order of user
		int orderid=Integer.parseInt(request.getParameter("orderid"));//gets order id
		CustomerDao cd=new CustomerDao();
		int x=cd.cancelOrder(orderid);//initiates method which cancels orders
		HttpSession ss=request.getSession();
		String cid=(String)ss.getAttribute("cid");
		ArrayList<Customer> list=cd.viewOrderHistory(cid);//views all the orders placed by user
		if(x!=0) {
			RequestDispatcher rd=request.getRequestDispatcher("viewOrderHistory.jsp");//redirects to this jsp with all the data
			request.setAttribute("LIST",list);
			request.setAttribute("cancellationmsg", "your order with orderid "+orderid+" has been successfully cancelled");
			rd.forward(request, response);
		}
	}

}
