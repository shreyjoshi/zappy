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
 * Servlet implementation class ManipulateCart
 */
@WebServlet("/ManipulateCart")
public class ManipulateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManipulateCart() {
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

		//deletes the product in cart
		String opname=request.getParameter("op");
		System.out.println(opname);
		if(opname.equals("delete"))
		{
		String cid=request.getParameter("cid");
		int pid=Integer.parseInt(request.getParameter("pid"));
		int addtocartid=Integer.parseInt(request.getParameter("addtocartid"));
			//System.out.println("hello cart delete");
			ProductDao pd=new ProductDao();
			int z=pd.deleteCartProduct(cid,addtocartid);		//deleted the specific product in cart
			if(z!=0)
			{
				ArrayList<Customer> list=pd.viewProductInCart(cid);//view products in cart
				ArrayList<Customer> list2=pd.cartValue(cid);		//gets total cart value
				RequestDispatcher rd=request.getRequestDispatcher("viewCart.jsp");//redirects to the page with data
				request.setAttribute("LIST", list);
				request.setAttribute("LIST2", list2);
				request.setAttribute("productdelete","order with orderid "+addtocartid+" is deleted from cart");
				rd.forward(request, response);
				//response.sendRedirect("ViewCartController");
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("customerHome.jsp");
				request.setAttribute("productid",pid+"can't be updated" );
				rd.forward(request, response);
			
		}	
		}
		else {
			//this controller adds product to cart
			HttpSession ss2=request.getSession();
			String u=(String)ss2.getAttribute("cid");
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			Double totalamount=Double.parseDouble(request.getParameter("totalamount"));
			int pid=Integer.parseInt(request.getParameter("pid"));

//			{										//if user is logged in then product is added to cart
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
}
