package controller;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.CustomerDao;


/**
 * Servlet implementation class ForgetPass
 */
@WebServlet("/ForgetPass")
public class ForgetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPass() {
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
		
			String userid=request.getParameter("userid");	//holds email id of user
			String typeofuser =request.getParameter("typeofuser");		//depicts type of user whether admin or customer
			
			String pass=null;
			if(typeofuser.equalsIgnoreCase("admin")) {
				AdminDao ed=new AdminDao();
				pass=ed.getPassword(userid);						//gets password if type of user is admin
			}
			else {
				String cid=request.getParameter("userid");
				CustomerDao cd=new CustomerDao();					
				pass=cd.getPassword(cid);							//gets password if type of user is customer
			}
			if(pass!=null)
			{
				InetAddress addr=InetAddress.getLocalHost();		//used to get systems ipv4 address
				String zappy=addr.getHostAddress();
				System.out.println(zappy);
				sendOldPassword(userid,pass,zappy);					//this method is used to send the password to registered email
				RequestDispatcher rd=request.getRequestDispatcher("forgetpass.jsp");
				request.setAttribute("forgetpasswordstatus","your password has been mailed on the registered email id... continue shopping");
				rd.forward(request,response);
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("forgetpass.jsp");
				request.setAttribute("forgetpasswordstatus","No such user with this user id found... please enter correct details");
				rd.forward(request,response);
			}

		
	}

	public void sendOldPassword(String userid, String pass,String zappy) {
		
		//request.createHostAddress();
		String to=userid;					//to whome the password to be sent
		String sub="password Information";	//subject of it
		String msg="Your password is "+pass+" "
				+ "visit us here http://"+zappy+":8080/Home.jsp";	//password and a linnk to website
		MailController ac=new MailController();
		ac.sendMail(to,sub,msg);					
		
		
	}

	
}
