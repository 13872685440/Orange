package com.cat.dictionary.jsonbean;

import java.util.ArrayList;
import java.util.List;

import com.cat.boot.jsonbean.BaseAppBean;
import com.cat.boot.model.BaseEntity;
import com.cat.boot.service.BaseService;
import com.cat.boot.util.StringUtil;
import com.cat.dictionary.model.Canton;

public class CantonBean extends BaseAppBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5012455713294408796L;

	private String code;

	private String statsCode;

	private String spell;

	private String wn;

	private String name;

	private String superior_name;
	
	private String superior_code;

	private String sn;

	private boolean isLeaf;

	private String extendField;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatsCode() {
		return statsCode;
	}

	public void setStatsCode(String statsCode) {
		this.statsCode = statsCode;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getWn() {
		return wn;
	}

	public void setWn(String wn) {
		this.wn = wn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperior_name() {
		return superior_name;
	}

	public void setSuperior_name(String superior_name) {
		this.superior_name = superior_name;
	}

	public String getSuperior_code() {
		return superior_code;
	}

	public void setSuperior_code(String superior_code) {
		this.superior_code = superior_code;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getExtendField() {
		return extendField;
	}

	public void setExtendField(String extendField) {
		this.extendField = extendField;
	}
	
	public CantonBean() {

	}

	public CantonBean(BaseEntity entity) {
		super(entity);
	}

	public static CantonBean setThis(Canton entity) {
		CantonBean bean = new CantonBean(entity);
		bean.setCode(entity.getCode());
		bean.setStatsCode(entity.getStatsCode());
		bean.setSpell(entity.getSpell());
		bean.setLeaf(entity.isLeaf());
		bean.setName(entity.getName());
		bean.setWn(entity.getWn());
		bean.setSn(entity.getSn());
		bean.setExtendField(entity.getExtendField());
		bean.setSuperior_code(entity.getSuperior() != null ? entity.getSuperior().getCode() : "");
		bean.setSuperior_name(entity.getSuperior() != null ? entity.getSuperior().getName() : "");

		return bean;
	}

	public static List<CantonBean> setThis(List<Canton> entitys) {
		List<CantonBean> beans = new ArrayList<CantonBean>();
		if (!StringUtil.isListEmpty(entitys)) {
			for (Canton bean : entitys) {
				beans.add(setThis(bean));
			}
		}
		return beans;
	}

	public static void clone(Canton bean, CantonBean entity, BaseService baseService) {
		bean.setCode(entity.getCode());
		bean.setStatsCode(entity.getStatsCode());
		bean.setSpell(entity.getSpell());
		bean.setLeaf(entity.isLeaf());
		bean.setName(entity.getName());
		bean.setWn(entity.getWn());
		bean.setSn(entity.getSn());
		bean.setExtendField(entity.getExtendField());

		if (!StringUtil.isEmpty(entity.getSuperior_code())) {
			bean.setSuperior((Canton) baseService.findById(Canton.class, entity.getSuperior_code()));
		}
	}
}
