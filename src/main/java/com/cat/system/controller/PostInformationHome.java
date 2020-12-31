package com.cat.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cat.boot.catconst.RedisConst;
import com.cat.boot.config.JedisUtil;
import com.cat.boot.jsonbean.PropParamBean;
import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.jsonbean.UserBean;
import com.cat.boot.service.BaseHome;
import com.cat.boot.util.IDCardNumUtil;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.PassWordUtil;
import com.cat.boot.util.RadomUtil;
import com.cat.boot.util.StringUtil;
import com.cat.dictionary.model.Dictionary;
import com.cat.system.jsonbean.PostInformationBean;
import com.cat.system.jsonbean.PostInformationBean2;
import com.cat.system.model.Organization;
import com.cat.system.model.Person;
import com.cat.system.model.Post;
import com.cat.system.model.PostInformation;
import com.cat.system.model.User;

@RestController
@RequestMapping("/postinformation")
public class PostInformationHome extends BaseHome<PostInformation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 314586715829350604L;
	
	@Autowired
	public JedisUtil jedisUtil;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam String id) {
		if (!StringUtil.isEmpty(id)) {
			PostInformation entity = findById(id);
			return ResultBean.getSucess(PostInformationBean.setThis(entity,baseService));
		} else {
			PostInformation entity = new PostInformation();
			return ResultBean.getSucess(PostInformationBean.setThis(entity,baseService));
		}
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		String token = request.getHeader("Access-Token");
		UserBean ybean = JSON.parseObject(jedisUtil.get(token,RedisConst.token_db), UserBean.class);
		PostInformation entity = (PostInformation)baseService.getSimple("PostInformation", null, true,
				NameQueryUtil.setParams("person.id",ybean.getId(),"xh",999));
		if(entity!=null) {
			return ResultBean.getSucess(PostInformationBean.setThis(entity,baseService));
		}else {
			return ResultBean.getSucess(PostInformationBean.setThis(new PostInformation(),baseService));
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam String id) {
		PostInformation entity = new PostInformation();
		Organization organization;
		if (StringUtil.isEmpty(id)) {
			organization = (Organization) baseService.findById(Organization.class, "0001");
		} else {
			organization = (Organization) baseService.findById(Organization.class, id);
		}
		
		entity.setOrganization(organization);
		entity.setIsleaf((Dictionary)baseService.getSimple("Dictionary", "", true,NameQueryUtil.setParams("typeCode","POSTTYPE","name","全职")));
		
		PostInformationBean bean = PostInformationBean.setThis(entity,baseService);
		bean.setCode(baseService.getCode(5,"Person","code", null).trim());
		bean.setLoginName("xt" + bean.getCode());
		return ResultBean.getSucess(bean);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(PostInformationBean entity) {
		PostInformation bean = new PostInformation();
		if (!StringUtil.isEmpty(entity.getId())) {
			bean = findById(entity.getId());
		}
		bean.setXh(999);
		bean.setProperty("PRO001");
		
		PostInformationBean.clone(bean, entity, baseService);
		baseService.save(bean);
		return ResultBean.getSucess(PostInformationBean.setThis(bean, baseService));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/save2", method = RequestMethod.POST)
	public String save2(PostInformationBean entity) {
		PostInformation bean = findById(entity.getId());
		
		List<Post> posts = (List<Post>)baseService.getList("Post", null, true,NameQueryUtil.setParams("id",entity.getPosts()));
		bean.getPosts().clear();
		bean.getPosts().addAll(posts);
		baseService.save(bean,true);
		return ResultBean.getSucess("sucess");
	}
	
	@RequestMapping(value = "/edit_postinformation", method = RequestMethod.GET)
	public String edit_postinformation(@RequestParam String id) {
		if (!StringUtil.isEmpty(id)) {
			PostInformation entity = (PostInformation)baseService.findById(PostInformation.class,id);
			return ResultBean.getSucess(PostInformationBean2.setThis(entity,baseService));
		} else {
			return ResultBean.getSucess(PostInformationBean2.setThis(new PostInformation(),baseService));
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/save_postinformation", method = RequestMethod.POST)
	public String save_postinformation(PostInformationBean2 entity) {
		PostInformation bean = new PostInformation();
		if (!StringUtil.isEmpty(entity.getId())) {
			bean = (PostInformation)baseService.findById(PostInformation.class,entity.getId());
		}
		
		PostInformationBean2.clone(bean, entity, baseService);
		baseService.save(bean);
		return ResultBean.getSucess("sucess");
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String resetRadom(@RequestParam String id) {
		PostInformation entity = findById(id);
		User user = (User) entity.getPerson();
		user.setRadom(RadomUtil.radom(6));
		Map<String, String> pwds = PassWordUtil.entryptPassword(user.getRadom(), true);
		user.setPwd(pwds.get("pwd"));
		user.setSalt(pwds.get("salt"));
		baseService.noAnnotationSave(user, true);
		return ResultBean.getSucess(user.getRadom());
	}

	@RequestMapping(value = "/makepwd", method = RequestMethod.POST)
	public String makePassword(@RequestParam("userId") String userId, @RequestParam("newpwd") String newpwd) {
		User user = (User)baseService.findById(User.class, userId);
		Map<String, String> pwds = PassWordUtil.entryptPassword(newpwd, false);
		user.setPwd(pwds.get("pwd"));
		user.setSalt(pwds.get("salt"));
		baseService.noAnnotationSave(user, true);
		return ResultBean.getSucess("");
	}
	
	@RequestMapping(value = "/validationCertNum", method = RequestMethod.GET)
	public String validationCertNum(@RequestParam String id,@RequestParam String certnum) {
		// 判断身份证号是否有录入
		JSONObject o = new JSONObject();
		try {
			if(loadinformation_vue("certnum",certnum,id)!=null) {
				o.put("error", "该身份证号已录入系统");
			} else {
				o = IDCardNumUtil.validate(certnum, "new");
			}
		} catch (Exception e) {
			o.put("error", "身份证号解析错误");
		}
		return ResultBean.getSucess(o);
	}
	
	@RequestMapping(value = "/validationUnique", method = RequestMethod.GET)
	public String validationPhone(@RequestParam String id,@RequestParam String key,@RequestParam String value,@RequestParam String error) {
		// 判断身份证号是否有录入
		JSONObject o = new JSONObject();
		if(loadinformation_vue(key,value,id)!=null) {
			o.put("error", error);
		} 
		return ResultBean.getSucess(o);
	}
	
	public User loadinformation_vue(String prop,String value, String id){
		// 查询User
		Person user = (Person) baseService.getSimple("User", "", false,
				Arrays.asList(new PropParamBean("=", "and", prop, value),
						new PropParamBean("!=", "and", "id", StringUtil.isEmpty(id) ? "xxxx" : id)));
		return user;
	}

}
