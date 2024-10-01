package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.implement.CategoryDaoImpl;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService{

	public CategoryDao cateDao = new CategoryDaoImpl();
	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(CategoryModel category) {
		cateDao.update(category);
		
	}

	@Override
	public void delete(int id) {
		cateDao.delete(id);
		
	}

	@Override
	public CategoryModel findbyId(int id) {
		return cateDao.findbyId(id);
	}

	@Override
	public List<CategoryModel> findAll() {
		return cateDao.findAll();
	}

	@Override
	public List<CategoryModel> findbyCategoryName(String keyword) {
		return cateDao.findbyCategoryName(keyword);
	}

}
