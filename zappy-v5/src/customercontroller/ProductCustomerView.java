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
import javax.servlet.http.HttpSession;

import beans.Customer;
import beans.ProductView;
import dao.ProductDao;

/**
 * Servlet implementation class ProductCustomerView
 */
@WebServlet("/ProductCustomerView")
public class ProductCustomerView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//controller used to get information all the available product
		ArrayList<Customer> list2=null;
		HttpSession ss=request.getSession();
		String cid=(String)ss.getAttribute("cid");
		ProductDao pd=new ProductDao();
		ArrayList<ProductView> list=pd.viewProduct();	//gets list of all the items in product
		RequestDispatcher rd=request.getRequestDispatcher("productDisplay.jsp");	//sends data to this page
		if(cid!=null)
		{
		 list2=pd.cartValue(cid);
		request.setAttribute("LIST2",list2);
		}else
		{
			InetAddress addr=InetAddress.getLocalHost();
			String ipadd=addr.getHostAddress();
			list2=pd.cartValue(ipadd);
			request.setAttribute("LIST2",list2);
		}
		request.setAttribute("LIST", list);
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
