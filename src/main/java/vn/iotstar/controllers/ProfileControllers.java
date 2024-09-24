package vn.iotstar.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/profile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 15) // 15MB
public class ProfileControllers extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		UserModel account = (UserModel) session.getAttribute("account");

		String username = account.getUsername();
		String image = account.getImage();
		String fullname = account.getFullname();
		String email = account.getEmail();
		String phone = account.getPhone();

		req.setAttribute("image", image);
		req.setAttribute("username", username);
		req.setAttribute("fullname", fullname);
		req.setAttribute("email", email);
		req.setAttribute("phone", phone);

		req.getRequestDispatcher("/view/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserModel account = (UserModel) session.getAttribute("account");

		String fileName = account.getImage();
		String username = account.getUsername();
		String image = account.getImage();
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");

		// Xu ly upload file
		Part part = req.getPart("multiPartServlet");
		if (part != null && part.getSize() > 0) {
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				fileName = part.getSubmittedFileName();
				part.write(uploadPath + File.separator + fileName);
				req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
				image = "./uploads/" + fileName;
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
		}

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		service.update(username, image, fullname, phone);

		account.setImage(image);
		account.setFullname(fullname);
		account.setPhone(phone);
		session.setAttribute("account", account);

		doGet(req, resp);
	}
}