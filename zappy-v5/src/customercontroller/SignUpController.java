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
import beans.ProductView;
import controller.MailController;
import dao.CustomerDao;
import dao.ProductDao;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		//gets the data from sign up form and uploads it in database
		String cname	=request.getParameter("cname");
		String cadd		=request.getParameter("cadd");
		String cmob		=request.getParameter("cmob");
		String cid	=request.getParameter("cid");
		String cpass	=request.getParameter("cpass");
		Customer c=new Customer(cid,cname,cpass,cmob,cadd);//sets the values of user data in beans class variables
		CustomerDao cd=new CustomerDao();

		int y=cd.insertCustomer(c);	//inserts the data in databae of user
		String zappy=null;
		if(y!=0)				//if insertion in database is successfull
		{
			InetAddress addr=InetAddress.getLocalHost();	//gets the local ip address
			zappy=addr.getHostAddress();
			sendSignUpMail(cid,cpass,zappy);	//sends the mail to user with login details
			ProductDao pd=new ProductDao();
			ArrayList<ProductView> list=pd.viewProduct();		//get the lists of all the products and their details
			RequestDispatcher rd=request.getRequestDispatcher("productDisplay.jsp");//redirects to the page along with the data of products
			request.setAttribute("LIST", list);
			request.setAttribute("msgsignup","you have successfully signed up,credentials are sent to registered email...happy browsing..");
			rd.forward(request, response);
		}
	
	}
void sendSignUpMail(String cid,String cpass,String zappy) {
		
		String to=cid;
		String sub="your credentials for zappy login";
		String msg="Welcome to zappy your login id= "+cid+" and"
				+ " password= "+cpass+" visit us at http://"+zappy+":8080/Home.jsp";
		MailController mc=new MailController();
		mc.sendMail(to, sub, msg);
	}

}

