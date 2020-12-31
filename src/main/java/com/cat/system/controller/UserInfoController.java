package com.cat.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cat.boot.catconst.RedisConst;
import com.cat.boot.config.JedisUtil;
import com.cat.boot.exception.CatException;
import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.jsonbean.UserBean;
import com.cat.boot.service.BaseService;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.StringUtil;
import com.cat.file.model.YeWuBLZLWD;
import com.cat.system.jsonbean.AppTreeInfoBean;
import com.cat.system.jsonbean.PermissionInfoBean;
import com.cat.system.jsonbean.PostInformationBean;
import com.cat.system.jsonbean.RoleInfoBean;
import com.cat.system.jsonbean.UserInfoBean;
import com.cat.system.model.AppTree;
import com.cat.system.model.Role;
import com.cat.system.model.User;

@RestController
@RequestMapping("/info")
public class UserInfoController {

	@Autowired
	public BaseService baseService;

	@Autowired
	public JedisUtil jedisUtil;

	@Autowired
	public UserInfoService userInfoService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(HttpServletRequest request) throws Exception {
		String token = request.getHeader("Access-Token");
		UserBean ybean = JSON.parseObject(jedisUtil.get(token,RedisConst.token_db), UserBean.class);
		UserInfoBean bean = new UserInfoBean(ybean);
		
		List<String> userRoles = new ArrayList<String>();
		if ("admin".equals(bean.getUsername())) {
			RoleInfoBean roleInfo = new RoleInfoBean("SYS_ADMINISTRATOR", "超级管理员", new ArrayList<PermissionInfoBean>());

			bean.getRoles().add(roleInfo);

			userRoles.add("SYS_ADMINISTRATOR");
		} else {
			List<PostInformationBean> posts = userInfoService.getPostInformation(bean.getId());
			bean.setPosts(posts);
			List<String> roles = new ArrayList<String>();
			if (!StringUtil.isListEmpty(posts)) {
				for (PostInformationBean p : posts) {
					if (!StringUtil.isListEmpty(p.getRoles())) {
						roles.addAll(p.getRoles());
					}
				}
			}
			// 查询通用角色
			List<String> tys = (List<String>) baseService.getList("SYS_Role", null, true, "name",NameQueryUtil.setParams("r_type", "RT001"));
			if(!StringUtil.isListEmpty(tys)) {
				roles.addAll(tys);
			}
			// 查询role
			if (!StringUtil.isListEmpty(roles)) {
				Map<String, RoleInfoBean> infos = new HashMap<String, RoleInfoBean>();
				List<Role> rs = (List<Role>) baseService.getList("Role", null, true,
						NameQueryUtil.setParams("name", roles));
				for (Role role : rs) {
					RoleInfoBean roleInfo = new RoleInfoBean(role.getName(), role.getDes(),
							new ArrayList<PermissionInfoBean>());
					infos.put(role.getName(), roleInfo);
					userRoles.add(role.getName());
				}
				
				if (!StringUtil.isMapEmpty(infos)) {
					bean.getRoles().addAll(infos.values());
				}
			}
		}
		
		ybean.setUserRoles(userRoles);
		String avatar = getAvatar(bean.getId());
		if(StringUtil.isEmpty(avatar)) {
			bean.setAvatar("/avatar2.jpg");
		} else {
			bean.setAvatar("/api/system/file/viewimage?id="+avatar+"&token="+token);
		}
		jedisUtil.set(token, JSONObject.toJSONString(ybean),RedisConst.token_db);
		bean.setUserRoles(userRoles);
		return ResultBean.getSucess(bean);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@RequestBody UserInfoBean bean, HttpServletRequest request) throws Exception {
		String token = request.getHeader("Access-Token");
		UserBean ybean = JSON.parseObject(jedisUtil.get(token,RedisConst.token_db), UserBean.class);
		
		User user = (User)baseService.findById(User.class, ybean.getId());
		user.setRegistration_id(bean.getRegistration_id());
		
		baseService.save(user, true);
		
		return ResultBean.getSucess("");
	}
	
	@SuppressWarnings("unchecked")
	public String getAvatar(String userId) throws CatException {
		List<YeWuBLZLWD> wds = (List<YeWuBLZLWD>)baseService.getList("YeWuBLZLWD", null, true,
				NameQueryUtil.setParams("keyValue",userId,"ebcn","com.cat.system.model.Person","sign","A1"));
		if(StringUtil.isListEmpty(wds)) {
			return null;
		}else {
			return wds.get(0).getId();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/router", method = RequestMethod.POST)
	public String router(@RequestBody UserBean bean) throws Exception {
		List<String> roles = bean.getUserRoles();
		if (!StringUtil.isListEmpty(roles)) {
			List<AppTree> apps = (List<AppTree>) baseService.getList("AppTree", "system", "AppTree_by_Role",
					NameQueryUtil.setParams("roles", roles));
			if (!StringUtil.isListEmpty(apps)) {
				return ResultBean.getSucess(AppTreeInfoBean.iniAppTree(apps, baseService));
			}
		}
		return ResultBean.getSucess("");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/app_router", method = RequestMethod.POST)
	public String app_router(@RequestBody UserBean bean) throws Exception {
		List<String> roles = bean.getUserRoles();
		if (!StringUtil.isListEmpty(roles)) {
			List<AppTree> apps = (List<AppTree>) baseService.getList("AppTree", "system", "AppTree_by_Role",
					NameQueryUtil.setParams("roles", roles));
			if (!StringUtil.isListEmpty(apps)) {
				return ResultBean.getSucess(AppTreeInfoBean.iniAppTree_App(apps));
			}
		}
		return ResultBean.getSucess("");
	}
}
