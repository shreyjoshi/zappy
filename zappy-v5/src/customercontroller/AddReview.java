package customercontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProductView;
import dao.CustomerDao;
import dao.ProductDao;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReview() {
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
	int pid=Integer.parseInt(request.getParameter("pid"));
	String cid=request.getParameter("cid");
	String review=request.getParameter("review");
	double rating=Double.parseDouble(request.getParameter("rating"));
	CustomerDao cd=new CustomerDao();
	int x=cd.addReview(pid,cid,review,rating);
	if(x>0) {
		//this method is used to get information of specific product
	//System.out.println(pid);
	ProductDao pd=new ProductDao();
	ArrayList<ProductView> list=pd.getProductInfo(pid);//gets the info of specific product
	request.setAttribute("productinfo", list);
		RequestDispatcher rd=request.getRequestDispatcher("productInfo.jsp");
		request.setAttribute("reviewpost", "your review has been posted");
		rd.forward(request, response);
	}
	else {
		//this method is used to get information of specific product
	//System.out.println(pid);
	ProductDao pd=new ProductDao();
	ArrayList<ProductView> list=pd.getProductInfo(pid);//gets the info of specific product
		RequestDispatcher rd=request.getRequestDispatcher("productInfo.jsp");
		request.setAttribute("reviewpost", "unable to post review ");
		request.setAttribute("productinfo", list);
		rd.forward(request, response);
	}
	}

}
