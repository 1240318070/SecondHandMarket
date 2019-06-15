package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.SessionManager;
import service.UserService;
import service.iml.UserServiceIml;

@WebServlet("/OneToOneServlet")
public class OneToOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OneToOneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = java.net.URLDecoder.decode(request.getParameter("userID"), "UTF-8");
		String msg = java.net.URLDecoder.decode(request.getParameter("msg"), "UTF-8");//解码汉字
		String goodsName = java.net.URLDecoder.decode(request.getParameter("goodsName"), "UTF-8");//解码汉字
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		UserService us = new UserServiceIml();
		String Unumber = user.getUnumber();
		try {
			user = us.getUserMsg(Unumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(SessionManager.getInstance().hasSession(userID)){
			Event event = Event.createDataEvent("/pushlet/onetoone");
			//加编码方式解决pushlet不能推送中文问题
			event.setField("message", URLEncoder.encode(msg, "utf-8"));
			event.setField("myUhead", URLEncoder.encode(user.getUhead(), "utf-8"));
			event.setField("myUnickname", URLEncoder.encode(user.getUnickname(), "utf-8"));
			event.setField("goodsName", URLEncoder.encode(goodsName, "utf-8"));
			event.setField("myUnumber", URLEncoder.encode(Unumber, "utf-8"));
			Dispatcher.getInstance().unicast(event, userID);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
