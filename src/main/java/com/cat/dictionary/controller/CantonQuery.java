package com.cat.dictionary.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.boot.jsonbean.BaseQueryHelp;
import com.cat.boot.jsonbean.QueryResultBean;
import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.service.BaseNqtQuery;
import com.cat.dictionary.jsonbean.CantonBean;
import com.cat.dictionary.model.Canton;

@RestController
@Scope("prototype")
@RequestMapping("/canton")
public class CantonQuery extends BaseNqtQuery<Canton>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5757013997340700553L;
	
	@Override
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String query(BaseQueryHelp parms) throws Exception {
		excuteQuery(parms);
		return ResultBean.getSucess(new QueryResultBean(parms, CantonBean.setThis(results)));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id) {
		return super.delete(id);
	}

}
