package com.cat.system.jsonbean;

import java.util.ArrayList;
import java.util.List;

import com.cat.boot.util.StringUtil;
import com.cat.system.model.Organization;
import com.cat.system.model.Person;

public class AddressBean {

	private String name;
	private String id;
	private Boolean isorgan;
    private String avatar;
    private Integer xh;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsorgan() {
		return isorgan;
	}
	public void setIsorgan(Boolean isorgan) {
		this.isorgan = isorgan;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getXh() {
		return xh;
	}
	public void setXh(Integer xh) {
		this.xh = xh;
	}
	
	public static List<AddressBean> setThis(List<Organization> entitys) {
		List<AddressBean> beans = new ArrayList<AddressBean>();
		if(!StringUtil.isListEmpty(entitys)) {
			for (Organization entity : entitys) {
				AddressBean bean = new AddressBean();
				bean.setAvatar("");
				bean.setId(entity.getId());
				bean.setIsorgan(true);
				bean.setName(entity.getName());
				bean.setXh(entity.getXh());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public static List<AddressBean> setThis2(List<Person> entitys) {
		List<AddressBean> beans = new ArrayList<AddressBean>();
		if(!StringUtil.isListEmpty(entitys)) {
			for (Person entity : entitys) {
				AddressBean bean = new AddressBean();
				bean.setAvatar("");
				bean.setId(entity.getId());
				bean.setIsorgan(false);
				bean.setName(entity.getName());
				beans.add(bean);
			}
		}
		return beans;
	}
}
