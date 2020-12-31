package com.cat.system.jsonbean;

import java.util.ArrayList;
import java.util.List;

import com.cat.boot.jsonbean.BaseAppBean;
import com.cat.boot.service.BaseService;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.StringUtil;
import com.cat.dictionary.model.Dictionary;
import com.cat.system.model.Post;
import com.cat.system.model.Role;

public class PostBean extends BaseAppBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7320672214259624135L;

	private String id;

	private String name;
	
	private Integer xh = 1;
	
	private List<String> zzjglxs = new ArrayList<String>();
	
	private List<String> zzjglx_names = new ArrayList<String>();
	
	private List<String> roles = new ArrayList<String>();
	
	private List<String> role_names = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public List<String> getZzjglxs() {
		return zzjglxs;
	}

	public void setZzjglxs(List<String> zzjglxs) {
		this.zzjglxs = zzjglxs;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getZzjglx_names() {
		return zzjglx_names;
	}

	public void setZzjglx_names(List<String> zzjglx_names) {
		this.zzjglx_names = zzjglx_names;
	}

	public List<String> getRole_names() {
		return role_names;
	}

	public void setRole_names(List<String> role_names) {
		this.role_names = role_names;
	}

	public static PostBean setThis(Post entity) {
		PostBean bean = new PostBean();
		bean.setName(entity.getName());
		bean.setXh(entity.getXh());
		bean.setId(entity.getId());
		if(!StringUtil.isSetEmpty(entity.getZzjglxs())) {
			for (Dictionary zzjglx : entity.getZzjglxs()) {
				bean.getZzjglxs().add(zzjglx.getId());
				bean.getZzjglx_names().add(zzjglx.getName());
			}
		}
		if(!StringUtil.isSetEmpty(entity.getRoles())) {
			for (Role role : entity.getRoles()) {
				bean.getRoles().add(role.getName());
				bean.getRole_names().add(role.getDes());
			}
		}
		return bean;
	}
	
	public static List<PostBean> setThis(List<Post> entitys) {
		List<PostBean> beans = new ArrayList<PostBean>();
		if (!StringUtil.isListEmpty(entitys)) {
			for (Post bean : entitys) {
				beans.add(setThis(bean));
			}
		}
		return beans;
	}

	@SuppressWarnings("unchecked")
	public static void clone(Post bean, PostBean entity, BaseService baseService) {
		bean.setXh(entity.getXh());
		bean.setName(entity.getName());
		
		if (!StringUtil.isListEmpty(entity.getZzjglxs())) {
			
			List<Dictionary> as = (List<Dictionary>) baseService.getList("Dictionary", null, true,
					NameQueryUtil.setParams("code", entity.getZzjglxs()));
			if (!StringUtil.isListEmpty(as)) {
				bean.getZzjglxs().clear();
				bean.getZzjglxs().addAll(as);
			} else {
				bean.setZzjglxs(null);;
			}
		} else {
			bean.setZzjglxs(null);;
		}
		if (!StringUtil.isListEmpty(entity.getRoles())) {
			
			List<Role> as = (List<Role>) baseService.getList("Role", null, true,
					NameQueryUtil.setParams("name", entity.getRoles()));
			if (!StringUtil.isListEmpty(as)) {
				bean.getRoles().clear();
				bean.getRoles().addAll(as);
			} else {
				bean.setRoles(null);;
			}
		} else {
			bean.setRoles(null);;
		}
	}
}
