package com.cat.system.jsonbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cat.boot.jsonbean.BaseAppBean;
import com.cat.boot.model.BaseEntity;
import com.cat.boot.service.BaseService;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.PassWordUtil;
import com.cat.boot.util.StringUtil;
import com.cat.dictionary.model.Dictionary;
import com.cat.system.model.Organization;
import com.cat.system.model.Person;
import com.cat.system.model.PostInformation;

public class PostInformationBean extends BaseAppBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8191109316056145682L;
	
	private String code;

	private String person_id;

	private String person_name;

	private String organ_id;

	private String organ_name;
	
	private String loginName;
	
	private String certnum;
	
	private String sex;
	
	private String birthday;
	
	private String expiredate;
	
	private String race_code;
	
	private String race_name;
	
	private String entrytime;
	
	private Integer probation;
	
	private String regulartime;
	
	private String regisplace;
	
	private String address;
	
	private Double checkpoint;
	
	private String maritalstatus_code;
	
	private String maritalstatus_name;
	
	private String education_code;
	
	private String education_name;
	
	private String school;
	
	private String graduatetime;
	
	private String fristday;
	
	private String major;
	
	private String directortime;
	
	private String cardno;
	
	private String openbank;
	
	private String emergencyname;
	
	private String emergencyphone;

	private String leaf_code;

	private String leaf_data;

	private String phone;
	
	private String poststate_code;
	
	private String poststate_name;
	
	private List<String> posts = new ArrayList<String>();
	
	private List<String> post_names = new ArrayList<String>();
	
	private List<String> roles = new ArrayList<String>();
	
	private List<String> permissions = new ArrayList<String>();

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCertnum() {
		return certnum;
	}

	public void setCertnum(String certnum) {
		this.certnum = certnum;
	}

	public String getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}

	public String getRace_code() {
		return race_code;
	}

	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public Integer getProbation() {
		return probation;
	}

	public void setProbation(Integer probation) {
		this.probation = probation;
	}

	public String getRegulartime() {
		return regulartime;
	}

	public void setRegulartime(String regulartime) {
		this.regulartime = regulartime;
	}

	public String getRegisplace() {
		return regisplace;
	}

	public void setRegisplace(String regisplace) {
		this.regisplace = regisplace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(Double checkpoint) {
		this.checkpoint = checkpoint;
	}

	public String getMaritalstatus_code() {
		return maritalstatus_code;
	}

	public void setMaritalstatus_code(String maritalstatus_code) {
		this.maritalstatus_code = maritalstatus_code;
	}

	public String getEducation_code() {
		return education_code;
	}

	public void setEducation_code(String education_code) {
		this.education_code = education_code;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getGraduatetime() {
		return graduatetime;
	}

	public void setGraduatetime(String graduatetime) {
		this.graduatetime = graduatetime;
	}

	public String getFristday() {
		return fristday;
	}

	public void setFristday(String fristday) {
		this.fristday = fristday;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDirectortime() {
		return directortime;
	}

	public void setDirectortime(String directortime) {
		this.directortime = directortime;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getOpenbank() {
		return openbank;
	}

	public void setOpenbank(String openbank) {
		this.openbank = openbank;
	}

	public String getEmergencyname() {
		return emergencyname;
	}

	public void setEmergencyname(String emergencyname) {
		this.emergencyname = emergencyname;
	}

	public String getEmergencyphone() {
		return emergencyphone;
	}

	public void setEmergencyphone(String emergencyphone) {
		this.emergencyphone = emergencyphone;
	}
	
	public String getPoststate_code() {
		return poststate_code;
	}

	public void setPoststate_code(String poststate_code) {
		this.poststate_code = poststate_code;
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

	public String getRace_name() {
		return race_name;
	}

	public void setRace_name(String race_name) {
		this.race_name = race_name;
	}

	public String getMaritalstatus_name() {
		return maritalstatus_name;
	}

	public void setMaritalstatus_name(String maritalstatus_name) {
		this.maritalstatus_name = maritalstatus_name;
	}

	public String getEducation_name() {
		return education_name;
	}

	public void setEducation_name(String education_name) {
		this.education_name = education_name;
	}

	public String getPoststate_name() {
		return poststate_name;
	}

	public void setPoststate_name(String poststate_name) {
		this.poststate_name = poststate_name;
	}

	public PostInformationBean() {

	}

	public PostInformationBean(BaseEntity entity) {
		super(entity);
	}

	@SuppressWarnings("unchecked")
	public static PostInformationBean setThis(PostInformation entity,BaseService baseService) {

		PostInformationBean bean = new PostInformationBean(entity);
		bean.setId(entity.getId());
		
		bean.setOrgan_id(entity.getOrganization() != null ? entity.getOrganization().getId() : "");
		bean.setOrgan_name(entity.getOrganization() != null ? entity.getOrganization().getName() : "");
		bean.setLeaf_code(entity.getIsleaf() != null ? entity.getIsleaf().getCode() : "");
		bean.setLeaf_data(entity.getIsleaf() != null ? entity.getIsleaf().getName() : "");
		bean.setEntrytime(entity.getEntrytime());
		bean.setPoststate_name(entity.getPoststate()!=null ? entity.getPoststate().getName() : "");
		bean.setPoststate_code(entity.getPoststate()!=null ? entity.getPoststate().getCode() : "");

		List<String> posts = (List<String>) baseService.getList("Org_Information_Post", null, false, "post_ID",
					NameQueryUtil.setParams("Post_Information_ID", entity.getId()));
		bean.setPosts(posts);
		if(!StringUtil.isListEmpty(posts)) {
			List<String> names = (List<String>)baseService.getList("Org_Post", "o.xh asc", true,"name",NameQueryUtil.setParams("id",posts));
			bean.setPost_names(names);
		}
		if(entity.getPerson()!=null) {
			bean.setPerson_id(entity.getPerson().getId());
			bean.setPerson_name(entity.getPerson().getName());
			Person p = entity.getPerson();
			bean.setLoginName(p.getLoginName());
			bean.setCertnum(p.getCertnum());
			bean.setExpiredate(p.getExpiredate());
			bean.setRace_name(p.getRace()!=null ? p.getRace().getName() : "");
			bean.setRace_code(p.getRace()!=null ? p.getRace().getCode() : "");
			bean.setProbation(p.getProbation());
			bean.setRegulartime(p.getRegulartime());
			bean.setRegisplace(p.getRegisplace());
			bean.setAddress(p.getAddress());
			bean.setCheckpoint(p.getCheckpoint());
			bean.setMaritalstatus_name(p.getMaritalstatus()!=null ? p.getMaritalstatus().getName() : "");
			bean.setMaritalstatus_code(p.getMaritalstatus()!=null ? p.getMaritalstatus().getCode() : "");
			bean.setEducation_name(p.getEducation()!=null ? p.getEducation().getName() : "");
			bean.setEducation_code(p.getEducation()!=null ? p.getEducation().getCode() : "");
			bean.setSchool(p.getSchool());
			bean.setGraduatetime(p.getGraduatetime());
			bean.setFristday(p.getFristday());
			bean.setMajor(p.getMajor());
			bean.setDirectortime(p.getDirectortime());
			bean.setCardno(p.getCardno());
			bean.setOpenbank(p.getOpenbank());
			bean.setEmergencyname(p.getEmergencyname());
			bean.setEmergencyphone(p.getEmergencyphone());
			bean.setCode(p.getCode());
			bean.setPhone(p.getPhone());
			bean.setSex(p.getSex());
			bean.setBirthday(p.getBirthday());
		}
		
		return bean;
	}

	public static List<PostInformationBean> setThis(List<PostInformation> entitys,BaseService baseService) {
		List<PostInformationBean> beans = new ArrayList<PostInformationBean>();
		if (!StringUtil.isListEmpty(entitys)) {
			for (PostInformation bean : entitys) {
				beans.add(setThis(bean,baseService));
			}
		}
		return beans;
	}

	public static void clone(PostInformation bean, PostInformationBean entity, BaseService baseService) {
		bean.setPerson(savePerson(entity, baseService));
		Organization organ = (Organization) baseService.findById(Organization.class, entity.getOrgan_id());
		bean.setOrganization(organ);
		bean.setIsleaf((Dictionary) baseService.findById(Dictionary.class, entity.getLeaf_code()));
		bean.setEntrytime(entity.getEntrytime());
		bean.setPoststate((Dictionary) baseService.findById(Dictionary.class, entity.getPoststate_code()));
	}

	static Person savePerson(PostInformationBean p, BaseService baseService) {
		if (StringUtil.isEmpty(p.getPerson_id())) {
			Person bean = new Person();
			bean.setLoginName(p.getLoginName());
			bean.setCertnum(p.getCertnum());
			bean.setExpiredate(p.getExpiredate());
			bean.setRace((Dictionary) baseService.findById(Dictionary.class, p.getRace_code()));
			bean.setProbation(p.getProbation());
			bean.setRegulartime(p.getRegulartime());
			bean.setRegisplace(p.getRegisplace());
			bean.setAddress(p.getAddress());
			bean.setCheckpoint(p.getCheckpoint());
			bean.setMaritalstatus((Dictionary) baseService.findById(Dictionary.class, p.getMaritalstatus_code()));
			bean.setEducation((Dictionary) baseService.findById(Dictionary.class, p.getEducation_code()));
			bean.setSchool(p.getSchool());
			bean.setGraduatetime(p.getGraduatetime());
			bean.setFristday(p.getFristday());
			bean.setMajor(p.getMajor());
			bean.setDirectortime(p.getDirectortime());
			bean.setCardno(p.getCardno());
			bean.setOpenbank(p.getOpenbank());
			bean.setEmergencyname(p.getEmergencyname());
			bean.setEmergencyphone(p.getEmergencyphone());
			bean.setCode(p.getLoginName().replace("ht", ""));
			bean.setPhone(p.getPhone());
			bean.setSex(p.getSex());
			bean.setBirthday(p.getBirthday());
			bean.setName(p.getPerson_name());
			bean.setCode(p.getLoginName().replace("ht", ""));
			
			Map<String, String> pwds = PassWordUtil.entryptPassword("666666", true);
			bean.setPwd(pwds.get("pwd"));
			bean.setSalt(pwds.get("salt"));
			baseService.save(bean, false);
			return bean;
		} else {
			Person bean = (Person) baseService.findById(Person.class, p.getPerson_id());
			bean.setLoginName(p.getLoginName());
			bean.setCertnum(p.getCertnum());
			bean.setExpiredate(p.getExpiredate());
			bean.setRace((Dictionary) baseService.findById(Dictionary.class, p.getRace_code()));
			bean.setProbation(p.getProbation());
			bean.setRegulartime(p.getRegulartime());
			bean.setRegisplace(p.getRegisplace());
			bean.setAddress(p.getAddress());
			bean.setCheckpoint(p.getCheckpoint());
			bean.setMaritalstatus((Dictionary) baseService.findById(Dictionary.class, p.getMaritalstatus_code()));
			bean.setEducation((Dictionary) baseService.findById(Dictionary.class, p.getEducation_code()));
			bean.setSchool(p.getSchool());
			bean.setGraduatetime(p.getGraduatetime());
			bean.setFristday(p.getFristday());
			bean.setMajor(p.getMajor());
			bean.setDirectortime(p.getDirectortime());
			bean.setCardno(p.getCardno());
			bean.setOpenbank(p.getOpenbank());
			bean.setEmergencyname(p.getEmergencyname());
			bean.setEmergencyphone(p.getEmergencyphone());
			bean.setCode(p.getCode());
			bean.setPhone(p.getPhone());
			bean.setSex(p.getSex());
			bean.setBirthday(p.getBirthday());
			bean.setName(p.getPerson_name());
			bean.setCode(p.getLoginName().replace("ht", ""));
			baseService.save(bean, true);
			return bean;
		}
	}

}
