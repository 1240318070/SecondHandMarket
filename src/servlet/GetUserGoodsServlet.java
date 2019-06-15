package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SellDAO;
import DAO.iml.SellDAOiml;
import domain.Sell;
import domain.User;

@WebServlet("/GetUserGoodsServlet")
public class GetUserGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetUserGoodsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//���post��ʽ����
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		SellDAO goods = new SellDAOiml();
		List<Sell> list = new ArrayList<Sell>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String Unumber = user.getUnumber();
		String msg = "";
		try {
			list = goods.getUserGoods(Unumber);
			for(int i = 0; i < list.size(); i++)//ͼƬ1�����ƣ��۸񣬷��࣬ͼƬ2��ͼƬ3��ͼƬ4���û�����������ID
				msg = msg + list.get(i).getSpicture1() + "|" + list.get(i).getSname() + "|" + list.get(i).getSprice() + "|" + list.get(i).getSclassify()
				+ "|" + list.get(i).getSpicture2()+ "|" + list.get(i).getSpicture3()+ "|" + list.get(i).getSpicture4()+ "|" + list.get(i).getUnumber()
				+ "|" + list.get(i).getSdescribe() + "|" + list.get(i).getGoodsID()+ "|";
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
