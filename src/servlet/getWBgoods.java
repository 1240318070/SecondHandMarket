package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Sell;
import domain.User;
import service.BuyService;
import service.iml.BuyServiceIml;

@WebServlet("/getWBgoods")
public class getWBgoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public getWBgoods() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String Unumber = user.getUnumber();
		String msg = "";
		PrintWriter out = response.getWriter();
		BuyService ss = new BuyServiceIml();
		try {
			List<Sell> WBGoods = ss.getWBgoods(Unumber);
			for(int i = 0; i < WBGoods.size(); i++)//Unumber,Sname,Sprice,Sdescribe,Spicture1,Spicture2,Spicture3,Spicture4,goodsID
				msg = msg + WBGoods.get(i).getUnumber() + "|" + WBGoods.get(i).getSname() + "|" + WBGoods.get(i).getSprice() + "|" + WBGoods.get(i).getSdescribe()
				+ "|" + WBGoods.get(i).getSpicture1()+ "|" + WBGoods.get(i).getSpicture2()+ "|" + WBGoods.get(i).getSpicture3()+ "|" + WBGoods.get(i).getSpicture4()
				+ "|" + WBGoods.get(i).getGoodsID()+ "|";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(msg);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
