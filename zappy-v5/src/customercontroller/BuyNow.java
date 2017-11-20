package customercontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MailController;
import dao.CustomerDao;

/**
 * Servlet implementation class BuyNow
 */
@WebServlet("/BuyNow")
public class BuyNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyNow() {
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
		//this controller is used to place a order and order is added to order table
		String user=request.getParameter("cid");
		//int product=Integer.parseInt(request.getParameter("pid"));
		CustomerDao cd=new CustomerDao();
		int x=cd.buyNow(user);			//this method add the order to order table and deletes the order from cart 
		if(x==1)
		{
			sendOrderStatus(user);	//this method sends mail to user registered email when order is successfully placed
			RequestDispatcher rd=request.getRequestDispatcher("customerHome.jsp");//redirects to customerHome.jsp
			request.setAttribute("orderstatus", "congratulations order placed");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("customerHome.jsp");
			request.setAttribute("orderstatus", "order failed");
			rd.forward(request, response);
		}
	}
	
	void sendOrderStatus(String cid) {
		String to=cid;
		String sub="voila.... your order placed";
		String msg="voila your order has been successfully placed ,soon it will be dispatched and you will be notified.";
		MailController mc=new MailController();
		mc.sendMail(to, sub, msg);
	}

}
