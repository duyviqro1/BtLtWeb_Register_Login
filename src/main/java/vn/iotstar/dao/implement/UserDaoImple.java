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
						rs.getString("fullname"), rs.getString("image"), rs.getString("password"),
						rs.getString("phone"), rs.getInt("roleid"), rs.getDate("createDate")));
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public UserModel findById(int id) {
		String sql = "select * from user where user.id = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("image"),
						rs.getString("phone"), rs.getInt("roleid"), rs.getDate("createDate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO user (username, email, fullname, image, password, roleid, phone, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

		try {
			conn = super.getDatabaseConnection();

			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getImage());
			ps.setString(5, user.getPassword());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getPhone());
			ps.setDate(8, user.getCreateDate());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

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
						rs.getString("fullname"), rs.getString("image"), rs.getString("password"),
						rs.getString("phone"), rs.getInt("roleid"), rs.getDate("createDate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "SELECT * FROM user WHERE email = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "SELECT * FROM user WHERE username = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "SELECT * FROM user WHERE phone = ?";

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();

			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}

	public static void main(String[] args) {
		try {
			IUserDao userDao = new UserDaoImple();
			System.out.println(userDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}