package jdbcpackage;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Register extends HttpServlet{

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;");
     
	
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
       

        PrintWriter writer = response.getWriter();
        writer.println("Thank you, " +username+ ". You are sucessfully registered. You can now login");

        
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

  //creating connection with the database
  String urll = "jdbc:mysql://localhost:3306/test"; String unam = "root"; String ps ="";  
  Connection  con=DriverManager.getConnection(urll,unam,ps);

        

         String qry = "insert into student values('"+(username)+"','"+(email)+"','"+(password)+"','"+(firstname)+"','"+(lastname)+"','"+(address)+"','"+(phonenumber)+"')";
         Statement st=con.createStatement();
         st.executeUpdate(qry); 
         RequestDispatcher rs = request.getRequestDispatcher("login.html");
         rs.include(request,response);

        int i=st.executeUpdate(qry);
        
          if(i>0)
          {
 
            writer.println("<html><body><p>");
            writer.println("Thank you, " +username+ ". You are sucessfully registered.");
            writer.println("</p></body></html>");
            writer.close(); 
            
          }else{
            writer.println("<html><body><p>");
            writer.println("Failed to execute update !!.");
            writer.println("</p></body></html>");
            writer.close();  
          }
          con.close();
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
  }
  