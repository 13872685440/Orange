package com.cat.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.service.BaseHome;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.StringUtil;
import com.cat.system.jsonbean.PostBean;
import com.cat.system.model.Organization;
import com.cat.system.model.Post;

@RestController
@RequestMapping("/post")
public class PostHome extends BaseHome<Post>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4426748563657560015L;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam String id) {
		if (!StringUtil.isEmpty(id)) {
			Post entity = findById(id);
			return ResultBean.getSucess(PostBean.setThis(entity));
		} else {
			return ResultBean.getSucess(PostBean.setThis(new Post()));
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(PostBean entity) throws Exception {
		Post bean = new Post();
		if (!StringUtil.isEmpty(entity.getId())) {
			bean = findById(entity.getId());
		}
		PostBean.clone(bean, entity, baseService);
		baseService.save(bean);
		return ResultBean.getSucess("sucess");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(@RequestParam String organ_id) throws Exception {
		Organization organ = (Organization)baseService.findById(Organization.class, organ_id);
		List<String> s = (List<String>)baseService.
				getList("Org_Post_Zzjglx", null, false,"Post_ID", NameQueryUtil.setParams("zzjglx_ID",organ.getZzlx().getCode()));
		if(!StringUtil.isListEmpty(s)) {
			List<Post> posts = (List<Post>)baseService.getList("Post", "o.xh asc", true,NameQueryUtil.setParams("id",s));
			return ResultBean.getSucess(PostBean.setThis(posts));
		}
		return ResultBean.getSucess(PostBean.setThis(new ArrayList<Post>()));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	public String get(@RequestParam List<String> posts) throws Exception {
		if(!StringUtil.isListEmpty(posts)) {
			List<String> s = (List<String>)baseService.
					getList("org_Post_Role", null, false,"role_ID", NameQueryUtil.setParams("Post_ID",posts));
			if(!StringUtil.isListEmpty(s)) {
				List<String> names = (List<String>)baseService.
						getList("SYS_Role", null, false,"des", NameQueryUtil.setParams("name",s));
				return ResultBean.getSucess(names);
			}
		}
		return ResultBean.getSucess(new ArrayList<String>());
	}
}
