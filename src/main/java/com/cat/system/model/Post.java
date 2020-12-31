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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import com.cat.boot.model.BaseEntity;
import com.cat.dictionary.model.Dictionary;

@Entity
@Table(name = "Org_Post")
@BatchSize(size = 50)
@DynamicInsert
public class Post extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -903411746936167148L;
	
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 40)
	private String id;
	
	/** 岗位名称 */
	@Column(length = 50,nullable=true)
	private String name;
	
	@Column
	private Integer xh = 1;
	
	/** 组织机构类型 */
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "Org_Post_Zzjglx", joinColumns = {
			@JoinColumn(name = "Post_ID") }, inverseJoinColumns = { @JoinColumn(name = "zzjglx_ID") })
	@OrderBy("code")
	@BatchSize(size = 50)
	private Set<Dictionary> zzjglxs = new HashSet<Dictionary>();

	/** 角色 */
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "org_Post_Role", joinColumns = {
			@JoinColumn(name = "Post_ID") }, inverseJoinColumns = { @JoinColumn(name = "role_ID") })
	@OrderBy("name")
	@BatchSize(size = 50)
	private Set<Role> roles = new HashSet<Role>();
	
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

	public Set<Dictionary> getZzjglxs() {
		return zzjglxs;
	}

	public void setZzjglxs(Set<Dictionary> zzjglxs) {
		this.zzjglxs = zzjglxs;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return this.id;
	}
}
