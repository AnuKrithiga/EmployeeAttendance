package getDataFroomDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/WelcmServ")
public class WelcmServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("name");
		out.println("<h1>Welcome "+n+"</h1>");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","aaaa");
			PreparedStatement ps=con.prepareStatement("SELECT empTime.intime,empTime.outime,empTime.date FROM empTime INNER JOIN empName ON empTime.tag=empName.tag where name=?");
			ps.setString(1,n);
			out.println("<table border=1 width=25% height=25%>");
			out.println("<tr><th>In Time</th><th>Out Time</th><th>Date</th><tr>");

			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String inTime=rs.getString(1);
				String outTime=rs.getString(2);
				String date=rs.getString(3);
			out.println("<tr><td>" + inTime + "</td><td>"+ outTime +"</td><td>"+ date +"</td></tr>" );
			
			}
			rs.close();
			con.close();
		}
		
			catch(Exception e){System.out.println(e);}
			
			out.close();
	
		
		
		
		
	}

}
