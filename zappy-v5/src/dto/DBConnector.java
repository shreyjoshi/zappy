package dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
/*returns the connection of database*/
	public Connection start()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/zappy","root","");		//"jdbc:mysql://localhost:3306/zappy","root",""
		}
		catch(SQLException | ClassNotFoundException e)
		{
			System.out.println(e);	
		}
		return con;
	}

}
