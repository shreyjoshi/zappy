package customercontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Customer;
import beans.ProductView;
import dao.CustomerDao;
import dao.ProductDao;

/**
 * Servlet implementation class ProductInfo
 */
@WebServlet("/ProductInfo")
public class ProductInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this method is used to get information of specific product
				int pid=Integer.parseInt(request.getParameter("pid"));
			//System.out.println(pid);
			ProductDao pd=new ProductDao();
			ArrayList<ProductView> list=pd.getProductInfo(pid);//gets the info of specific product
			CustomerDao cd=new CustomerDao();
			ArrayList<Customer> list1=cd.loadReview(pid);
			RequestDispatcher rd=request.getRequestDispatcher("productInfo.jsp");//redirects to the specific page along withdata
			request.setAttribute("productinfo", list);
			request.setAttribute("reviewinfo", list1);
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//this method is used to get information of specific product
		int pid=Integer.parseInt(request.getParameter("pid"));
	//System.out.println(pid);
	ProductDao pd=new ProductDao();
	ArrayList<ProductView> list=pd.getProductInfo(pid);//gets the info of specific product
	CustomerDao cd=new CustomerDao();
	ArrayList<Customer> list1=cd.loadReview(pid);
	RequestDispatcher rd=request.getRequestDispatcher("productInfo.jsp");//redirects to the specific page along withdata
	request.setAttribute("productinfo", list);
	request.setAttribute("reviewinfo", list1);
	rd.forward(request, response);
	}

}
