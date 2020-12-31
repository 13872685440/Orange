package com.cat.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.boot.jsonbean.BaseQueryHelp;
import com.cat.boot.jsonbean.PropParamBean;
import com.cat.boot.jsonbean.QueryResultBean;
import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.jsonbean.TreeBean;
import com.cat.boot.jsonbean.TreeParmBean;
import com.cat.boot.service.BaseNqtQuery;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.StringUtil;
import com.cat.system.jsonbean.PostInformationBean;
import com.cat.system.jsonbean.PostInformationBean2;
import com.cat.system.model.PostInformation;

@RestController
@Scope("prototype")
@RequestMapping("/postinformation")
public class PostInformationQuery extends BaseNqtQuery<PostInformation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5295892878289502288L;
 
	@Override
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String query(BaseQueryHelp parms) throws Exception {
		excuteQuery(parms);
		return ResultBean.getSucess(new QueryResultBean(parms, PostInformationBean.setThis(results,baseService)));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query_postinformation", method = RequestMethod.GET)
	public String query_family(PostInformationBean2 bean) throws Exception {
		List<PostInformation> p = (List<PostInformation>) baseService.getList("PostInformation", "o.xh desc", true,NameQueryUtil.setParams("person.id",bean.getPerson_id()));
		
		return ResultBean.getSucess(PostInformationBean2.setThis(p,baseService));
	}
	
	@RequestMapping(value = "/delete_postinformation", method = RequestMethod.POST)
	public String delete_postinformation(@RequestParam String id) throws Exception {
		return delete(PostInformation.class,id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id) {
		baseService.delete4Prop("PostInformation", false, NameQueryUtil.setParams("person.id",id));
		baseService.delete4Prop("Person", false, NameQueryUtil.setParams("id",id));
		return ResultBean.getSucess("");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public String getTree(TreeParmBean bean) throws Exception {
		List<PropParamBean> beans = new ArrayList<PropParamBean>();
		if (!StringUtil.isEmpty(bean.getRoot())) {
			beans.add(new PropParamBean("like", "and", "code", bean.getRoot() + "%"));
		}
		List<Object[]> orgs = (List<Object[]>) baseService.getList("Org_Organization", "o.code asc", true,
				"o.code,o.name,o.sc_id", beans);

		if (!StringUtil.isListEmpty(orgs)) {
			List<TreeBean> tree = TreeBean.getTree(orgs, null);
			return ResultBean.getSucess(tree);
		}
		return ResultBean.getResultBean("200", "", "");
	}

}
