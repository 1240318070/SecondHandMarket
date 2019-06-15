package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SellDAO;
import DAO.iml.SellDAOiml;

/**
 * Servlet implementation class delGoods
 */
@WebServlet("/delGoods")
public class delGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public delGoods() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String goodsID = request.getParameter("goodsID");
		String gtype = request.getParameter("gtype");
		SellDAO goods = new SellDAOiml();
		try {
			if (gtype.equals("SELL")) {
				goods.delGoods(goodsID);
			}else {
				goods.delWBGoods(goodsID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print("true");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
