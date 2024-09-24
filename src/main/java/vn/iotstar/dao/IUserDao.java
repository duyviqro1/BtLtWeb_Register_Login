package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface IUserDao {

	List<UserModel> findAll();

	UserModel findById(int id);

	void insert(UserModel user);

	UserModel findByUsername(String username);

	boolean checkExistPhone(String phone);

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	boolean register(String username, String email, String password, String fullname, String image);

	boolean updatePassword(String username, String newPassword);

	void update(String username, String fullname, String image, String phone);
	

}
