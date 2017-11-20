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
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/ProductUpdateController")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//perform operations of admin on the products
		int pid=(Integer)request.getAttribute("productid");
		ProductDao pd=new ProductDao();
		ArrayList<ProductView> list=pd.viewProduct(pid);
		RequestDispatcher rd=request.getRequestDispatcher("updateProduct.jsp");
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
