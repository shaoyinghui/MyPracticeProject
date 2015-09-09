package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.giftDao;
import model.lottery_infoDao;
import model.personDao;
import model.return1;

public class lotteryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public lotteryServlet() {
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

		response.setContentType("text/html");

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
		System.out.println("-------------------------------------");
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		return1 r  = null;
		personDao pd = new personDao();
		String wechat_id = request.getParameter("wechat_id");	
		int remaining_number = pd.selectPerson(wechat_id);
		remaining_number--;
		System.out.println("remainingnum "+remaining_number);
		Date date = new Date();
		if(remaining_number>0){
			String gift = request.getParameter("gift");
			int gift_id=0;
			if(gift.equals("谢谢参与")){
				 gift_id = 0;
			}
			else if(gift.equals("300元礼品")){
				 gift_id = 1;
			}
			else if(gift.equals("100元双人电影票")){
				 gift_id = 2;
			}
			else if(gift.equals("十元话费")){
				 gift_id = 3;
			}
			lottery_infoDao ld = new lottery_infoDao();
			ld.insertLottery(date, gift_id, wechat_id);		
			boolean result1 = pd.updatePerson1(wechat_id, remaining_number);
			System.out.println("updatePerson1 "+result1);
			if(gift_id!=0){
				giftDao gd = new giftDao();
				boolean result2 = gd.updateGift(gift_id);
			}
		}
		PrintWriter out =null;		
		try {			
			out = response.getWriter();	
			//{"key1":"dfdf","key1":"ffff"}
			r.setRemaining_number(remaining_number);
			//String jsonData="{\"remaining_number\":"+remaining_number+"}";			
			//out.print(jsonData);	
			System.out.println(gson.toJson(r));
			out.println(gson.toJson(r));
			} catch (Exception e) {			
				e.printStackTrace();		
				}finally{			
					if (out!=null) {				
						out.flush();				
						out.close();			
						}		
					}
					
					
		
//		request.getParameter("wechat_id");
//		PrintWriter out = response.getWriter();
//		out.print("{\"aaa\":\"222\"}");
//		out.flush();
//		out.close();
		
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
