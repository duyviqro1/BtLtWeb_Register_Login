package vn.iotstar.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImple extends DBConnectMySQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "select * from user";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("fullname"), rs.getString("image"), rs.getString("password")));
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public UserModel findById(int id) {
		String sql = "select * from user where users.id = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("image"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO user (id, username, email, fullname, image, password) VALUES (?, ?, ?, ?, ?, ?) ";

		try {
			conn = super.getDatabaseConnection();

			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getImage());
			ps.setString(6, user.getPassword());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUsername(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(int id, String username, String email, String fullname, String image, String password) {
		if (this.checkExistUsername(username) || this.checkExistEmail(email))
			return false;
		this.insert(new UserModel(id, username, email, fullname, image, password));
		return true;
	}

	@Override
	public boolean checkExistUsername(String username) {
		String sql = "select * from user where username = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkExistEmail(String email) {
		String sql = "select * from user where email = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserModel findByUsername(String username) {
		String sql = "select * from user where username = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("fullname"), rs.getString("image"), rs.getString("password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		UserDaoImple userDao = new UserDaoImple();

		System.out.println(userDao.login("leduy", "123@@"));

		System.out.println(userDao.register(5, "abc", "abcd@gmail.com", "123", "ABC", "rsda21ess"));

		List<UserModel> list = userDao.findAll();

		for (UserModel user : list) {
			System.out.println(user);
		}
	}
}