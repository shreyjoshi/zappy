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
 * Servlet implementation class UpdatedProductViewController
 */
@WebServlet("/UpdatedProductViewController")
public class UpdatedProductViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid=(Integer)request.getAttribute("updatedproduct");
		//System.out.println(pid+" in update product view controller");
		ProductDao pd=new ProductDao();
		ArrayList<ProductView> list=pd.viewProduct(pid);
		if(list!=null){
		RequestDispatcher rd=request.getRequestDispatcher("ProductViewController");
		request.setAttribute("LIST", list);
		rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("updateProduct.jsp");
			request.setAttribute("updateinfo","product"+pid+"updation failed");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
