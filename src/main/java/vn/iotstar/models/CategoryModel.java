package vn.iotstar.models;

import java.io.Serializable;

public class CategoryModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private int cate_id;
	private String cate_name;
	private String image;
	private int active;
	
	public CategoryModel() {
		super();
	}

	public CategoryModel(int cate_id, String cate_name, String image, int active) {
		super();
		this.cate_id = cate_id;
		this.cate_name = cate_name;
		this.image = image;
		this.active = active;
	}

	public int getCate_id() {
		return cate_id;
	}

	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "CategoryModel [cate_id=" + cate_id + ", cate_name=" + cate_name + ", image=" + image + ", active="
				+ active + "]";
	}
	
	
	
	
}
