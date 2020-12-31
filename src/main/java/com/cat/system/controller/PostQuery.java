package com.cat.system.controller;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.boot.jsonbean.BaseQueryHelp;
import com.cat.boot.jsonbean.QueryResultBean;
import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.service.BaseNqtQuery;
import com.cat.boot.util.NameQueryUtil;
import com.cat.system.jsonbean.PostBean;
import com.cat.system.model.Post;

@RestController
@Scope("prototype")
@RequestMapping("/post")
public class PostQuery extends BaseNqtQuery<Post>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2798235782225405038L;

	@Override
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String query(BaseQueryHelp parms) throws Exception {
		excuteQuery(parms);
		return ResultBean.getSucess(new QueryResultBean(parms, PostBean.setThis(results)));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id) {
		try {
			baseService.delete4Prop("Org_Post_Zzjglx", true, NameQueryUtil.setParams("Post_ID", id));
			baseService.delete4Prop("org_Post_Role", true, NameQueryUtil.setParams("Post_ID", id));
			baseService.delete(baseService.findById(Post.class, id));
			return ResultBean.getSucess("删除成功");
		}catch (Exception e) {
			e.printStackTrace();
			return ResultBean.getResultBean("400", "", "删除失败");
		}
	}
}
