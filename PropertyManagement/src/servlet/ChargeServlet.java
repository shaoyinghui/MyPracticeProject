package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import model.Charge_model;
import model.Estate_fee;
import model.House;
import model.Status;
import model.Unit_price;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ChargeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChargeServlet() {
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
		Status status = null;
		
		 HttpSession session = request.getSession();
		 String type = (String)session.getAttribute("type");
	      
	        
	       if(type==null){
				session.invalidate();
				response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
				return;
			}  
	       
	if(type.equals("manager")){
			if(property.equals("setUnit_price")){
				float unit_price = Float.parseFloat(request.getParameter("unit_price"));
				
				if(Charge_model.setUnit_price(unit_price)){
					status = new Status(true,"物业费添加成功");
				}else{
					status = new Status(false,"物业费添加失败");
				}
				out.println(gson.toJson(status));
		}
		
	}
		
		 if(property.equals("getEstate_fee")){
				
				int account_id = (Integer) session.getAttribute("account_id");
	
				float estate_fee = Charge_model.getEstate_fee(account_id);
				
				status = new Status(true, gson.toJson(estate_fee));
				out.println(gson.toJson(status));
		}
			
			
			
			else if(property.equals("getHistoryEstate")){
				int account_id = (Integer) session.getAttribute("account_id");
				List<Estate_fee>  le =  Charge_model.getHistoryEstate(account_id);
				
				Gson gson_Estate=new GsonBuilder().registerTypeAdapter(Estate_fee.class, new JsonSerializer<Estate_fee>() {
		            public JsonElement serialize(Estate_fee  src, Type typeOfSrc,
		                    JsonSerializationContext context) {
		                JsonObject o=new JsonObject();
		                o.addProperty("Estate_fee_id", src.getEstate_fee_id());
		                o.addProperty("Estate_fee", src.getEstate_fee());            
		                if(src.getEstate_fee_date()!=null)
		                	o.addProperty("Estate_fee_date",src.getEstate_fee_date().toString());
		                else
		                	o.addProperty("Estate_fee_date", "未记录");
		                return o;
		            }
		        }).create();

				
				if(!le.isEmpty()){
					status = new Status(true, gson_Estate.toJson(le));
				}else{
					status = new Status(false, "没有历史记录");
				}
				out.println(gson.toJson(status));
			}
			
			else if(property.equals("getHistoryUnit_price")){
				
				List<Unit_price> ln = Charge_model.getHistoryUnit_price();
				
				if(!ln.isEmpty()){
					status = new Status(true, gson.toJson(ln)); 
				}else{
					status = new Status(false, "没有历史记录");
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

