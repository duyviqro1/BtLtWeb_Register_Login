package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {
	
	UserModel login(String username, String password);
	UserModel findbyUsername (String username);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	void insert(UserModel user);
	boolean register(String email, String password, String username, String fullname, String phone);
	boolean updatePassword(String username, String newPassword);
	void update(String username, String image, String fullname, String phone);
}