package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Notice;
import model.Notice_model;
import model.Status;

public class NoticeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NoticeServlet() {
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
		 Gson gson = new Gson();
		
		if(property.equals("getNotice")){
			Status status = null;
			int flag=Integer.parseInt(request.getParameter("flag"));
			String notice_type = (String)request.getParameter("notice_type");
			List<Notice> an = Notice_model.getNotice(notice_type,flag);
			
			
			if(!an.isEmpty()){
				status = new Status(true, gson.toJson(an));
			}else{
				status = new Status(false, "还没有公告");
			}
			out.println(gson.toJson(status));
		}
		

		
		
		
		else if(property.equals("selectNotice")){
				
			Status status =null;
			String notice_type = request.getParameter("notice_type");
			String dateon = request.getParameter("dateon");
			String dateoff = request.getParameter("dateoff");
			
			List<Notice> ln = Notice_model.selectNotice(notice_type, dateon, dateoff);
			
			if(!ln.isEmpty()){
				status = new Status(true, gson.toJson(ln));
			}else{
				status = new Status(false, "没有符合条件的公告");
			}
			out.println(gson.toJson(status));
			
		}
		
		
		

	
		HttpSession session = request.getSession();
		String type  = (String) session.getAttribute("type");
		
		if(type==null){
			session.invalidate();
			response.sendRedirect("http://localhost:8080/PropertyManagement/index.html");
			return;
		}
		
		
		if(type.equals("manager")){
		
		
			Status status =null;
			 if(property.equals("addNotice")){
				 String notice_type = (String)request.getParameter("notice_type");
				 String title = (String)request.getParameter("notice_title");
				 String content = (String)request.getParameter("notice_content");
				 if(Notice_model.addNotice(notice_type,title,content)){
					 status = new Status(true, "添加Notice成功");
					 response.sendRedirect("http://localhost:8080/PropertyManagement/manage_notice.html");
					 return;
				 }else{
					 status = new Status(false,"添加Notice失败");
					 out.println(gson.toJson(status));
				 }
				 //out.println(gson.toJson(status));
			 }else if(property.equals("updateNotice")){
				 int notice_id =Integer.parseInt(request.getParameter("notice_id"));
				 String title = (String)request.getParameter("notice_title");
				 String content = (String)request.getParameter("notice_content");
				 if(Notice_model.updateNotice(notice_id,title,content)){
					 status = new Status(true, "success");
				 }else{
					 status = new Status(false,"更新Notice失败");
				 }
				 out.println(gson.toJson(status));	
				 
			 }else if(property.equals("deleteNotice")){
				 int notice_id =Integer.parseInt(request.getParameter("notice_id"));
				 if(Notice_model.deleteNotice(notice_id)){
					 status = new Status(true, "删除Notice成功");
				 }else{
					 status = new Status(false,"删除Notice失败");
				 }
				 out.println(gson.toJson(status));	
			 }
			
				
			
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
