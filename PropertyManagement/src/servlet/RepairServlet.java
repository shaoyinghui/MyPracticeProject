package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.House;
import model.Repair_report;
import model.Repair_report_model;
import model.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RepairServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RepairServlet() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();  
        
        String name = request.getParameter("repairer_name");

        
        
        
        HttpSession session = request.getSession();
        String property = (String)request.getParameter("property");
        String type = (String)session.getAttribute("type");
        
        
  
        if(type==null){
			session.invalidate();
			response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
			return;
		}
       
        
		Status status = null;
        
		
		
		Gson gson = new Gson();
		Gson gson_repair=new GsonBuilder().registerTypeAdapter(Repair_report.class, new JsonSerializer<Repair_report>() {
            public JsonElement serialize(Repair_report  src, Type typeOfSrc,
                    JsonSerializationContext context) {
                JsonObject o=new JsonObject();
                o.addProperty("repair_report_id", src.getRepair_report_id());
                o.addProperty("house_id", src.getHouse().getHouse_id());
                o.addProperty("repair_description", src.getRepair_description());    
                if(src.getApply_time()!=null)
                	 o.addProperty("apply_time",(src.getApply_time().toString()).split(" ")[0]);
                else
                	 o.addProperty("apply_time", "未知错误");
                o.addProperty("repairer_name", src.getRepairer_name());
                o.addProperty("repairer_phone", src.getRepairer_phone());
                o.addProperty("repair_result", src.getRepair_result());
                o.addProperty("repair_fee", src.getRepair_fee());
         
                if(src.getRepair_time()!=null)
               	 o.addProperty("repair_time",src.getRepair_time().toString().split(" ")[0]);
               else
               	 o.addProperty("repair_time", "待处理");
                return o;
            }
        }).create();
		
		
		
		
		
     
		
        if(type.equals("owner")){
        	if(property.equals("addRepair")){
            	int account_id = (Integer) session.getAttribute("account_id");
            	String repair_description = (String)request.getParameter("repair_description");
            	Repair_report r = Repair_report_model.addRepair(account_id,repair_description,null,null);
            	
            	if(r!=null){
            		status = new Status(true,gson_repair.toJson(r));
            	}else{
            		status = new Status(false,"申请出错");
            	}
            	out.println(gson.toJson(status));
        	}
        	
        	
        	
        	else if(property.equals("getRepair")){
            	int account_id = (Integer) session.getAttribute("account_id");
        		
            	List<Repair_report> lr =  Repair_report_model.getRepair(account_id); 
            	
            	if(!lr.isEmpty()){
            		status = new Status(true, gson_repair.toJson(lr));
            	}else{
            		status = new Status(false, "用户没有提交维修申请");
            	}
            	out.println(gson.toJson(status));            	
            }
        	else if(property.equals("getAllRepair")){
            	int flag=Integer.parseInt(request.getParameter("flag"));
            	List<Repair_report> lr =  Repair_report_model.getAllRepair(flag);
            	
            	if(!lr.isEmpty()){
            		status = new Status(true, gson_repair.toJson(lr));
            	}else{
            		status = new Status(false, "没有维修申请");
            	}
            	out.println(gson.toJson(status));
            }     	     	
        }
        
        
        
        
        else if(type.equals("manager")){
        	if(property.equals("getRepair")){
        			House h = new House();
        			h.setHouse_building(request.getParameter("house_building"));
        			h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
        			h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
        			h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
        			
        			List<Repair_report> lr = Repair_report_model.getRepair(h);
        		
        			if(!lr.isEmpty()){
                		status = new Status(true,gson_repair.toJson(lr));
                	}else{
                		status = new Status(false, "没有维修申请");
                	}
                	out.println(gson.toJson(status));	
        		
        	}
        	if(property.equals("getAllRepair")){   		
        		int flag=Integer.parseInt(request.getParameter("flag"));
            	List<Repair_report> lr =  Repair_report_model.getAllRepair(flag);
            	
            	if(!lr.isEmpty()){
            		status = new Status(true,gson_repair.toJson(lr));
            	}else{
            		status = new Status(false, "没有维修申请");
            	}
            	out.println(gson.toJson(status));	
        	}else if(property.equals("deleteRepair")){
        		int repair_report_id=Integer.parseInt(request.getParameter("repair_report_id"));
        		if(Repair_report_model.deleteRepair(repair_report_id)){
        			status = new Status(true, "删除维护成功");
        		}else{
        			status = new Status(false,"删除维护失败");
        		}
        		out.println(gson.toJson(status));
        	}else if(property.equals("setRepairPlan")){
        		 int repair_report_id = Integer.parseInt(request.getParameter("repair_report_id"));
        		 String repairer_name 	 	=  request.getParameter("repairer_name");
				 String repairer_phone	    =  request.getParameter("repairer_phone");
				 if(Repair_report_model.setRepairPlan(repair_report_id,repairer_name,repairer_phone)){
	        			status = new Status(true, "设置维护计划成功");
	        		}else{
	        			status = new Status(false,"设置维护计划失败");
	        		}
	        		out.println(gson.toJson(status));			
        	}else if(property.equals("setRepairResult")){
        		int repair_report_id = Integer.parseInt(request.getParameter("repair_report_id"));
       		 	String Result 	 	 =  (String)request.getParameter("repair_result");
       		 	float repair_fee = Float.parseFloat(request.getParameter("repair_fee"));
       		 	if(Repair_report_model.setRepairResult(repair_report_id,Result,repair_fee)){
       		 		status = new Status(true, "设置维护结果成功");
       		 	}else{
       		 		status = new Status(false,"设置维护结果失败");
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
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=UTF-8"); 	
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
