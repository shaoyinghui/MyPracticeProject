package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.House;
import model.House_model;
import model.Manager;
import model.Manager_model;
import model.Owner_account_model;
import model.Owner_model;
import model.Park_space;
import model.Park_space_model;
import model.Repair_report;
import model.Root_model;
import model.Status;
import model.Vehicle;
import model.Vehicle_model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class PersonalInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PersonalInfoServlet() {
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
		
		
		
		//鏈敓鏂ゆ嫹褰曠洿閿熸枻鎷烽敓鏂ゆ嫹杞敓鏂ゆ嫹閿熸枻鎷烽〉
		HttpSession session = request.getSession();

        String property = (String)request.getParameter("property"); 
        String type = (String)session.getAttribute("type");
    
        if(type==null){
        	session.invalidate();
			response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
			return;
        }
        
        
        Gson gson = new Gson();
		
        
        
      
        
        //isRepeat閿熷彨璁规嫹   閿熺煫浼欐嫹閿熸枻鎷烽敓鎴潻鎷烽敓鏂ゆ嫹閿熸枻鎷穎alse閿熸枻鎷�
    	if(property.equals("isRepeat")){
    			String username = (String)request.getParameter("username");
            	Status status = null;
            	
            	
            	if(type.equals("manager")){
            		if(Owner_account_model.isRepeat(username)){
            			status = new Status(false, " ");
            		} else {
       				 	status = new Status(true, " ");
					}
       			 out.println(gson.toJson(status));
       		}
       	
            	else if (type.equals("owner")){
            		if(Owner_account_model.isRepeat(username)){
            			status = new Status(false, " ");
            		} else {
   				 		status = new Status(true, " ");
            		}
            		out.println(gson.toJson(status));					
            	}
       		
            	else if (type.equals("root")){
            		if(Manager_model.isRepeat(username)){
            			status = new Status(false, " ");
            		} else {
            			status = new Status(true, " ");
            		}
            		out.println(gson.toJson(status));					
            	}
            	         			
    		}
     
    	
    	
    	
     if(type.equals("owner")){
		if(property.equals("findHouse")){
			Status status = null;
			int account_id = (Integer) session.getAttribute("account_id");
			
			
			
			Gson gson_House=new GsonBuilder().registerTypeAdapter(House.class, new JsonSerializer<House>() {
	            public JsonElement serialize(House  src, Type typeOfSrc,
	                    JsonSerializationContext context) {
	                JsonObject o=new JsonObject();
	                //o.addProperty("house_id", src.getHouse_id());
	                o.addProperty("house_building", src.getHouse_building());
	                o.addProperty("house_floor", src.getHouse_floor());
	                o.addProperty("house_unit",src.getHouse_unit());
	                o.addProperty("house_num", src.getHouse_num());
	                o.addProperty("house_area", src.getHouse_area());
	                o.addProperty("house_type", src.getHouse_type());
	                o.addProperty("house_remark", src.getHouse_remark());
	                return o;
	            }
	        }).create();

			House h = House_model.findHouse(account_id);
			if(h!=null){
				status = new Status(true, gson_House.toJson(h));
			}else{
				status = new Status(false," ");
			}
			
			out.println(gson.toJson(status));	
			
		}else if(property.equals("findManager")){	
			Status status =null;
			List<Manager> lm= Manager_model.findManager();
		
			if(!lm.isEmpty()){
					status = new Status(true, gson.toJson(lm));
			}else{
					status = new Status(false, "閿熺煫鍑ゆ嫹浣忛敓鏂ゆ嫹涓洪敓鏂ゆ嫹");
				}
				out.println(gson.toJson(status));
			
			}else if(property.equals("findVehicle")){
				Status status = null;
				int account_id = (Integer) session.getAttribute("account_id");
			
				List<Vehicle> av = Vehicle_model.findVehicle(account_id);
			
				Gson gson_Vehicle=new GsonBuilder().registerTypeAdapter(Vehicle.class, new JsonSerializer<Vehicle>() {
					public JsonElement serialize(Vehicle  src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o=new JsonObject();
						o.addProperty("vehicle_id", src.getVehicle_id());
						o.addProperty("vehicle_plate", src.getVehicle_plate());
						if(src.getVehicle_register_time()!=null)
							o.addProperty("vehicle_registe_time", src.getVehicle_register_time().toString().split(" ")[0]);
						else
							o.addProperty("vehicle_registe_time","未知错误");
	          
						return o;
					}
				}).create();
			
				if(!av.isEmpty()){
					status = new Status(true, gson_Vehicle.toJson(av));
				}else{
					status = new Status(false, "浣忛敓鏂ゆ嫹娌￠敓鍙鎷烽敓鏂ゆ嫹");
				}
					out.println(gson.toJson(status));
			}else if(property.equals("findPark")){
				Status status =null;
				int account_id = (Integer) session.getAttribute("account_id");
			
				List<Park_space> ap = Park_space_model.findPark(account_id);
			
				Gson gson_Park=new GsonBuilder().registerTypeAdapter(Park_space.class, new JsonSerializer<Park_space>() {
					public JsonElement serialize(Park_space src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o=new JsonObject();
						o.addProperty("park_space_id", src.getPark_space_id());
						o.addProperty("park_position", src.getPark_position());
						return o;
					}
				}).create();
				
				if(!ap.isEmpty()){
					status = new Status(true, gson_Park.toJson(ap));
				}else{
					status = new Status(false, "閿熸枻鎷蜂綇閿熸枻鎷锋病閿熸枻鎷峰仠閿熸枻鎷烽敓鏂ゆ嫹");
				}
					out.println(gson.toJson(status));
			
			
			}else if(property.equals("findOwner")){
				Status status =null;
				int account_id = (Integer) session.getAttribute("account_id");
			
				List<model.Owner> ao = Owner_model.findOwner(account_id);
			
				Gson gson_Owner=new GsonBuilder().registerTypeAdapter(model.Owner.class, new JsonSerializer<model.Owner>() {
					public JsonElement serialize(model.Owner  src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o=new JsonObject();
						o.addProperty("owner_id", src.getOwner_id());
						o.addProperty("owner_name",  src.getOwner_name());
	                	o.addProperty("owner_phone", src.getOwner_phone());
	                	o.addProperty("owner_gender",src.getOwner_gender());
	                	o.addProperty("owner_email",  src.getOwner_email());
	                	o.addProperty("owner_age",  src.getOwner_age());
	                	return o;
					}
				}).create();
			
				if(!ao.isEmpty()){
					status = new Status(true, gson_Owner.toJson(ao));
				}else{
					status = new Status(false,"閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹");
				}
				out.println(gson.toJson(status));
			

		}else if(property.equals("addOwner")){
			Status status = null;
			int account_id 			=   (Integer) session.getAttribute("account_id");
			
		
			
			model.Owner o = new model.Owner();
			o.setOwner_name((String)request.getParameter("owner_name"));
			o.setOwner_phone( (String)request.getParameter("owner_phone"));
			o.setOwner_gender(Integer.parseInt(request.getParameter("owner_gender")));
			o.setOwner_email((String)request.getParameter("owner_email"));
			o.setOwner_age(Integer.parseInt(request.getParameter("owner_age")));
	
			
			Gson gson_Owner=new GsonBuilder().registerTypeAdapter(model.Owner.class, new JsonSerializer<model.Owner>() {
				public JsonElement serialize(model.Owner  src, Type typeOfSrc,
						JsonSerializationContext context) {
					JsonObject o=new JsonObject();
					o.addProperty("owner_id", src.getOwner_id());
					o.addProperty("owner_name",  src.getOwner_name());
                	o.addProperty("owner_phone", src.getOwner_phone());
                	o.addProperty("owner_gender",src.getOwner_gender());
                	o.addProperty("owner_email",  src.getOwner_email());
                	o.addProperty("owner_age",  src.getOwner_age());
                	return o;
				}
			}).create();
			
			 o 	= 	Owner_model.addOwner(account_id,o);
			 if(o!=null){
				 status = new Status(true,gson_Owner.toJson(o));
			 }else{
				 status = new Status(false,"添加出错");
			 }
			out.println(gson.toJson(status));
		}else if(property.equals("deleteOwner")){
			int owner_id       	    =  Integer.parseInt(request.getParameter("owner_id"));
			
			Status status = null;
			if(Owner_model.deleteOwner(owner_id)){
				status  = new Status(true, "鍒犻敓鏂ゆ嫹浣忛敓鏂ゆ嫹閿熺即鐧告嫹");
			}else{
				status = new Status(false, "鍒犻敓鏂ゆ嫹浣忛敓鏂ゆ嫹澶遍敓鏂ゆ嫹");
			}
			out.println(gson.toJson(status));
			
		}else if(property.equals("updateOwner")){
			int owner_id       	=   Integer.parseInt(request.getParameter("owner_id"));
			model.Owner o = new model.Owner();
			o.setOwner_id(owner_id);
			o.setOwner_name((String)request.getParameter("owner_name"));
			o.setOwner_phone((String)request.getParameter("owner_phone"));
			o.setOwner_gender(Integer.parseInt(request.getParameter("owner_gender")));
			o.setOwner_email( (String)request.getParameter("owner_email"));
			o.setOwner_age( Integer.parseInt(request.getParameter("owner_age")));

			Status status = null;
			if(Owner_model.updateOwner(owner_id,o)){
				status  = new Status(true, "閿熸枻鎷烽敓鏂ゆ嫹浣忛敓鏂ゆ嫹閿熺即鐧告嫹");
			}else{
				status = new Status(false, "閿熸枻鎷烽敓鏂ゆ嫹浣忛敓鏂ゆ嫹澶遍敓鏂ゆ嫹");
			}
			out.println(gson.toJson(status));	
			
			
		}else if(property.equals("updatePassword")){
			int account_id		    = (Integer) session.getAttribute("account_id");
		
			String newpassword      =   (String)request.getParameter("newpassword");
			Status status = null;
			if(Owner_account_model.updatePassword(account_id,newpassword)){
				status  = new Status(true, "index.jsp");
				out.print(gson.toJson(status));
				session.invalidate();
 		        //response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
 		        return;
			}else{
				status = new Status(false, "閿熺潾闈╂嫹閿熸枻鎷烽敓鏂ゆ嫹澶遍敓鏂ゆ嫹");
			}
			out.println(gson.toJson(status));
		}
      }
      
      
      	//manager  閿熸枻鎷烽敓鐨嗚鎷疯閿熺殕纭锋嫹閿熸枻鎷烽敓鏂ゆ嫹鎭敓鏂ゆ嫹閿熺潾闈╂嫹閿熸枻鎷烽敓鏂ゆ嫹
      else if(type.equals("manager")){
    	  Status status = null;
    	  	if(property.equals("getInfo")){
    	  		int manager_id = (Integer) session.getAttribute("manager_id");
    	  		
    			Manager m = Manager_model.getInfo(manager_id);
    			
    			if(m!=null){
    				status = new Status(true, gson.toJson(m));
    			}else{
    				status = new Status(false, "閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹");
    			}
    			out.println(gson.toJson(status));	
    			
    			
    	  	}else if(property.equals("updatePassword")){
    	  		int manager_id 			= (Integer) session.getAttribute("manager_id");
    	  		
    	  		String newpassword      =   (String)request.getParameter("newpassword");
    	  		if(Manager_model.updatePassword(manager_id,newpassword)){
    	  			status  = new Status(true, " ");
    	  			session.invalidate();
    	  			response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
    	  			return;
    	  		}else{
    	  			status  = new Status(false, "閿熺潾闈╂嫹閿熸枻鎷烽敓鏂ゆ嫹澶遍敓鏂ゆ嫹");
    	  		}
    	  		out.println(gson.toJson(status));
       	  	}
    	  
      }
      
      else if(type.equals("root")){
    	  Status status = null;
    	  	if(property.equals("updatePassword")){
    	  		int        root_id 		 =   (Integer) session.getAttribute("root_id");
    	  		
    	  		String	   newpassword   =   (String)request.getParameter("newpassword");
    	  		if(Root_model.updatePassword(root_id,newpassword)){
    	  			status  = new Status(true, " ");
    	  			session.invalidate();
     		        response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
     		        return;
    	  		}else{
    	  			status  = new Status(false, "閿熺潾闈╂嫹閿熸枻鎷烽敓鏂ゆ嫹澶遍敓鏂ゆ嫹");
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
			doGet(request, response);   //閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽�浣块敓鏂ゆ嫹doGet

	}
	
	
	
	private static boolean toBoolean(String name) {
		return ((name != null) && name.equalsIgnoreCase("true"));
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
