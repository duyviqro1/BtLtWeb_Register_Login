package vn.iotstar.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.models.CategoryModel;

public class CategoryDaoImpl extends DBConnectMySQL implements CategoryDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public void insert(CategoryModel category) {
		String sql = "INSERT INTO category(cate_name,image, active) VALUES (?,?,?)";
		try {
			Connection con = super.getDatabaseConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCate_name());
			ps.setString(2, category.getImage());
			ps.setInt(3, category.getActive());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(CategoryModel category) {
		String sql = "UPDATE category SET cate_name = ?, image=?, active=? WHERE cate_id = ?";
		try {
			Connection con = super.getDatabaseConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCate_name());
			ps.setString(2, category.getImage());
			ps.setInt(3, category.getActive());
			ps.setInt(4, category.getCate_id());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE cate_id = ?";
		try {
			Connection con = super.getDatabaseConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public CategoryModel findbyId(int id) {

		String sql = "SELECT * FROM category WHERE cate_id = ? ";
		try {
			Connection con = super.getDatabaseConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCate_id(rs.getInt("cate_id"));
				category.setCate_name(rs.getString("cate_name"));
				category.setImage(rs.getString("image"));
				category.setActive(rs.getInt("active"));
				return category;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Category";
		try {
			conn= super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCate_id(rs.getInt("cate_id"));
				category.setCate_name(rs.getString("cate_name"));
				category.setImage(rs.getString("image"));
				category.setActive(rs.getInt("active"));
				categories.add(category);
			}
			return categories;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryModel> findbyCategoryName(String keyword) {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Category";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCate_id(rs.getInt("cate_id"));
				category.setCate_name(rs.getString("cate_name"));
				category.setImage(rs.getString("image"));
				category.setActive(rs.getInt("active"));
				categories.add(category);
			}
			conn.close();
			return categories;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
