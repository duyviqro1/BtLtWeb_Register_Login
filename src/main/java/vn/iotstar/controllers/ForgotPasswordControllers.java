package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgotpassword")
public class ForgotPasswordControllers extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/view/forgotpassword.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("uname");
		String password = req.getParameter("psw");

		String alertMsg = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forgetPassword.jsp").forward(req, resp);
			return;
		}
		UserServiceImpl service = new UserServiceImpl();

		if (!service.checkExistUsername(username)) {
			alertMsg = "Tài khoản không tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/view/forgotp assword.jsp").forward(req, resp);
			return;
		}
		boolean tmp = service.updatePassword(username, password);
		if (tmp) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			alertMsg = "Có lỗi xảy ra";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/view/forgotpassword.jsp").forward(req, resp);
		}
	}
}
