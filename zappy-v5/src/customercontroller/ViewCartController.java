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
 * Servlet implementation class ViewCartController
 */
@WebServlet("/ViewCartController")
public class ViewCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this controller returns a list of all the products within a cart of any user
		HttpSession ss2=request.getSession();
		String user=(String)ss2.getAttribute("cid");
		ProductDao pd=new ProductDao();
		ArrayList<Customer> list=pd.viewProductInCart(user);
		ArrayList<Customer> list2=pd.cartValue(user);
		RequestDispatcher rd=request.getRequestDispatcher("viewCart.jsp");
		request.setAttribute("LIST", list);
		request.setAttribute("LIST2", list2);
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
