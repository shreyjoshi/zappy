package customercontroller;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this controller adds product to cart
		HttpSession ss2=request.getSession();
		String u=(String)ss2.getAttribute("cid");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		Double totalamount=Double.parseDouble(request.getParameter("totalamount"));
		int pid=Integer.parseInt(request.getParameter("pid"));
//		System.out.println(u+" in add to cart");
//		System.out.println(pid);
		
//		if(u==null)		//checks if session is null,i.e. user is logged in or not
//		{
//			boolean isInAddToCart=true;
//			RequestDispatcher rd=request.getRequestDispatcher("customerLogin.jsp");
//			request.setAttribute("loginmsg","please login first");
//			request.setAttribute("isinaddtocart",isInAddToCart);
//			request.setAttribute("pid",pid);
//			request.setAttribute("quantity", quantity);
//			request.setAttribute("totalamount", totalamount);
//			rd.forward(request, response);
//		}
//		else
//		{										//if user is logged in then product is added to cart
			ProductDao pd=new ProductDao();
			int y=0;
			Date d=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MMMM-dd");
			String date=sf.format(d);
			InetAddress addr=InetAddress.getLocalHost();
			String ipadd=addr.getHostAddress();
			if(u==null)
			{
			y=pd.addToCart(pid,ipadd,quantity,totalamount,date);	//this method is used to add ordern to cart
			}
			else {
				y=pd.addToCart(pid, u, quantity, totalamount, date);
			}
			if(y!=0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("ProductCustomerView");
				request.setAttribute("buystatus", "product no. "+pid+" added successfully");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("ProductCustomerView");
				request.setAttribute("buystatus", "product "+pid+" is out of orderdate");
				rd.forward(request, response);
			}
		//}
	}
	
}
