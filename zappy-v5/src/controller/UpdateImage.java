package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import beans.ProductView;
import dao.ProductDao;
import dto.DBConnector;

/**
 * Servlet implementation class UpdateData
 */
//used to update the image of product
@WebServlet("/UpdateImage")
public class UpdateImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private String filePath;
	   private int maxFileSize = 1000 * 4096;
	   private int maxMemSize = 100 * 4096;
	   private File file ;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  boolean isMultipart = ServletFileUpload.isMultipartContent(request);
      
		  if( !isMultipart )
		  {
             return;
               }
		  
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
     
      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{

      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);

      // Process the uploaded file items
      Iterator i = fileItems.iterator();
 
     String filename=null;
     int pid=0;
      
      while ( i.hasNext())
      {
         FileItem fi = (FileItem)i.next();     
         if(fi.isFormField ())
         {
        	 String fieldName = fi.getFieldName();
        	 if(fieldName.equals("pid"))
             {
          	   pid=Integer.parseInt(fi.getString());
          	   System.out.println(pid);
             }
         }
        	 else 
        	 {	 String fieldName = fi.getFieldName();
	            if(fieldName.equals("file"))
	            {
	             ServletConfig sc=getServletConfig();
	             String field=fi.getString();
	             String contentType = fi.getContentType();
	             filename=fi.getName();
	              boolean isInMemory = fi.isInMemory();
	              long sizeInBytes = fi.getSize();
	
	              //create folder
	              File f = new File(sc.getServletContext().getRealPath("/")+"images/") ;
	                 if(!f.exists())
	                	 f.mkdir();
	              // Write the file
	              file = new File(sc.getServletContext().getRealPath("/")+"images/"+filename) ;
	               fi.write( file ) ;
	              out.println("Uploaded Filename: " +filename + "<br>");
	            System.out.println("PATH="+file.getPath());
            }
         }      
      }  
      try {
    	  DBConnector dbc=new DBConnector();
    	  Connection con=dbc.start();
    	  PreparedStatement ps=con.prepareStatement("update product set image=? where pid=?");//placeholder
    	    ps.setString(1,filename);
    	    ps.setInt(2,pid);
    	    int y=0;y=ps.executeUpdate();
    	    if(y!=0)

    	    {
    	    	ProductDao pd=new ProductDao();
    			ArrayList<ProductView> list=pd.viewProduct(pid);
    	    	RequestDispatcher rd=request.getRequestDispatcher("updateProduct.jsp");		//("UpdatedProductViewController");
    			request.setAttribute("LIST",list);
    			request.setAttribute("updateinfo","product "+pid+" updation successfull");
    			rd.forward(request, response);
    	    }
    	    else
    	    {
    	    	RequestDispatcher rd=request.getRequestDispatcher("updateProduct.jsp");
    			request.setAttribute("updateinfo","product "+pid+" updation failed");
    			rd.forward(request, response);
    	    }
      }catch(Exception e)
      {
    	  System.out.println(e);
      }

    }//}
      
      catch(Exception ex)
    {
        ex.printStackTrace();
       System.out.println(ex);
   }
      
	}	
}


