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

import domain.Sell;
import javafx.scene.control.Alert;
import service.BuyService;
import service.SellService;
import service.iml.BuyServiceIml;
import service.iml.SellServiceIml;

/**
 * Servlet implementation class CkGoodsServlet
 */
@WebServlet("/CkGoodsServlet")
public class CkGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CkGoodsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//���post��ʽ����
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		List<Sell> list = new ArrayList<Sell>();
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");
//		String keyword = "��";
//		String type = "getSelectGoods";
		String msg = "";
		try {
			if ("getSelectGoods".equals(type)) {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");//���뺺��
				BuyService CK =new BuyServiceIml();
				list = CK.getSelectGoods(keyword);
				for(int i = 0; i < list.size(); i++)//ͼƬ1�����ƣ��۸񣬷��࣬ͼƬ2��ͼƬ3��ͼƬ4���û�����������ID
					msg = msg + list.get(i).getSpicture1() + "|" + list.get(i).getSname() + "|" + list.get(i).getSprice() + "|" + list.get(i).getSclassify()
					+ "|" + list.get(i).getSpicture2()+ "|" + list.get(i).getSpicture3()+ "|" + list.get(i).getSpicture4()+ "|" + list.get(i).getUnumber()
					+ "|" + list.get(i).getSdescribe() + "|" + list.get(i).getGoodsID()+ "|";
			}else {
				SellService goods = new SellServiceIml();
				list = goods.getGoods();
				for(int i = 0; i < list.size(); i++)//ͼƬ1�����ƣ��۸񣬷��࣬ͼƬ2��ͼƬ3��ͼƬ4���û�����������ID
					msg = msg + list.get(i).getSpicture1() + "|" + list.get(i).getSname() + "|" + list.get(i).getSprice() + "|" + list.get(i).getSclassify()
					+ "|" + list.get(i).getSpicture2()+ "|" + list.get(i).getSpicture3()+ "|" + list.get(i).getSpicture4()+ "|" + list.get(i).getUnumber()
					+ "|" + list.get(i).getSdescribe() + "|" + list.get(i).getGoodsID()+ "|";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(msg);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
