package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserService;
import service.iml.UserServiceIml;

@WebServlet("/getUserMsgServlet")
public class getUserMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getUserMsgServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserService us = new UserServiceIml();
		String Unumber = request.getParameter("Unumber");
		if ("chet".equals(Unumber)) {
			String outUnumber = request.getParameter("outUnumber");
			HttpSession session = request.getSession();
			if (session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				Unumber = user.getUnumber();
				try {
					user = us.getUserMsg(Unumber);
					String msg = user.getUhead();
					user = us.getUserMsg(outUnumber);
					msg = msg + "|" + user.getUnickname();
					out.println(msg);
					out.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				out.println("false");
				out.close();
			}
		}else {
			try {
			User user = us.getUserMsg(Unumber);
			String msg = user.getUhead() + "|" + user.getUnickname() + "|" + user.getUsex();
			out.println(msg);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
