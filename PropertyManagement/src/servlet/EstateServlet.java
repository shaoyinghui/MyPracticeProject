package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
//import java.security.acl.Owner;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.House;
import model.House_model;
import model.Owner;
import model.Owner_account;
import model.Owner_account_model;
import model.Owner_model;
import model.Park_space;
import model.Park_space_model;
import model.Repair_report;
import model.Status;
//import model.Owner;
import model.Vehicle;
import model.Vehicle_model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class EstateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EstateServlet() {
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
		
		  String type = (String)session.getAttribute("type");
	      if(type==null){
				session.invalidate();
				response.sendRedirect("http://localhost:8080/PropertyManagement/index.jsp");
				return;
			}
	
		
		//updateHouse有问题
		if(property.equals("updateHouse")){
			House h = new House();
			int house_id = Integer.parseInt(request.getParameter("house_id"));
			
			h.setHouse_building((String)request.getParameter("house_building"));
			h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
			h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
			h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
			h.setHouse_area(Float.parseFloat(request.getParameter("house_area")));
			h.setHouse_type(Integer.parseInt(request.getParameter("house_type")));
			h.setHouse_remark((String)request.getParameter("house_remark"));
			if(House_model.updateHouse(house_id,h)){
				status = new Status(true,"更新成功 ");
			}else{
				status = new Status(false, "更新失败");
			}
			out.println(gson.toJson(status));
			
			
			
			
		}else if(property.equals("isHouseRepeat")){       //isRepeat是判断房子是否已经出售
			House h = new House();
			h.setHouse_building((String)request.getParameter("house_building"));
			h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
			h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
			h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
			
			if(House_model.isRepeat(h)){
				status = new Status(true, "已有住户");
			}else{
				status = new Status(false,"空房可以出售");
			}
			out.println(gson.toJson(status));
		}
		
		
		
		
		 else if(property.equals("isOwner_accountRepeat")){
			String account_name   =  (String)request.getParameter("account_name");
			if(Owner_account_model.isRepeat(account_name)){
				status = new Status(true, "已被注册");
			}else{
				status = new Status(false, "用户名可以使用");
			}
			out.println(gson.toJson(status));
		}
		
		
		 else if(property.equals("getTotalandOccupied_House")){

			 int Total_House = House_model.getTotalHouse();
			 int Occupied_House = House_model.getOccupiedHouse();
			 if(Total_House!=-1&&Occupied_House!=-1)
				 status = new Status(true,  "{\"Total_House\":"+Total_House+",\"Occupied_House\":"+Occupied_House+"};") ;
			 else
				 status = new Status(false,"未查找到");
			 
			out.println(gson.toJson(status)); 	 

		 }

		
		 else if(property.equals("getTotalandOccupied_Park_space")){
			 
			 int TotalPark_space = Park_space_model.getTotalParkSpace();
			 int OccupiedPark_space = Park_space_model.getOccupiedParkSpace();
			 if(TotalPark_space!=-1&&OccupiedPark_space!=-1)
				 status = new Status(true,  "{\"TotalPark_space\":"+TotalPark_space+",\"OccupiedPark_space\":"+OccupiedPark_space+"};") ;
			 else
				 status = new Status(false,"未查找到");
			 
			out.println(gson.toJson(status)); 	 
		 }
		

		
		
		 else if(property.equals("addAccount")){
			 	House h = new House();
				//int house_id = Integer.parseInt(request.getParameter("house_id"));
				//h.setHouse_id(house_id);
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
				
			//默认添加一个 属性为业主的Owner
				model.Owner o = new model.Owner();
				o.setOwner_name(request.getParameter("owner_name"));
				o.setOwner_phone(request.getParameter("owner_phone"));
				o.setOwner_gender(Integer.parseInt(request.getParameter("owner_gender")));
				o.setOwner_email(request.getParameter("owner_email"));
				o.setOwner_age(Integer.parseInt(request.getParameter("owner_age")));
	
				Owner_account oa = new Owner_account();
				oa.setAccount_name(request.getParameter("account_name"));
				oa.setAccount_password(request.getParameter("account_password"));
				//oa.setRoom_address(request.getParameter("room_address"));
				
				
					Gson gson_Owner=new GsonBuilder().registerTypeAdapter(Owner.class, new JsonSerializer<Owner>() {
			            public JsonElement serialize(Owner  src, Type typeOfSrc,
			                    JsonSerializationContext context) {
			                JsonObject o=new JsonObject();
			                o.addProperty("owner_id", src.getOwner_id());
			                o.addProperty("owner_name", src.getOwner_name());
			                o.addProperty("owner_phone", src.getOwner_phone());
			                o.addProperty("owner_gender", src.getOwner_gender());
			                o.addProperty("owner_email", src.getOwner_email());
			                o.addProperty("owner_age", src.getOwner_age());
			                return o;
			            }
			        }).create();
				
					
				int account_id = Owner_account_model.addAccount(h,oa);//增加一个账户，没有owner
				
				if(account_id!=-1){
					
					
					o  = Owner_model.addOwner(account_id,o);
					if(o!=null){
						status = new Status(true,gson_Owner.toJson(o));
					}else{
						status = new Status(false,"添加出错");
					}
					out.println(gson.toJson(status));
				}
		 }
		
		 else if(property.equals("deleteAccount")){
			 	House h = new House();
				//int house_id = Integer.parseInt(request.getParameter("house_id"));
				//h.setHouse_id(house_id);
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
				
				if(Owner_account_model.deleteAccount(h)){
					status = new Status(true, "删除成功");
				}else{
					status = new Status(false, "删除失败");
				}
			 out.println(gson.toJson(status));
		 	}
		
		
		
		
		 else if(property.equals("findOwnerAccount")){
			 	House h = new House();
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
				
				Owner_account oa = Owner_account_model.findOwnerAccount(h);
				
				Gson gson_OwnerAccount=new GsonBuilder().registerTypeAdapter(Owner_account.class, new JsonSerializer<Owner_account>() {
		            public JsonElement serialize(Owner_account  src, Type typeOfSrc,
		                    JsonSerializationContext context) {
		                JsonObject o=new JsonObject();
		                o.addProperty("account_name", src.getAccount_name());
		                o.addProperty("account_id", src.getAccount_id());
		                o.addProperty("check_in_time", src.getCheck_in_time().toString());
		              
		                return o;
		            }
		        }).create();
				
				if(oa!=null){
					status = new Status(true, gson_OwnerAccount.toJson(oa));
				}else{
					status = new Status(false, "该房没有住户");
				}
					out.println(gson.toJson(status));		
		 	}
		

		
		
		 else if(property.equals("findOwner")){
				House h = new House();
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
			 
				List<model.Owner> lo= Owner_model.findOwner(h);
				
				if(!lo.isEmpty()){
					Gson gson_Owner=new GsonBuilder().registerTypeAdapter(Owner.class, new JsonSerializer<Owner>() {
			            public JsonElement serialize(Owner  src, Type typeOfSrc,
			                    JsonSerializationContext context) {
			                JsonObject o=new JsonObject();
			                o.addProperty("owner_id", src.getOwner_id());
			                o.addProperty("owner_name", src.getOwner_name());
			                o.addProperty("owner_phone", src.getOwner_phone());
			                o.addProperty("owner_gender", src.getOwner_gender());
			                o.addProperty("owner_email", src.getOwner_email());
			                o.addProperty("owner_age", src.getOwner_age());
			                return o;
			            }
			        }).create();
					status = new Status(true, gson_Owner.toJson(lo));
				}else{
					status = new Status(false, "还没有住户");
				}				
				out.println(gson.toJson(status));
		 	}
		
		
	     //错
		 else if(property.equals("findHouse")){
			String house_building   =   request.getParameter("house_building");
			int    house_floor      =   Integer.parseInt(request.getParameter("house_floor"));
			int    house_unit       =   Integer.parseInt(request.getParameter("house_unit"));
			int    house_num        =   Integer.parseInt(request.getParameter("house_num"));
			
			House h = House_model.findHouse(house_building,house_floor,house_unit,house_num);
			if(h!=null){
					status = new Status(true, gson.toJson(h));
			}else{
					status = new Status(false, "未查找到房产信息");
			}
				out.println(gson.toJson(status));
		 }
			

		 else if(property.equals("findHouseByHouse_id")){
			 
			 int house_id = Integer.parseInt(request.getParameter("house_id"));
			 
			 House h = House_model.getHouse(house_id);
			 if(h!=null){
				 	status = new Status(true,gson.toJson(h));
			 }else{
				 	status = new Status(false,"未查找到房产信息");
			 }
			 out.println(gson.toJson(status));
		 }
		
		
		 else if(property.equals("findOwnerByHouse_id")){
			 int house_id = Integer.parseInt(request.getParameter("house_id"));
			 int account_id = House_model.getAccountID(house_id);
			 Gson gson_Owner=new GsonBuilder().registerTypeAdapter(model.Owner.class, new JsonSerializer<model.Owner>() {
		            public JsonElement serialize(model.Owner  src, Type typeOfSrc,
		                    JsonSerializationContext context) {
		                JsonObject o=new JsonObject();
		                o.addProperty("owner_name",  src.getOwner_name());
		                o.addProperty("owner_phone", src.getOwner_phone());
		                o.addProperty("owner_gender",src.getOwner_gender());
		                o.addProperty("owner_email",  src.getOwner_email());
		                o.addProperty("owner_age",  src.getOwner_age());
		                return o;
		            }
		        }).create();
			 
		 if(account_id!=-1){
				 List<model.Owner> ao = Owner_model.findOwner(account_id);

					if(!ao.isEmpty()){
						status = new Status(true, gson_Owner.toJson(ao));
					}else{
						status = new Status(false,"没有住户");
					}
			 }else{
				 status = new Status(false,"没有住户"); 
			 }
			 out.println(gson.toJson(status));
		 }
		
		
		
		 else if(property.equals("addVehicle")){
			 	House h = new House();
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
				
				Vehicle v = new Vehicle();
				Vehicle v1 = new Vehicle();
				v.setVehicle_plate(request.getParameter("vehicle_plate"));
				
	
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
				    
				 
				 v1 = Vehicle_model.addVehicle(h,v);
				 if(v!=null){
					 status=new Status(true,gson_Vehicle.toJson(v));		
				 }else{
					 status= new Status(false,"未知错误");
				 }
				out.println(gson.toJson(status));
		 	}
		
		
		
		
		 else if(property.equals("deleteVehicle")){
			 	int	vehicle_id  = 	Integer.parseInt(request.getParameter("vehicle_id"));
			 	
			 	if(Vehicle_model.deleteVehicle(vehicle_id)){
			 		status = new Status(true, "车辆删除成功");
			 	}else{
			 		status = new Status(false, "车辆删除失败");
			 	}
			 	out.println(gson.toJson(status));
		 	}
		
		
		

		 else if(property.equals("findVehicle")){
			 	House h = new House();
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
				

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
					
				
				List<Vehicle>  lv =	Vehicle_model.findVehicle(h);

				if(!lv.isEmpty()){
					status = new Status(true, gson_Vehicle.toJson(lv));
				}else{
					status = new Status(false, "该住户没有车辆");
				}
					out.println(gson.toJson(status));		
		 	}
		
		
		
		
		
		 else if(property.equals("updateVehicle")){
			 	Vehicle v = new Vehicle();
				v.setVehicle_plate(request.getParameter("vehicle_plate"));
				int	vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
			 	if(Vehicle_model.updateVehicle(v,vehicle_id)){
			 		status = new Status(true, "车辆更新成功");
			 	}else{
			 		status = new Status(false, "车辆更新失败");
			 	}
			 	out.println(gson.toJson(status));
		 }
		
		
		
		
		
		 else if(property.equals("addPark")){
			 	House h = new House();
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
			 
				int park_space_id = Integer.parseInt(request.getParameter("park_space_id"));
				
			//	******************************************************这个参数可能错
				if(Park_space_model.addPark(h,park_space_id)){
					status = new Status(true, "停车位添加成功");
				}else{
					status = new Status(false, "车位添加失败");
				}
				out.println(gson.toJson(status));
		 }
			
		

		
		 else if(property.equals("deletePark")){
			 int park_space_id = Integer.parseInt(request.getParameter("park_space_id"));
			 
			 if(Park_space_model.deletePark(park_space_id)){
				 status = new Status(true, "停车位删除成功");
			 }else{
				 status = new Status(false, "停车位删除失败");
			 }
			 out.println(gson.toJson(status));
		 }
			
		

		
		 else if(property.equals("findPark")){
			 	House h = new House();
				h.setHouse_building(request.getParameter("house_building"));
				h.setHouse_floor(Integer.parseInt(request.getParameter("house_floor")));
				h.setHouse_unit(Integer.parseInt(request.getParameter("house_unit")));
				h.setHouse_num(Integer.parseInt(request.getParameter("house_num")));
				
				Gson gson_Park=new GsonBuilder().registerTypeAdapter(Park_space.class, new JsonSerializer<Park_space>() {
		            public JsonElement serialize(Park_space src, Type typeOfSrc,
		                    JsonSerializationContext context) {
		                JsonObject o=new JsonObject();
		                o.addProperty("park_space_id", src.getPark_space_id());
		                o.addProperty("park_position", src.getPark_position());
		                return o;
		            }
		        }).create();
				
				List<Park_space> lp = Park_space_model.findPark(h);
				
				
				if(!lp.isEmpty()){
					status = new Status(true, gson_Park.toJson(lp));
				}else{
					status = new Status(false, "该住户没有停车位");
				}
				out.println(gson.toJson(status));
		 }

		
		 else if(property.equals("updatePark")){
			 int park_space_id = Integer.parseInt(request.getParameter("park_space_id"));
			 int newpark_space_id = Integer.parseInt(request.getParameter("newpark_space_id"));
			 
			 if(Park_space_model.updatePark(park_space_id,newpark_space_id)){
				 status = new Status(true, "停车位更新成功");
			 }else{
				 status = new Status(false, "停车位更新失败");
			 }
			 out.print(gson.toJson(status));
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
