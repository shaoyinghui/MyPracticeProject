package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.lottery_infoDao;
import model.personDao;

public class personServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public personServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String wechat_id = request.getParameter("wechat_id");
		System.out.println("111111111111111");
		String judge = "";
		boolean duijiang = false;
		System.out.println(wechat_id);
		if(wechat_id.equals("")){
			 judge = "noperson";
		}
		else{
		String nickname = request.getParameter("nickname");
			personDao pd = new personDao();
			
			int a = pd.selectPerson(wechat_id);
			if(a==-1){
			   boolean result1 = pd.insertPerson(wechat_id, nickname);
			   System.out.println("insert "+result1);
			   boolean result2 = pd.updatePerson1(wechat_id, 3);
			   System.out.println("update1---3");
			   judge = "3";
			}
			else{
				lottery_infoDao ld = new lottery_infoDao();
				Map<String, Object> m = ld.selectLottery(wechat_id);
				if(m.size()!=0){
				String gift_id = m.get("gift_id").toString();
				String person_name = m.get("person_name").toString();
				    if(gift_id.equals("0")){
					   judge  = Integer.toString(a);
				    }
				    else{
				    	if(person_name==null){
						 duijiang = false;	
				    	}else{
						 duijiang = true;
					}
					 
					judge = "zhongjiang";
				}
			}
		}
			System.out.println(judge);	
		PrintWriter out =null;		
		try {			
			out = response.getWriter();	
			//{"key1":"dfdf","key1":"ffff"}
			String data="{\"judge\":"+judge+",\"wechat_id\":"+wechat_id+",\"duijiang\":"+duijiang+"}";			
			out.print(data);		
			} catch (Exception e) {			
				e.printStackTrace();		
				}finally{			
					if (out!=null) {				
						out.flush();				
						out.close();			
						}
				}
		}
		
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
