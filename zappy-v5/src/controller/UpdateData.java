package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProductView;
import dao.ProductDao;

/**
 * Servlet implementation class UpdateData
 */
@WebServlet("/UpdateData")
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateData() {
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
		String pname=request.getParameter("pname");
		String pweight=request.getParameter("pweight")+" gms";
		String pdesc=request.getParameter("pdesc");
		int pid=Integer.parseInt(request.getParameter("pid"));
		double price=Double.parseDouble(request.getParameter("price"));
		//String price1=request.getParameter("price");
		//System.out.println(pid+" "+pname+" "+pdesc+" "+price+" "+pweight);
		ProductDao pd=new ProductDao();
		int y=pd.updateData(pid, pname, price, pweight, pdesc);		//used to update the detaiks of the product
		if(y!=0)
		{
			ArrayList<ProductView> list=pd.viewProduct(pid);
	    	RequestDispatcher rd=request.getRequestDispatcher("updateProduct.jsp");		//("UpdatedProductViewController");
			request.setAttribute("LIST",list);
			request.setAttribute("updateinfo","product "+pid+" and "+pname+" updation successfull");
			rd.forward(request, response);
		}
		else {
			ArrayList<ProductView> list=pd.viewProduct(pid);
			RequestDispatcher rd=request.getRequestDispatcher("updateProduct.jsp");
			request.setAttribute("LIST",list);
			request.setAttribute("updateinfo","product "+pid+" and "+pname+" updation failed");
			rd.forward(request, response);
		}
	}

}
