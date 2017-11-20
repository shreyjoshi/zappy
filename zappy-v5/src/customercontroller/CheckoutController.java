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
import dao.ProductDao;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet("/CheckoutController")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckoutController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//used to checkout or place the order
		ArrayList<Customer> list2=null;
		HttpSession ss2=request.getSession();
		String user=(String) ss2.getAttribute("cid");	//gets session value
		//System.out.println(user);
		ProductDao pd=new ProductDao();
		ArrayList<Customer> list=pd.viewProductInCart(user);//view the products of cart of any user
		if(!list.isEmpty()) {
			list2=pd.cartValue(user);//gets cartvalue of user
			//System.out.println(list2);
		RequestDispatcher rd=request.getRequestDispatcher("checkout.jsp");//redirects to page with data
		request.setAttribute("LIST2",list2);
		request.setAttribute("LIST",list);
		rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("checkout.jsp");
			request.setAttribute("msg","nothing in cart");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
