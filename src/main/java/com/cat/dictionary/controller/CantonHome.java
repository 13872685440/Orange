package com.cat.dictionary.controller;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.service.BaseHome;
import com.cat.boot.util.StringUtil;
import com.cat.dictionary.jsonbean.CantonBean;
import com.cat.dictionary.model.Canton;

@RestController
@RequestMapping("/canton")
public class CantonHome extends BaseHome<Canton>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2818650234529546380L;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam String id) {
		if (!StringUtil.isEmpty(id)) {
			Canton entity = findById(id);
			return ResultBean.getSucess(CantonBean.setThis(entity));
		} else {
			return ResultBean.getSucess(CantonBean.setThis(new Canton()));
		}
	}
	
	@RequestMapping(value = "/addlower", method = RequestMethod.GET)
	public String addlower(@RequestParam String id) {
		Canton entity = new Canton();
		Canton superior = (Canton) baseService.findById(Canton.class, id);
		entity.setSuperior(superior);

		return ResultBean.getSucess(CantonBean.setThis(entity));
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(CantonBean entity) throws Exception {
		Canton bean = findById(entity.getCode());
		if (bean == null) {
			bean = new Canton();
			bean.setCode(entity.getCode());
		}
		CantonBean.clone(bean, entity, baseService);
		baseService.save(bean);
		return ResultBean.getSucess("sucess");
	}
}
