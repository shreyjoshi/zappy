package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Customer;
import beans.ProductView;
import dto.DBConnector;

public class ProductDao {
	
	/*returns all the details of all products*/
	public ArrayList<ProductView> viewProduct()
	{
		ArrayList<ProductView> list=new ArrayList<>();
		
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps1=con.prepareStatement("select * from product");
//	     	PreparedStatement ps=con.prepareStatement("select * from product");
			ResultSet rs=ps1.executeQuery();
			
			while(rs.next())
			{
				ProductView pv=new ProductView();
				pv.setPname(rs.getString("pname"));
				pv.setPrice(rs.getDouble("price"));
				pv.setWeight(rs.getString("weight"));
				pv.setDesc(rs.getString("details"));
				pv.setImage(rs.getString("image"));
				pv.setPid(rs.getInt("pid"));
				list.add(pv);
		}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;
	}
	

	/*returns details of a specific product*/
	public ArrayList<ProductView> viewProduct(int pid)
	{
		ArrayList<ProductView> list=new ArrayList<>();
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from product where pid=?");
			ps.setInt(1,pid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ProductView pv=new ProductView();
				pv.setPname(rs.getString("pname"));
				pv.setPrice(rs.getDouble("price"));
				pv.setWeight(rs.getString("weight"));
				pv.setDesc(rs.getString("details"));
				pv.setImage(rs.getString("image"));
				pv.setPid(rs.getInt("pid"));
				list.add(pv);	
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;		
	}

	
/*deletes any product from product lists*/	
	
public int deleteProduct(int p) {
		int y=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("delete from product where pid=?");
			ps.setInt(1,p);
			
			y=ps.executeUpdate();
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return y;
	}

/*deletes a product from cart*/
public int deleteCartProduct(String cid,int addtocartid) {
	int y=0;
	try 
	{
		DBConnector dbc=new DBConnector();
		Connection con=dbc.start();
		PreparedStatement ps=con.prepareStatement("delete from addtocart where cid=? and addtocartid=?");
		ps.setString(1, cid);
		ps.setInt(2,addtocartid);
		y=ps.executeUpdate();
	}catch(Exception e)
	{
		System.out.println(e);
	}
	return y;
}
	
/*updates product data from admin*/
	public int updateData(int pid,String pname,double price,String pweight,String pdesc)
	{
		int x=0;
		DBConnector dsc=new DBConnector();
		Connection con=dsc.start();
		try {
			PreparedStatement ps=con.prepareStatement("update product set pname=?,price=?,weight=?,details=? where pid=?");
			ps.setString(1, pname);
			ps.setDouble(2,price);
			ps.setString(3,pweight);
			ps.setString(4, pdesc);
			ps.setInt(5, pid);
			x=ps.executeUpdate();
//			System.out.println(x+" in update data");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
		
	}

	/*enables customer to add any product to cart*/
	public int addToCart(int pid,String u,int quantity,double totalamount,String date)
	{
		Customer c=new Customer();
		int x=0, aa=0,bb=0,cc=0;
		double dd=0.0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			//System.out.println("testttt   111");
			PreparedStatement ps10=con.prepareStatement("select totalamount,quantity,addtocartid,pid from addtocart where pid=? and cid=?");
			ps10.setInt(1,pid);
			ps10.setString(2,u);
			ResultSet rs10=ps10.executeQuery();
			while(rs10.next()) 
			{
			 aa=rs10.getInt("pid");
			 bb=rs10.getInt("addtocartid");
			 cc=rs10.getInt("quantity")+quantity;
			 dd=rs10.getDouble("totalamount")+totalamount;
			}
			con.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		try 
		{	
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			if(aa==pid) 
			{
				PreparedStatement psttt=con.prepareStatement("update addtocart set quantity=?,totalamount=?,orderdate=? where addtocartid=?");
				psttt.setInt(1,cc);
				psttt.setDouble(2, dd);
				psttt.setString(3,date);
				psttt.setInt(4,bb);
				int y=psttt.executeUpdate();				
				if(y>0)
					{
						x=1;
					}				
			}
			else
			{
			PreparedStatement ps1=con.prepareStatement("select * from product where pid=?");
			ps1.setInt(1,pid);
			ResultSet rs=ps1.executeQuery();
			while(rs.next())
			{
				
				c.setPid(rs.getInt("pid"));
				c.setPname(rs.getString("pname"));
			//	System.out.println(rs.getString("pname"));
				c.setPrice(rs.getDouble("price"));
				c.setWeight(rs.getString("weight"));
				c.setDesc(rs.getString("details"));
				c.setImage(rs.getString("image"));
			}
				
			PreparedStatement ps2=con.prepareStatement("insert into addtocart(pid,pname,price,weight,details,quantity,totalamount,cid,image,orderdate) values(?,?,?,?,?,?,?,?,?,?)");
//			Customer c=new Customer();
			ps2.setInt(1,c.getPid());
			ps2.setString(2,c.getPname());
			
//			System.out.println(c.getPname()+" in product dao"); // ek auto increament value leni hai
					
			ps2.setDouble(3,c.getPrice());
			ps2.setString(4,c.getWeight());
			ps2.setString(5,c.getDesc());
			ps2.setInt(6, quantity);
			ps2.setDouble(7, totalamount);
			ps2.setString(8, u);
			ps2.setString(9,c.getImage());
			ps2.setString(10, date);			//isko database me insert karna hai ....
			x=ps2.executeUpdate();
//			System.out.println("value of x in addtocart dao"+x)
			con.close();
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return x;
	}
//--------------------------------------------------------------------
//--------------------------------------------------------------------
//--------------------------------------------------------------------
	
	/*returns the total cart value of any user */
	public ArrayList<Customer> cartValue(String cid)
	{
		ArrayList<Customer> list1=new ArrayList<>();
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps3=con.prepareStatement("select cid, count(quantity) as cquant,sum(totalamount) as sumamount from addtocart where cid=?");
			ps3.setString(1,cid);
			ResultSet rs3=ps3.executeQuery();
			while(rs3.next())
			{
				Customer c=new Customer();
				c.setCid(rs3.getString("cid"));
				c.setCquant(rs3.getInt("cquant"));
				c.setSumamount(rs3.getDouble("sumamount"));
				list1.add(c);
				//System.out.println(rs3.getInt("cquant")+" total quantity "+rs3.getDouble("sumamount"));
			}
			
		}catch(SQLException e)
		{System.out.println(e);}
		return list1;
	}
	
//--------------------------------------------------------------------
//--------------------------------------------------------------------
//--------------------------------------------------------------------
	/*returns the products within cart of any user*/
	public ArrayList<Customer> viewProductInCart(String user)
	{
		ArrayList<Customer> list=new ArrayList<>();
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps2=con.prepareStatement("select * from addtocart where cid=?");
			ps2.setString(1,user);
			ResultSet rs=ps2.executeQuery();
			while(rs.next())
			{
				Customer c=new Customer();
				c.setPid(rs.getInt("pid"));
				c.setPname(rs.getString("pname"));
				c.setPrice(rs.getDouble("price"));
				c.setWeight(rs.getString("weight"));
				c.setDesc(rs.getString("details"));
				c.setImage(rs.getString("image"));
				c.setCid(rs.getString("cid"));
				c.setQuantity(rs.getInt("quantity"));
				c.setTa(rs.getDouble("totalamount"));
				c.setAddtocartid(rs.getInt("addtocartid"));
				c.setOrderdate(rs.getString("orderdate"));
				//System.out.println(c.getPid()+" this is pid in product dao customerrrrrrrr");
				list.add(c);
				
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;	
	}

/*returns any specific product information */
	public ArrayList<ProductView> getProductInfo(int pid) {
	
		ArrayList<ProductView> list=new ArrayList<>();
		try {
				DBConnector dbc=new DBConnector();
				Connection con=dbc.start();
				PreparedStatement ps=con.prepareStatement("select * from product where pid=?");
				ps.setInt(1,pid);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					ProductView pv=new ProductView();
					pv.setPid(rs.getInt("pid"));
					pv.setPname(rs.getString("pname"));
					pv.setPrice(rs.getDouble("price"));
					pv.setWeight(rs.getString("weight"));
					pv.setDesc(rs.getString("details"));
					pv.setImage(rs.getString("image"));
					list.add(pv);
				}	
			}catch(SQLException e) {System.out.println(e);}
			return list;
	}
	
//	public ArrayList<Customer> viewCartProduct(int pid,String cid)
//	{
//		ArrayList<Customer> list=new ArrayList<>();
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps2=con.prepareStatement("select * from addtocart where cid=? and pid=?");
//			ps2.setString(1,cid);
//			ps2.setInt(2, pid);
//			ResultSet rs=ps2.executeQuery();
//			while(rs.next())
//			{
//				Customer c=new Customer();
//				c.setPid(rs.getInt("pid"));
//				c.setPname(rs.getString("pname"));
//				c.setPrice(rs.getDouble("price"));
//				c.setWeight(rs.getString("weight"));
//				c.setDesc(rs.getString("details"));
////				c.setImage(rs.getString("image"));
//				c.setCid(rs.getString("cid"));
//				System.out.println(c.getPid()+" in view cart product");
//				list.add(c);
//				
//			}
//		}catch(SQLException e)
//		{
//			System.out.println(e);
//		}
//		return list;	
//	}
}
