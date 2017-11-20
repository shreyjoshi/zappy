package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Customer;
import dao.CustomerDao;

/**
 * Servlet implementation class OperateProduct
 */
@WebServlet("/OperateProduct")
public class OperateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperateProduct() {
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
		String op=request.getParameter("op");
		String cid=request.getParameter("cid");
		int orderid=Integer.parseInt(request.getParameter("orderid"));
		//System.out.println(op+" "+cid+" "+orderid);
		int pid=Integer.parseInt(request.getParameter("pid"));
	
			CustomerDao cd=new CustomerDao();
			int z=cd.dispatchOrder(cid,pid,orderid);   //this method changes the status field of product from 0 to 1
			if(z!=0)
			{
				sendOrderMail(cid,pid,orderid);
				ArrayList<Customer> list=cd.viewTodayOrder();
				RequestDispatcher rd=request.getRequestDispatcher("viewTodayOrder.jsp");
				request.setAttribute("LIST",list);
				request.setAttribute("dispatchstatus", "order for "+pid+" from "+cid+" successfully dispatched");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("viewTodayOrder.jsp");
				request.setAttribute("dispatchstatus","can't be dispatch");
				rd.forward(request, response);
			}
		
	}
	public void sendOrderMail(String cid,int pid,int orderid) 
	{
		String to=cid;
		String sub="Order approved and dispatched";
		String msg="Your order with orderid "+orderid+" has been successfully placed and dispatched."
				+ "Happy Shopping!!!" ;
		MailController mc=new MailController();
		mc.sendMail(to, sub, msg);	
	}

}
