package com.cat.system.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import com.cat.boot.model.BaseEntity;
import com.cat.dictionary.model.Dictionary;

@Entity
@Table(name = "Org_Post_Information")
@BatchSize(size = 50)
@DynamicInsert
public class PostInformation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8567384726887936704L;

	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 40)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
	@BatchSize(size = 50)
	private Person person;

	/** 工作经历 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	@BatchSize(size = 50)
	private Organization organization;
	
	/**
	 * 工作单位
	 */
	@Column(length = 200)
	private String workplace;

	/**
	 * 员工类型 全职 退休返聘 离职
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "isleaf")
	@BatchSize(size = 50)
	private Dictionary isleaf;
	
	/** 员工状态 正式 试用*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_state")
	@BatchSize(size = 50)
	private Dictionary poststate;
	
	/**
	 * 职务
	 */
	@Column(length = 30)
	private String duty;

	/**
	 * 入职时间
	 */
	@Column(length = 30)
	private String entrytime;

	/**
	 * 离开时间
	 */
	@Column(length = 30)
	private String leaftime;
	
	/** 岗位（多选） */
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "Org_Information_Post", joinColumns = {
			@JoinColumn(name = "Post_Information_ID") }, inverseJoinColumns = { @JoinColumn(name = "post_ID") })
	@OrderBy("xh")
	@BatchSize(size = 50)
	private Set<Post> posts = new HashSet<Post>();

	/** 当前任职序号是999 */
	@Column
	private Integer xh = 1;
	
	/** 司内=PRO001 司外=PRO002 */
	@Column(length = 30)
	private String property;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return this.id;
	}

	public Dictionary getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(Dictionary isleaf) {
		this.isleaf = isleaf;
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
	
	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public Dictionary getPoststate() {
		return poststate;
	}

	public void setPoststate(Dictionary poststate) {
		this.poststate = poststate;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
