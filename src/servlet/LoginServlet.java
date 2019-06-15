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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		User user = new User();
		String Unumber = request.getParameter("Unumber");
		String UPassword = request.getParameter("UPassword");
		user.setUnumber(Unumber);
		user.setUPassword(UPassword);
		HttpSession session = request.getSession();//获取会话对象，用来保存当前登录用户信息
		
		//调用业务逻辑
		boolean i = false;
		PrintWriter out = response.getWriter();
		try {
			UserService us = new UserServiceIml();
			i = us.login(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(i);
		out.close();
		if (i) {
			session.setAttribute("user", user);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
