package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.spi.LoginModule;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Manager_model;
import model.Owner_account_model;
import model.Owner_model;
import model.Root_model;
import model.Status;

import com.google.gson.Gson;


public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
        		 
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();  
        String property = (String)request.getParameter("property");
        String type = (String)request.getParameter("type");
        Gson gson = new Gson();
        
    	
        
        
        
        
		
      //Login
        if(property.equals("login")){
        	String username = (String)request.getParameter("username");
        	String password = (String)request.getParameter("password");
        	Status status = null;
        	   //管理登陆
        		if(type.equals("manager")){
        			 int manager_id = Manager_model.isManager(username,password);
        			 if(manager_id==-1)
        				 status = new Status(false, "用户名或密码错误");
        			 else {
        				 HttpSession session = request.getSession();
        				 session.setAttribute("manager_id",manager_id);
        				 session.setAttribute("type","manager");
        				 status = new Status(true, "manage_vehicle.jsp");
					}
        			 out.println(gson.toJson(status));
        		}
        		//住户登陆
        		else if (type.equals("owner")){
        			 int account_id = Owner_account_model.isOwner_account(username,password);
        			 if(account_id==-1)
        				 status = new Status(false, "用户名或密码错误");
        			 else {
        				 HttpSession session = request.getSession();
        				 session.setAttribute("account_id",account_id);
        				 session.setAttribute("type","owner");
        				 status = new Status(true, "owner_notice.jsp");
        				 System.out.println("account_id"+account_id);
					}
        			 out.println(gson.toJson(status));					
				}
        		//root登录
        		else if (type.equals("root")){
        			int root_id = Root_model.isRoot(username,password);
        			if(root_id==-1)
       				 	status = new Status(false, "用户名或密码错误");
       			 	else {
       			 		HttpSession session = request.getSession();
       			 		session.setAttribute("root_id",root_id);
       			 		session.setAttribute("type","root");
       			 		status = new Status(true, "登陆成功");
       			 		}
       			 	out.println(gson.toJson(status));					
					}
        		}
        
        
        
        		
        		else if(property.equals("exit")){
        				HttpSession session = request.getSession(false);		//防止创建Session  
        		        if(session == null){  
        		            response.sendRedirect("../index.jsp");  
        		            return;  
        		        }  
        		        session.invalidate();
        		        response.sendRedirect("../index.jsp");
        		        return;
               }
        out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
