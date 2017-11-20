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
import beans.ProductView;
import dao.CustomerDao;
import dao.ProductDao;

/**
 * Servlet implementation class CheckCustomerLogin
 */
@WebServlet("/CheckCustomerLogin")
public class CheckCustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCustomerLogin() {
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
		//used to check customer login
		String cid=request.getParameter("cid");
		String cpass=request.getParameter("cpass");
		boolean verify=Boolean.parseBoolean(request.getParameter("verify"));
		
		//System.out.println(pid+"in check customer controller");
//		System.out.println(cid);
//		System.out.println(cpass);
//		System.out.println(verify);
		CustomerDao cd=new CustomerDao();
		int y=0;
		y=cd.checkLogin(cid,cpass);//checks the emailid and password inserted
		if(y==1)					//if true 
		{
			InetAddress addr=InetAddress.getLocalHost();
			String ipadd=addr.getHostAddress();
			cd.updateIpaddressInCart(ipadd,cid);
			HttpSession ss2=request.getSession();
			ss2.setAttribute("cid", cid);				//sets session id
			if(verify)							//checks if verify variable is true i.e from add to cart then initiates adtocart method
			{
				//System.out.println("verfied after log in");
				int pid=Integer.parseInt(request.getParameter("pid"));
							
				int z=0;
				String c=(String)ss2.getAttribute("cid");
				//System.out.println(c+" value of session");
				int quantity=Integer.parseInt(request.getParameter("quantity"));
				double totalamount=Double.parseDouble(request.getParameter("ta"));
				ProductDao pd=new ProductDao();
				Date d=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MMMM-dd");//gets current date in requested format
				String date=sf.format(d);
				z=pd.addToCart(pid,c,quantity,totalamount,date);//adds product to cart
				if(z!=0)
				{
					ArrayList<ProductView> list=pd.viewProduct();//gets lists of all the products
					 ArrayList<Customer>list2=pd.cartValue(cid);//gets the total quantity and total amount of cart
						
					RequestDispatcher rd=request.getRequestDispatcher("productDisplay.jsp");//sends data to page alongwith data
					request.setAttribute("LIST", list);	
					request.setAttribute("LIST2",list2);  
					request.setAttribute("cartmsg", "product "+pid+" added successfully");
					rd.forward(request, response);
				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("customerHome.jsp");
					request.setAttribute("cartmsg", "product "+pid+" is out of stock");
					rd.forward(request, response);
				}
			}
			else {
				response.sendRedirect("customerHome.jsp");
//				request.setAttribute("welcomemsg", "welcome "+cid);
//				rd.forward(request, response);
			}
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("customerLogin.jsp");
			request.setAttribute("loginmsg","enter valid details!!!!!");
			rd.forward(request, response);
		}
	}

}
