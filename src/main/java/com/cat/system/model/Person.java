package com.cat.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;

import com.cat.dictionary.model.Dictionary;

@Entity
@Table(name = "Org_Person")
@BatchSize(size = 50)
@DynamicInsert
public class Person extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2360610594649791315L;
	
	/** 员工编号 */
	private String code;

	/** 身份证号 */
	@Column(length = 100,nullable=false)
	private String certnum;
	
	/** 有效期 */
	@Column(length = 30)
	private String expiredate;
	
	/** 入职时间 */
	@Column(length = 30)
	private String entrytime;
	
	/** 试用期 */
	@Column(nullable=true)
	private Integer probation;
	
	/** 转正时间 */
	@Column(length = 30)
	private String regulartime;
	
	/** 考核分数 */
	@Column(nullable=true)
	private Double checkpoint;
	
	/** 出身日期 */
	@Column(length = 30)
	private String birthday;
	
	/** 性别 */
	@Column(length = 10)
	private String sex;
	
	/** 民族 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "race")
	@BatchSize(size = 50)
	private Dictionary race;
	
	/** 身份证地址 */
	@Column(length = 200)
	private String regisplace;
	
	/** 居住地址 */
	@Column(length = 200)
	private String address;
	
	/** 婚姻状况 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marital_status")
	@BatchSize(size = 50)
	private Dictionary maritalstatus;
	
	/** 初次工作日期 */
	@Column(length = 200)
	private String fristday;
	
	/** 学历 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "education")
	@BatchSize(size = 50)
	private Dictionary education;
	
	/** 毕业院校 */
	@Column(length = 200)
	private String school;
	
	/** 毕业时间 */
	@Column(length = 30)
	private String graduatetime;
	
	/** 专业 */
	@Column(length = 30)
	private String major;
	
	/** 任职店长时间 */
	@Column(length = 30)
	private String directortime;
	
	/** 银行卡号 */
	@Column(length = 50)
	private String cardno;
	
	/** 开户行 */
	@Column(length = 200)
	private String openbank;
	
	/** 紧急联系人 */
	@Column(length = 50)
	private String emergencyname;
	
	/** 紧急联系人电话 */
	@Column(length = 50)
	private String emergencyphone;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Double getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(Double checkpoint) {
		this.checkpoint = checkpoint;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Dictionary getRace() {
		return race;
	}

	public void setRace(Dictionary race) {
		this.race = race;
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

	public Dictionary getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(Dictionary maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getFristday() {
		return fristday;
	}

	public void setFristday(String fristday) {
		this.fristday = fristday;
	}

	public Dictionary getEducation() {
		return education;
	}

	public void setEducation(Dictionary education) {
		this.education = education;
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
}
