package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.payfee;
import dao.database;

public class getFeeNumber extends HttpServlet {

	private payfee payfee;
	public payfee getPayfee() {return payfee;}
	public void setPayfee(payfee payfee) {this.payfee = payfee;}
	 
	
	private String city;

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Constructor of the object.
	 */
	public getFeeNumber() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doPost(request,response);
		//response.setContentType("text/html;charset=utf-8");

		
		//System.out.println("------------"+city);
		//System.out.println(request.getParameter("city"));
		this.doPost(request, response);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		   response.setContentType("text/html;charset=utf-8");
		   System.out.println("11111111");
		   String city=request.getParameter("city");
		   String fee_type=request.getParameter("fee_type");
		   String fee_table_id1=request.getParameter("fee_table_id");
		   String fee_number1=request.getParameter("fee_number");
		   String is_student1=request.getParameter("is_student");
		   int fee_table_id = Integer.valueOf(fee_table_id1).intValue();
		   int is_student = Integer.valueOf(is_student1).intValue();
		   int fee_number = Integer.valueOf(fee_number1).intValue();
		   String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String dd=((int)(Math.random()*900000+100000))+"";
			
			int hash = fee_type.hashCode();
			String s = String.valueOf(hash);
			String orderNumber = "GW"+date.substring(0,date.length()-9)+""+s.substring(2,4)+""+"830010"+(dd.length()==6?dd:date.substring(date.length()-6, date.length()));
			request.setAttribute("orderId", orderNumber);
			request.setAttribute("txnAmt",(fee_number*100));
			database db = new database();
		   boolean result =db.insert(city, fee_table_id, fee_type, fee_number, is_student, "Î´Ö§¸¶",orderNumber);
		   
       System.out.println(result);
            System.out.println();
         response.sendRedirect("http://localhost:8080/WechatPay/mess.html");
      
 
//       String backEndUrl ="http://localhost:8080/WechatPay/servlet/getReturn";
 //      String  bizType="111111111";
//        response.sendRedirect("http://114.215.138.215:8088/zftpaygate/frontPayServlet?txnAmt="+"1"+"&orderId="+orderNumber+"&backEndUrl"+backEndUrl+"&bizType"+bizType);


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
