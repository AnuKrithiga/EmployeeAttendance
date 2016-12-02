import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Validate {
	
	public static boolean vali(String tag,String stat,int t,String d){
		//System.out.print(tag);
		boolean status=false;
		boolean statCheck=false;
		boolean timeCheck=false;
		final String DATE_FORMAT = "yyyy/MM/dd";
		boolean dateCheck=false;
		boolean res=false;
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","aaaa");
		PreparedStatement ps=con.prepareStatement("select * from empName where tag=?");
		ps.setString(1,tag);
		ResultSet rs=ps.executeQuery();
		status=rs.next();
		//System.out.println(stat);
		statCheck=((stat.equalsIgnoreCase("I"))||(stat.equalsIgnoreCase("O")));
		if (t>0000&&t<235959){timeCheck=true;}
		else{timeCheck=false;}
		
		 try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(d);
	            dateCheck=true;
	        } catch (ParseException e) {
	            dateCheck=false;
	        }
		res=status && statCheck && timeCheck && dateCheck;
		System.out.println(status+"::"+statCheck+"::"+timeCheck+"::"+dateCheck);
			
		}
		catch(Exception e){
			System.out.println("anu:::"+e);
		}
		return res;
	}

}
