package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Buy;
import domain.User;
import service.BuyService;
import service.iml.BuyServiceIml;

@WebServlet("/WantBuyServlet")
public class WantBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public WantBuyServlet() {
        super();
    }
    public static int toInt(String strNum ){//½«String×ª»»³Éint
    	 return Integer.parseInt(strNum);
    	 } 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			String Unumber = user.getUnumber();
			Buy buy = new Buy();
			int goodsID = toInt(request.getParameter("goodsID"));
			String Bconnect = request.getParameter("Bconnect");
			buy.setUnumber(Unumber);
			buy.setGoodsID(goodsID);
			buy.setBconnect(Bconnect);
			BuyService wantBuy = new BuyServiceIml();
			try {
				boolean i = wantBuy.WantBuy(buy);
				out.print(i);
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			out.print("false");
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
