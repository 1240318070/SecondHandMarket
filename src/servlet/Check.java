package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String status = request.getParameter("status");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (status.equals("log") && session.getAttribute("user") != null) {
			out.print("true");
		}else {
			out.print("false");
			session.removeAttribute("user");
		}
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
