package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.net.ssl.ManagerFactoryParameters;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Manager;
import model.Manager_model;
import model.Status;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.Gson;

public class RootServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RootServlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String property = (String)request.getParameter("property");
		Status status = null;
		Gson gson = new Gson();
		
		
		HttpSession session = request.getSession();
		String type = (String) session.getAttribute("type");
		if(type==null||!type.equals("root")){
			session.invalidate();
			response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
			return;
		}
		
		
		
		
		if(property.equals("addManager")){
			Manager m = new Manager();
			m.setManager_account((String)request.getParameter("manager_account"));
			m.setManager_password((String)request.getParameter("manager_password"));
			m.setManager_name((String)request.getParameter("manager_name"));
			m.setManager_phone((String)request.getParameter("manager_phone"));
			m.setManager_tel((String)request.getParameter("manager_tel"));
			m.setManager_email((String)request.getParameter("manager_email"));
			m.setManager_gender(Integer.parseInt(request.getParameter("manager_gender")));
			
			if(m.getManager_name().equals("万方舟")||m.getManager_name().equalsIgnoreCase("wanfangzhou")){
				session.invalidate();
				response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
				return;
			}
			
			
			if(Manager_model.addManager(m)){
				status = new Status(true, "管理员添加成功");
			}else{
				status = new Status(false, "管理员添加失败");
			}
			out.println(gson.toJson(status));
		}
		
		else if(property.equals("isRepeat")){
			String manager_account = (String)request.getParameter("manager_account");
			 
			if(Manager_model.isRepeat(manager_account)){
				status = new Status(true, "用户名无效");
			}else{
				status = new Status(false, "用户名可用");
			}
			out.println(gson.toJson(status));
		}
		
		else if(property.equals("deleteManager")){
			int manager_id = Integer.parseInt(request.getParameter("manager_id"));
			
			if(Manager_model.deleteManager(manager_id)){
				status = new Status(true, "删除管理员成功");
			}else{
				status = new Status(false, "删除管理员失败");
			}
			out.println(gson.toJson(status));
		}
		
		else if(property.equals("updateManager")){
			int manager_id = Integer.parseInt(request.getParameter("manager_id"));
			
			Manager m = new Manager();
			m.setManager_account((String)request.getParameter("manager_account"));
			m.setManager_password((String)request.getParameter("manager_password"));
			m.setManager_name((String)request.getParameter("manager_name"));
			m.setManager_phone((String)request.getParameter("manager_phone"));
			m.setManager_tel((String)request.getParameter("manager_tel"));
			m.setManager_email((String)request.getParameter("manager_email"));
			m.setManager_gender(Integer.parseInt(request.getParameter("manager_gender")));
			
			if(Manager_model.updateManager(manager_id,m)){
				status = new Status(true, "更新管理员成功");
			}else{
				status = new Status(false, "更新管理员失败");
			}
			out.println(gson.toJson(status));
		}
		
		else if(property.equals("findManager")){
			
			List<Manager> lm= Manager_model.findManager();
			if(!lm.isEmpty()){
				status = new Status(true, gson.toJson(lm));
			}else{
				status = new Status(false, "该房住户为空");
			}
			out.println(gson.toJson(status));
		}
			
		out.close();
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
		
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
