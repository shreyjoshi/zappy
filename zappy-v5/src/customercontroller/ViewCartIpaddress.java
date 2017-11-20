package customercontroller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Customer;
import dao.ProductDao;

/**
 * Servlet implementation class ViewCartIpaddress
 */
@WebServlet("/ViewCartIpaddress")
public class ViewCartIpaddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCartIpaddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InetAddress addr=InetAddress.getLocalHost();
		String user=addr.getHostAddress();
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
