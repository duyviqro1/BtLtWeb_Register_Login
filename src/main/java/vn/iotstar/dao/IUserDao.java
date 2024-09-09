package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface IUserDao {

	List<UserModel> findAll();

	UserModel findById(int id);

	void insert(UserModel user);

	UserModel login(String username, String password);

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	UserModel findByUsername(String username);

	boolean register(int id, String username, String email, String fullname, String image, String password);
	

}
