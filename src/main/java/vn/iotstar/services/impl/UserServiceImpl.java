package vn.iotstar.services.impl;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.implement.UserDaoImple;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImple();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findbyUsername(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel findbyUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (this.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		userDao.insert(new UserModel(username, email, password, fullname, null, phone, 1, date));
		return true;
	}

	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistUsername(phone);
	}
	@Override
	public void update(String username, String fullname, String image, String phone) {
		userDao.update(username, fullname, image, phone);
	}

	@Override
	public boolean updatePassword(String username, String newPassword) {
		return userDao.updatePassword(username, newPassword);
	}



}