package getDataFroomDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class check {

	public static boolean verify(String n)
	{
		boolean status=false;
		System.out.println(n);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","aaaa");
			PreparedStatement ps=con.prepareStatement("select * from empName where name=?");
			ps.setString(1,n);
			ResultSet rs=ps.executeQuery();
			status = rs.next();
			System.out.println(status);
			}
		catch(Exception e){System.out.println(e);}
		return status;
	}
}
