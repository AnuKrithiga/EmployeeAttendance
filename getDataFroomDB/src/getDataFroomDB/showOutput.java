package getDataFroomDB;



import getDataFroomDB.check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/goo")
public class showOutput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		String name=request.getParameter("name");
		if(check.verify(name)){
			RequestDispatcher rd=request.getRequestDispatcher("WelcmServ");
			rd.forward(request,response);
		}
		else{
			out.print("give a registered employee name !!!!");
			RequestDispatcher rd=request.getRequestDispatcher("pageOne.html");
			rd.include(request,response);
		}
		
		out.close();
	}

}
