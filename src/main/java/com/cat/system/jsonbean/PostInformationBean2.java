package com.cat.system.jsonbean;

import java.util.ArrayList;
import java.util.List;

import com.cat.boot.jsonbean.BaseAppBean;
import com.cat.boot.model.BaseEntity;
import com.cat.boot.service.BaseService;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.StringUtil;
import com.cat.dictionary.model.Dictionary;
import com.cat.system.model.Person;
import com.cat.system.model.PostInformation;

public class PostInformationBean2 extends BaseAppBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7620252497638338753L;

	private String person_id;

	private String person_name;
	
	private String organ_id;

	private String organ_name;
	
	private String leaf_code;

	private String leaf_data;
	
	private String poststate_code;
	
	private String poststate_data;
	
	private List<String> posts = new ArrayList<String>();
	
	private List<String> post_names = new ArrayList<String>();
	
	private String entrytime;
	
	private String leaftime;
	
	private String property;
	
	private Integer xh =1;

	private String duty;
	
	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getOrgan_id() {
		return organ_id;
	}

	public void setOrgan_id(String organ_id) {
		this.organ_id = organ_id;
	}

	public String getOrgan_name() {
		return organ_name;
	}

	public void setOrgan_name(String organ_name) {
		this.organ_name = organ_name;
	}

	public String getLeaf_code() {
		return leaf_code;
	}

	public void setLeaf_code(String leaf_code) {
		this.leaf_code = leaf_code;
	}

	public String getLeaf_data() {
		return leaf_data;
	}

	public void setLeaf_data(String leaf_data) {
		this.leaf_data = leaf_data;
	}

	public String getPoststate_code() {
		return poststate_code;
	}

	public void setPoststate_code(String poststate_code) {
		this.poststate_code = poststate_code;
	}

	public String getPoststate_data() {
		return poststate_data;
	}

	public void setPoststate_data(String poststate_data) {
		this.poststate_data = poststate_data;
	}

	public List<String> getPosts() {
		return posts;
	}

	public void setPosts(List<String> posts) {
		this.posts = posts;
	}

	public List<String> getPost_names() {
		return post_names;
	}

	public void setPost_names(List<String> post_names) {
		this.post_names = post_names;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getLeaftime() {
		return leaftime;
	}

	public void setLeaftime(String leaftime) {
		this.leaftime = leaftime;
	}
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public PostInformationBean2() {

	}

	public PostInformationBean2(BaseEntity entity) {
		super(entity);
	}
	
	@SuppressWarnings("unchecked")
	public static PostInformationBean2 setThis(PostInformation entity,BaseService baseService) {

		PostInformationBean2 bean = new PostInformationBean2(entity);
		bean.setId(entity.getId());
		
		bean.setLeaf_code(entity.getIsleaf() != null ? entity.getIsleaf().getCode() : "");
		bean.setLeaf_data(entity.getIsleaf() != null ? entity.getIsleaf().getName() : "");
		bean.setEntrytime(entity.getEntrytime());
		bean.setPoststate_data(entity.getPoststate()!=null ? entity.getPoststate().getName() : "");
		bean.setPoststate_code(entity.getPoststate()!=null ? entity.getPoststate().getCode() : "");
		bean.setLeaftime(entity.getLeaftime());
		bean.setPerson_id(entity.getPerson()!=null ? entity.getPerson().getId() : "");
		bean.setPerson_name(entity.getPerson()!=null ? entity.getPerson().getName() : "");
		bean.setProperty(entity.getProperty());
		bean.setXh(entity.getXh());
		if("PRO001".equals(entity.getProperty())) {
			if(entity.getOrganization()!=null) {
				bean.setOrgan_id(entity.getOrganization() != null ? entity.getOrganization().getId() : "");
				bean.setOrgan_name(entity.getOrganization() != null ? entity.getOrganization().getName() : "");
			} else {
				bean.setOrgan_name(entity.getWorkplace());
			}
			if(StringUtil.isEmpty(entity.getDuty())) {
				List<String> posts = (List<String>) baseService.getList("Org_Information_Post", null, false, "post_ID",
					NameQueryUtil.setParams("Post_Information_ID", entity.getId()));
				bean.setPosts(posts);
				if(!StringUtil.isListEmpty(posts)) {
					List<String> names = (List<String>)baseService.getList("Org_Post", "o.xh asc", true,"name",NameQueryUtil.setParams("id",posts));
					//bean.setPost_names(names);
					bean.setDuty(StringUtil.listToString(names));
				}
			} else {
				bean.setDuty(entity.getDuty());
			}
		} else {
			bean.setOrgan_name(entity.getWorkplace());
			//bean.getPost_names().add(entity.getDuty());
			bean.setDuty(entity.getDuty());
		}
		
		return bean;
	}

	public static List<PostInformationBean2> setThis(List<PostInformation> entitys,BaseService baseService) {
		List<PostInformationBean2> beans = new ArrayList<PostInformationBean2>();
		if (!StringUtil.isListEmpty(entitys)) {
			for (PostInformation bean : entitys) {
				beans.add(setThis(bean,baseService));
			}
		}
		return beans;
	}
	
	public static void clone(PostInformation bean, PostInformationBean2 entity, BaseService baseService) {
		bean.setWorkplace(entity.getOrgan_name());
		bean.setDuty(entity.getDuty());
		bean.setPerson((Person)baseService.findById(Person.class, entity.getPerson_id()));
		bean.setLeaftime(entity.getLeaftime());
		bean.setEntrytime(entity.getEntrytime());
		bean.setProperty(entity.getProperty());
		bean.setXh(entity.getXh());
		bean.setIsleaf((Dictionary)baseService.getSimple("Dictionary", null, true,NameQueryUtil.setParams("typeCode","POSTTYPE","name","离职")));
	}
}
