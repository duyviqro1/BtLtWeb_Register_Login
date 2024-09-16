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

	
	

}
