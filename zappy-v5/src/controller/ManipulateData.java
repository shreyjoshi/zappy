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
 * Servlet implementation class ManipulateData
 */
@WebServlet("/ManipulateData")
public class ManipulateData extends HttpServlet {
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
//	
//		String s=request.getParameter("pid");
//		System.out.println(">>>>>>>"+s);
		int p=Integer.parseInt(request.getParameter("pid"));     //gets the product id to be deleted
		String y=request.getParameter("op");					//GETS THE TYPE OF OPERATION TO BE PERFORMED
	if(y.equals("update"))
		{
//		System.out.println("hello data update");
			RequestDispatcher rd=request.getRequestDispatcher("ProductUpdateController");
			request.setAttribute("productid",p );
			rd.forward(request, response);
		}
	else {
			//System.out.println("hello data delete");
			ProductDao pd=new ProductDao();
			int z=pd.deleteProduct(p);						//this methods deletes the product to be deleted
			if(z!=0)
			{
				ArrayList<ProductView> list=pd.viewProduct();
				RequestDispatcher rd=request.getRequestDispatcher("viewProduct.jsp");
				request.setAttribute("LIST", list);
				request.setAttribute("deletemsg","product "+p+" deleted successfully");
//				response.sendRedirect("ProductViewController");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("viewProduct.jsp");
				request.setAttribute("productid",p+"can't be updated" );
				rd.forward(request, response);
			}
		}
	}

}
