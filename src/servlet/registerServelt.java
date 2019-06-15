package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.User;
import service.UserService;
import service.iml.UserServiceIml;

/**
 * Servlet implementation class registerServelt
 */
@WebServlet("/registerServelt")
public class registerServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//解决post方式编码
		
		response.setContentType("text/html;charset=UTF-8");
		//获取表单数据
		try {
			User user = new User();
//			BeanUtils.populate(user, request.getParameterMap()); //不知道为啥不能用了
			String Unumber = request.getParameter("Unumber"); //所以就用了这个
			String UPassword = request.getParameter("UPassword");
			String Unickname = request.getParameter("Unickname");
			String Usex = request.getParameter("sex");
			user.setUnumber(Unumber);
			user.setUPassword(UPassword);
			user.setUnickname(Unickname);
			user.setUsex(Usex);
			
			//调用业务逻辑
			UserService us = new UserServiceIml();
			us.register(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//分发转向
		response.getWriter().write("注册成功！ 1s后返回首页");
		response.setHeader("refresh","1;url="+request.getContextPath()+"/index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
