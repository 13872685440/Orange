package com.cat.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cat.boot.jsonbean.UserBean;
import com.cat.boot.service.BaseService;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.StringUtil;
import com.cat.system.jsonbean.PostInformationBean;
import com.cat.system.model.Organization;
import com.cat.system.model.PostInformation;

@RestController
public class UserInfoService {

	@Autowired
	public BaseService baseService;

	/**
	 * 根据userId查询任职情况 以及任职时对应的角色
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PostInformationBean> getPostInformation(String userId) {
		List<PostInformation> ps = (List<PostInformation>) baseService.getList("PostInformation",
				"o.isleaf.code asc, o.ct asc", true, NameQueryUtil.setParams("person.id", userId, "isleaf.name",
						Arrays.asList(new String[] { "全职", "退休返聘" }),"xh",999));
		List<PostInformationBean> beans = new ArrayList<PostInformationBean>();
		if (!StringUtil.isListEmpty(ps)) {
			for (PostInformation p : ps) {
				PostInformationBean bean = PostInformationBean.setThis(p,baseService);
				if(!StringUtil.isListEmpty(bean.getPosts())) {
					// 查询岗位角色
					List<String> roles = (List<String>)baseService.getList("org_Post_Role", null, false, 
							"role_ID",NameQueryUtil.setParams("Post_ID",bean.getPosts()));
					bean.setRoles(roles);
				}
				beans.add(bean);
			}
		}
		return beans;
	}

	/**
	 * 根据role和organ查询用户信息 当organ为空时 根据role查询 role不能为空
	 * 
	 * @param role
	 *            or permission
	 * @param organ
	 * 
	 * @param fiter_part_time
	 *            过滤兼职
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserBean> getUserBean(String roleName, String organId, boolean fiter_part_time) {
		List<UserBean> beans = new ArrayList<UserBean>();
		// 根据角色查询岗位
		List<String> posts = (List<String>)baseService.getList("org_Post_Role", null, false,"Post_ID",NameQueryUtil.setParams("role_ID",roleName));
		// 根据岗位查询任职
		if(!StringUtil.isListEmpty(posts)) {
			List<String> postInformations =  (List<String>)baseService.getList("Org_Information_Post", 
					null, false,"Post_Information_ID",NameQueryUtil.setParams("post_ID",posts));
			// 根据任职查询在职的
			if(!StringUtil.isListEmpty(postInformations)) {
				List<PostInformation> is = (List<PostInformation>) baseService.getList("PostInformation", null, true,
						NameQueryUtil.setParams("xh",999,"id",postInformations));
				if(!StringUtil.isListEmpty(is)) {
					for (PostInformation pi : is) {
						UserBean bean = new UserBean();
						if(StringUtil.isEmpty(organId)) {
							bean.setId(pi.getPerson().getId());
							bean.setName(pi.getPerson().getName());
							beans.add(bean);
						} else {
							if(organId.equals(pi.getOrganization().getCode())) {
								bean.setId(pi.getPerson().getId());
								bean.setName(pi.getPerson().getName());
								beans.add(bean);
							}
						}
					}
				}
			}
		}
		return beans;
	}
	
	public List<String> getUserIds(String roleName, String organId, boolean fiter_part_time) {
		List<UserBean> beans = getUserBean(roleName, organId, fiter_part_time);
		List<String> ids = new ArrayList<String>();
		if (!StringUtil.isListEmpty(beans)) {
			for (UserBean user : beans) {
				ids.add(user.getId());
			}
		}
		return ids;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getOrganMap() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<Organization> organs = (List<Organization>) baseService.getList("Organization", "o.code asc", true);
		List<String> all_organ = new ArrayList<String>();
		for (Organization organ : organs) {
			if (organ.getSuperior() != null) {
				String key = organ.getSuperior().getCode();
				if (StringUtil.isMapContainsKey(map, key)) {
					List<String> s = map.get(key);
					s.add(organ.getCode());
					map.replace(key, s);
				} else {
					List<String> s = new ArrayList<String>();
					s.add(organ.getCode());
					map.put(key, s);
				}
			}
			all_organ.add(organ.getCode());
		}
		map.put("all_organ", all_organ);
		return map;
	}
}
