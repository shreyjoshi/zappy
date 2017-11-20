package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import beans.Customer;
import beans.ProductView;
import dto.DBConnector;

public class CustomerDao {

	public CustomerDao() {
		// TODO Auto-generated constructor stub
	}
/*checks login of user*/
	public int checkLogin(String cid,String cpass)
	{
		int x=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
		
			PreparedStatement ps = con.prepareStatement("select * from customer where cid=? and cpass=?");
			ps.setString(1,cid);
			ps.setString(2, cpass);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				{
					x=1;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;	
	}
	/*this methods inserts the data in order table and deletes it from addtocart table*/
	public int buyNow(String user)//,int product)
	{
		int x=0,y,z;
		ArrayList<Customer> list=new ArrayList<>();
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select c.cid,c.cname,c.cmob,c.cadd,p.pid,p.pname,p.price,p.weight,p.details,p.image,p.quantity,p.totalamount,p.orderdate from addtocart p,customer c where p.cid=c.cid and p.cid=? ");
			ps.setString(1, user);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Customer c=new Customer();
				c.setPid(rs.getInt("pid"));
				c.setPname(rs.getString("pname"));
				c.setPrice(rs.getDouble("price"));
				c.setWeight(rs.getString("weight"));
				c.setDesc(rs.getString("details"));
				c.setImage(rs.getString("image"));
				c.setQuantity(rs.getInt("quantity"));
				c.setTa(rs.getDouble("totalamount"));
				c.setCid(rs.getString("cid"));
				c.setCname(rs.getString("cname"));
				c.setCmob(rs.getString("cmob"));
				c.setCadd(rs.getString("cadd"));
				c.setOrderdate(rs.getString("orderdate"));
//				c.setStatus(rs.getInt("status"));
				
				list.add(c);
			}
			for(Customer cc:list)
			{
				int status=0;
				PreparedStatement ps2=con.prepareStatement("insert into orders(pid,pname,price,weight,details,cid,cname,cmob,cadd,image,quantity,totalamount,status,orderdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				ps2.setInt(1,cc.getPid());
				ps2.setString(2,cc.getPname());
				ps2.setDouble(3,cc.getPrice());								//isme ek auto increament bhi leni hai
				ps2.setString(4,cc.getWeight());
				ps2.setString(5, cc.getDesc());
				ps2.setString(6,cc.getCid());
				ps2.setString(7, cc.getCname());
				ps2.setString(8, cc.getCmob());
				ps2.setString(9, cc.getCadd());
				ps2.setString(10,cc.getImage());
				ps2.setInt(11, cc.getQuantity());
				ps2.setDouble(12,cc.getTa());
				ps2.setInt(13,status);
				ps2.setString(14, cc.getOrderdate());
				PreparedStatement ps3=con.prepareStatement("delete from addtocart where cid=?");
				ps3.setString(1, cc.getCid());
				 con.setAutoCommit(false);
				 y=ps2.executeUpdate();
				 z=ps3.executeUpdate();
				 con.commit(); 
				 if(y!=0 && z!=0)
						x=1;
			 }
			
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return x;
	}
/*deletes the customer*/	
	public int deleteCustomer(String cid) {
		int y=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("delete from customer where cid=?");
			ps.setString(1,cid);
			
			y=ps.executeUpdate();
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return y;
	}
	/*inserts customer data when a new customer signs up*/
	public int insertCustomer(Customer c)
	 {int x=0;
		 try 
		 {
		 DBConnector dbc=new DBConnector();
		 Connection con=dbc.start();
		 
		 PreparedStatement ps=con.prepareStatement("insert into customer values(?,?,?,?,?)");
		 	ps.setString(1,c.getCid());	
		 	ps.setString(2,c.getCname());
		 	ps.setString(3,c.getCpass());
			ps.setString(4,c.getCmob());
			ps.setString(5,c.getCadd());
			x= ps.executeUpdate();
		
		 }catch(SQLException ex)
		 {
			 System.out.println(ex);
		 }
		 return x;
	}
	
	/*it will display all the customers to admin*/
	public ArrayList<Customer> viewAllCustomer()
	{
		ArrayList<Customer> list=new ArrayList<>();
		//int x=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from customer");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{	Customer c=new Customer();
				c.setCid(rs.getString("cid"));
				c.setCname(rs.getString("cname"));
				c.setCpass(rs.getString("cpass"));
				c.setCmob(rs.getString("cmob"));
				c.setCadd(rs.getString("cadd"));
				list.add(c);
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;
	}
	/*shows the orders previously placed by any customer*/
	public ArrayList<Customer> viewOrderHistory(String cid)
	{
		ArrayList<Customer> list=new ArrayList<>();
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from orders where cid=?");
			ps.setString(1, cid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Customer c=new Customer();
				c.setOrderid(rs.getInt("orderid"));
				c.setPid(rs.getInt("pid"));
				c.setPname(rs.getString("pname"));
				c.setPrice(rs.getDouble("price"));
				c.setWeight(rs.getString("weight"));
				c.setDesc(rs.getString("details"));
				c.setImage(rs.getString("image"));
				c.setCid(rs.getString("cid"));
				c.setCname(rs.getString("cname"));
				c.setCmob(rs.getString("cmob"));
				c.setCadd(rs.getString("cadd"));
				c.setQuantity(rs.getInt("quantity"));
				c.setTa(rs.getDouble("totalamount"));
				c.setStatus(rs.getInt("status"));
				c.setOrderdate(rs.getString("orderdate"));;
				list.add(c);
			}
		
	}catch(SQLException e)
		{
		System.out.println(e+" cant view orders");
		}
		return list;
}
	/*select all the orders with status =0*/
	public ArrayList<Customer> viewTodayOrder()
	{
		ArrayList<Customer> list=new ArrayList<>();
		//int x=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from orders where status=0");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Customer c=new Customer();
				c.setOrderid(rs.getInt("orderid"));
				c.setPid(rs.getInt("pid"));
				c.setPname(rs.getString("pname"));
				c.setPrice(rs.getDouble("price"));
				c.setWeight(rs.getString("weight"));
				c.setDesc(rs.getString("details"));
				c.setImage(rs.getString("image"));
				c.setCid(rs.getString("cid"));
				c.setCname(rs.getString("cname"));
				c.setCmob(rs.getString("cmob"));
				c.setCadd(rs.getString("cadd"));
				c.setQuantity(rs.getInt("quantity"));
				c.setTa(rs.getDouble("totalamount"));
				c.setStatus(rs.getInt("status"));
				c.setOrderdate(rs.getString("orderdate"));
				list.add(c);
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;
	}
	/*updates the order table set status =1 for a orderid*/
	public int dispatchOrder(String cid,int pid,int orderid)
	{
		int x=0,y=1;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("update orders set status=? where cid=? and orderid=?");
			ps.setInt(1,y);
			ps.setString(2,cid);
			ps.setInt(3, orderid);
			x=ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
		return x;
	}
/*returns the password of a particular user*/	
	public String getPassword(String cid)
	{
		String pass=null;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select cpass from customer where cid=?");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				pass=rs.getString("cpass");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return pass;
	}
	
	public TreeMap<String,String> getCartValue(String cid) {
		Customer c=null;
		TreeMap<String,String> tm=null;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select sum(quant)as cquant,sum(totalamount) as sumamount from addtocart where cid=? ");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				tm=new TreeMap<>();
				tm.put("cquant",rs.getString("cquant"));
				tm.put("sumamount",rs.getString("sumamount"));
			}
			
		}catch(SQLException e) {System.out.println(e);}
		return tm;
	}

	/*selects all the details of a customer*/
	public ArrayList<Customer> viewProfile(String cid){
		ArrayList<Customer> list=new ArrayList<>();
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from customer where cid=?");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Customer c=new Customer();
				c.setCid(rs.getString("cid"));
				c.setCname(rs.getString("cname"));
				c.setCadd(rs.getString("cadd"));
				c.setCmob(rs.getString("cmob"));
				c.setCpass(rs.getString("cpass"));
				list.add(c);
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;
	}

	/*updates the data of a customer*/
	public int updateCustomerData(Customer c) {
		int x=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("update customer set cid=?,cname=?,cpass=?,cmob=?,cadd=? where cid=?");
			ps.setString(1,c.getCid());	
		 	ps.setString(2,c.getCname());
		 	ps.setString(3,c.getCpass());
			ps.setString(4,c.getCmob());
			ps.setString(5,c.getCadd());
			ps.setString(6,c.getCid());
			x=ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
		return x;
	}
/*deletes the order from order table upon cancellation*/	
	public int cancelOrder(int orderid) {
		int x=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("delete from orders where orderid=?");
			ps.setInt(1,orderid);	
			x=ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
		return x;
	}
	
	/**/
	public int checkMyOrder(String userid,int pid) {
		int x=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select pid from orders where cid=? and pid=?");
			ps.setString(1,userid);	
			ps.setInt(2,pid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				
			if(rs.getInt("pid")==pid) {
				x=1;
			}
				}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return x;
	}
	
	/**/
	public int verifyEmail(String cid) {
		int x=0;
		//System.out.println("cid in dao "+cid);
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select cid from customer");
			//ps.setString(1,cid);	
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("cid").equalsIgnoreCase(cid)) {
					x=1;
				}
				}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return x;
	}
	
	/**/
	public int addReview(int pid,String cid,String review,double rating) {
		int x=0;
		try 
		{			//System.out.println(rating);
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("insert into reviews(pid,cid,review,rating) values(?,?,?,?)");
			ps.setInt(1, pid);
			ps.setString(2,cid);
			ps.setString(3,review);
			ps.setDouble(4,rating);
			x=ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
		return x;
	}
	/**/
	public ArrayList<Customer> loadReview(int pid){
		ArrayList<Customer> list=new ArrayList<>();
		try 
		{
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from reviews where pid=?");
			ps.setInt(1, pid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Customer c=new Customer();
				c.setReviewid(rs.getInt("reviewid"));
				c.setPid(rs.getInt("pid"));
				c.setCid(rs.getString("cid"));
				c.setReview(rs.getString("review"));
				c.setRating(rs.getDouble("rating"));
				list.add(c);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	public void updateIpaddressInCart(String ipadd, String cid) {

		try 
		{
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("update addtocart set cid=? where cid=?");
			ps.setString(1, cid);
			ps.setString(2, ipadd);
			ps.executeUpdate();			
		}catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}
