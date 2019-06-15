package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import domain.Sell;
import domain.User;
import service.SellService;
import service.iml.SellServiceIml;

@WebServlet("/UplodServlet")
public class UplodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    public UplodServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
	    String UPLOAD_DIRECTORY = "userPic/" + user.getUnumber();
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: enctype=multipart/form-data");
            writer.flush();
            return;
        }
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
         
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        upload.setSizeMax(MAX_REQUEST_SIZE);

        upload.setHeaderEncoding("UTF-8"); 

        String uploadPath = request.getServletContext().getRealPath("./") + "/" + UPLOAD_DIRECTORY;
         
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
            Sell goods = new Sell();
            if (formItems != null && formItems.size() > 0) {
            	int i = 0;
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                    	String fName  = item.getName();
                    	if (fName!=null && !fName.equals("")) {
                    		i = i + 1;
                    		String fileName = new File(item.getName()).getName();
                    		String filePath = uploadPath + "/" + fileName;
                    		File storeFile = new File(filePath);
                    		item.write(storeFile);
                    		filePath = UPLOAD_DIRECTORY + "/" + fileName;
                    		switch (i) {
                    		case 1:
    							goods.setSpicture1(filePath);
    							break;
    						case 2:
    							goods.setSpicture2(filePath);
    							break;
    						case 3:
    							goods.setSpicture3(filePath);
    							break;
    						case 4:
    							goods.setSpicture4(filePath);
    							break;

							default:
								break;
							}
						}
                    }
                    else {
                    	String name = item.getFieldName();
                    	String value = item.getString("UTF-8");
                    	switch (name) {
						case "Sname":
							goods.setSname(value);
							break;
						case "Sprice":
							goods.setSprice(value);
							break;
						case "Sclassify":
							goods.setSclassify(value);
							break;
						case "Sdescribe":
							goods.setSdescribe(value);
							break;

						default:
							break;
						}
					}
                }
            }
            goods.setUnumber(user.getUnumber());
            SellService sell = new SellServiceIml();
			sell.SellGoods(goods);
			PrintWriter out = response.getWriter();
			response.setHeader("refresh","0.01;url="+request.getContextPath()+"/my.jsp");
			out.print("<script type='text/javascript'>alert('商品添加成功');</script>");
			out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
