import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class FirstIn {

	
		public static boolean inCheck(String tag,String stat,String date){
			
			boolean status=true;
			int t;

			try{
				if(stat.equalsIgnoreCase("I")){
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","aaaa");
				PreparedStatement ps=con.prepareStatement("select intime from empTime where tag=? and date=?");
				ps.setString(1, tag);
				ps.setString(2, date);

				ResultSet rs=ps.executeQuery();
				//status=rs.next();
				while(rs.next())  {
					t=rs.getInt(1);
					System.out.print(t);
					if((t>0)||(t<235959)){
						status=false;
					}
				}
				}
				
				}
			catch(Exception e){
				System.out.println("anu:::"+e);
			    }
			System.out.println("**"+status+"**");
			return (status);
				
		}
	

}