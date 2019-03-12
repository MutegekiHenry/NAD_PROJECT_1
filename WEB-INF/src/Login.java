package loginpackage;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if(checkUser(email,password))
        {
            // writer.println("DOPE YOU ARE A VALID USER");
            RequestDispatcher rs = request.getRequestDispatcher("welcome.html");
            rs.include(request,response);
        }
        else
        {
           writer.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("login.html");
           rs.include(request,response);
        }
    }  

//VALIDATING A USER
    public static boolean checkUser(String email,String password) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");

      //creating connection with the database 
      String urll = "jdbc:mysql://localhost:3306/test"; String unam = "root"; String pas ="";  
      Connection  con=DriverManager.getConnection(urll,unam,pas);
      PreparedStatement ps =con.prepareStatement("select * from student where email=? and password=?");
         ps.setString(1,email);
         ps.setString(2,password);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }   
}