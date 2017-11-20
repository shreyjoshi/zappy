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
 * Servlet implementation class ProductViewController
 */
@WebServlet("/ProductViewController")
public class ProductViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ProductViewController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//takes admin to the products list so he can perform operations on it
		ProductDao pd=new ProductDao();
		ArrayList<ProductView> list=pd.viewProduct();
		RequestDispatcher rd=request.getRequestDispatcher("viewProduct.jsp");
		request.setAttribute("LIST", list);
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
