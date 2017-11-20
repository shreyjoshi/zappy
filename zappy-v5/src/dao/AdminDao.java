package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Customer;
import dto.DBConnector;

public class AdminDao{
/* checks the login of admin*/
		public int check(String u,String p)
		{
			int x=0;
			try 
			{
				DBConnector dbc=new DBConnector();
				Connection con=dbc.start();
				PreparedStatement ps=con.prepareStatement("select * from admin where aid=? and apass=?");
				ps.setString(1,u);
				ps.setString(2,p);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					x=1;
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return x;
		}
	/*gets the password of a user */	
		public String getPassword(String userid)
		{
			String pass="";
			try {
				DBConnector dbc=new DBConnector();
				Connection con=dbc.start();
				PreparedStatement ps=con.prepareStatement("select password from admin where email=?");
				ps.setString(1, userid);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					pass=rs.getString("password");
				}
			}catch(SQLException e) {
				System.out.println(e);
			}
			return pass;
		}
	/*changes the password of the user*/	
		public int changePassword(String cid,String newpassword,String oldpassword) {
			int x=0;
			try {
				DBConnector dbc=new DBConnector();
				Connection con=dbc.start();
				PreparedStatement ps=con.prepareStatement("update admin set apass=? where aid=? and apass=?");
				ps.setString(1,newpassword);	
				ps.setString(2, cid);
				ps.setString(3,oldpassword);
				x=ps.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e);
			}
			return x;
		}
	/*returns the list of all the orders where status is ordered*/	
		public ArrayList<Customer> viewOrderHistory()
		{
			int y=1;
			ArrayList<Customer> list=new ArrayList<>();
			try {
				DBConnector dbc=new DBConnector();
				Connection con=dbc.start();
				PreparedStatement ps=con.prepareStatement("select * from orders where status=?");
				ps.setInt(1, y);
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
	}


