package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 15) // 15MB
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search"})
public class CategoryControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("categories")) {

			List<CategoryModel> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/view/admin/category-list.jsp").forward(req, resp);

		} else if (url.contains("add")) {
			req.getRequestDispatcher("/view/admin/category-add.jsp").forward(req, resp);
		}else if (url.contains("edit")) {
			int id = Integer.parseInt( req.getParameter("id"));
			CategoryModel category = cateService.findbyId(id);
			
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/view/admin/category-edit.jsp").forward(req, resp);
			
		}else if (url.contains("delete")) {
			String id = req.getParameter("id");
			cateService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {

			String categoryname = req.getParameter("cate_name");
			String status = req.getParameter("active");
			int statuss = Integer.parseInt(status);
			CategoryModel category = new CategoryModel();
			category.setCate_name(categoryname);
			category.setActive(statuss);
			//upload image
			String fname = "";
			String uploadPath = DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize()>0)
				{
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() +  "." + ext;
					//upload file 
					part.write(uploadPath + "/" + fname);
					//ghi ten file vao data
					category.setImage(fname);
				}else {
					category.setImage("avata.png");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		} else if (url.contains("update")) {
			CategoryModel category = new CategoryModel();
			int categoryid = Integer.parseInt( req.getParameter("cate_id"));
			String categoryname = req.getParameter("cate_name");
			String status = req.getParameter("active");
			int statuss = Integer.parseInt(status);
			category.setCate_id(categoryid);
			category.setCate_name(categoryname);
			category.setActive(statuss);
			//luu hinh cu~
			CategoryModel cateold = cateService.findbyId(categoryid);
			String fileold = cateold.getImage();
			//upload image
			String fname = "";
			String uploadPath = DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize()>0)
				{
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() +  "." + ext;
					//upload file 
					part.write(uploadPath + "/" + fname);
					//ghi ten file vao data
					category.setImage(fname);
				}else {
					category.setImage(fileold);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		}
	}
}
