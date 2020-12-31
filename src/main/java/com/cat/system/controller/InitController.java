package com.cat.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.boot.service.BaseService;
import com.cat.boot.util.NameQueryUtil;
import com.cat.boot.util.PassWordUtil;
import com.cat.boot.util.StringUtil;
import com.cat.dictionary.model.Dictionary;
import com.cat.system.model.AppTree;
import com.cat.system.model.Role;
import com.cat.system.model.RoleApp;
import com.cat.system.model.User;

@RestController
@RequestMapping("/init")
public class InitController {

	@Autowired(required = true)
	public BaseService baseService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init() {
		List obj = (List) baseService.getList("User", null, false);
		if (StringUtil.isListEmpty(obj)) {
			return "-1";
		} else {
			return "0";
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/initdata", method = RequestMethod.POST)
	public String initdata() {
		Map<Object, Object> map = NameQueryUtil.setParams("loginName", "admin");
		boolean flag = baseService.isUnion("User", null, false, map);
		if (flag) {
			initmethod();
		}
		return "ok";
	}

	// 根据需要复写初始化方法

	private void initmethod() {
		List<AppTree> list = new ArrayList<AppTree>();
		
		AppTree apptree = new AppTree();
		apptree.setCode("0001");
		apptree.setClc("0001");
		apptree.setWn("仪表盘");
		apptree.setName("仪表盘");
		apptree.setLeaf(false);
		apptree.setDes("仪表盘");
		apptree.setXh(1);
		apptree.setLxh(1L);
		apptree.setComponent("RouteView");
		baseService.save(apptree, false);
		
		AppTree apptree2 = new AppTree();
		apptree2.setCode("00010001");
		apptree2.setClc("0001");
		apptree2.setWn("仪表盘_工作台");
		apptree2.setName("工作台");
		apptree2.setLeaf(true);
		apptree2.setDes("工作台");
		apptree2.setXh(1);
		apptree2.setPath("/index");
		apptree2.setComponent("/dashboard/Workplace");
		apptree2.setSuperior(apptree);
		apptree2.setLxh(11L);
		baseService.save(apptree2, false);
		list.add(apptree2);
		
		AppTree apptree11 = new AppTree();
		apptree11.setCode("0002");
		apptree11.setClc("0002");
		apptree11.setWn("系统管理");
		apptree11.setName("系统管理");
		apptree11.setLeaf(false);
		apptree11.setDes("系统管理");
		apptree11.setXh(2);
		apptree11.setLxh(2L);
		apptree11.setComponent("PageView");
		baseService.save(apptree11, false);
		
		AppTree apptree12 = new AppTree();
		apptree12.setCode("00020001");
		apptree12.setClc("0001");
		apptree12.setWn("系统管理_菜单管理");
		apptree12.setName("菜单管理");
		apptree12.setLeaf(true);
		apptree12.setDes("菜单管理");
		apptree12.setXh(1);
		apptree12.setPath("/apptree/main");
		apptree12.setComponent("/system/apptree/Main");
		apptree12.setSuperior(apptree11);
		apptree12.setLxh(apptree11.getLxh()*10+apptree12.getXh());
		baseService.save(apptree12, false);
		list.add(apptree12);

		AppTree apptree13 = new AppTree();
		apptree13.setCode("00020002");
		apptree13.setClc("0002");
		apptree13.setWn("系统管理_角色管理");
		apptree13.setName("角色管理");
		apptree13.setLeaf(true);
		apptree13.setDes("角色管理");
		apptree13.setXh(2);
		apptree13.setPath("/role/main");
		apptree13.setComponent("/system/role/Main");
		apptree13.setSuperior(apptree11);
		apptree13.setLxh(apptree11.getLxh()*10+apptree13.getXh());
		baseService.save(apptree13, false);
		list.add(apptree13);
		
		AppTree apptree14 = new AppTree();
		apptree14.setCode("00020003");
		apptree14.setClc("0003");
		apptree14.setWn("系统管理_组织机构");
		apptree14.setName("组织机构");
		apptree14.setLeaf(true);
		apptree14.setDes("组织机构");
		apptree14.setXh(3);
		apptree14.setPath("/organization/main");
		apptree14.setComponent("/system/organization/Main");
		apptree14.setSuperior(apptree11);
		apptree14.setLxh(apptree11.getLxh()*10+apptree14.getXh());
		baseService.save(apptree14, false);
		list.add(apptree14);
		
		AppTree apptree15 = new AppTree();
		apptree15.setCode("00020004");
		apptree15.setClc("0004");
		apptree15.setWn("系统管理_人员管理");
		apptree15.setName("人员管理");
		apptree15.setLeaf(true);
		apptree15.setDes("人员管理");
		apptree15.setXh(4);
		apptree15.setPath("/postinformation/main");
		apptree15.setComponent("/system/postinformation/Main");
		apptree15.setSuperior(apptree11);
		apptree15.setLxh(apptree11.getLxh()*10+apptree15.getXh());
		baseService.save(apptree15, false);
		list.add(apptree15);
		
		AppTree apptree16 = new AppTree();
		apptree16.setCode("00020005");
		apptree16.setClc("0005");
		apptree16.setWn("系统管理_岗位管理");
		apptree16.setName("岗位管理");
		apptree16.setLeaf(true);
		apptree16.setDes("岗位管理");
		apptree16.setXh(5);
		apptree16.setPath("/post/main");
		apptree16.setComponent("/system/post/Main");
		apptree16.setSuperior(apptree11);
		apptree16.setLxh(apptree11.getLxh()*10+apptree16.getXh());
		baseService.save(apptree16, false);
		list.add(apptree16);
		
		AppTree apptree17 = new AppTree();
		apptree17.setCode("00020006");
		apptree17.setClc("0006");
		apptree17.setWn("系统管理_任务路由");
		apptree17.setName("任务路由");
		apptree17.setLeaf(true);
		apptree17.setDes("任务路由");
		apptree17.setXh(6);
		apptree17.setPath("/taskrouter/main");
		apptree17.setComponent("/activiti/taskrouter/Main");
		apptree17.setSuperior(apptree11);
		apptree17.setLxh(apptree11.getLxh()*10+apptree17.getXh());
		baseService.save(apptree17, false);
		list.add(apptree17);
		
		
		AppTree apptree18 = new AppTree();
		apptree18.setCode("00020007");
		apptree18.setClc("0007");
		apptree18.setWn("系统管理_流程部署");
		apptree18.setName("流程部署");
		apptree18.setLeaf(true);
		apptree18.setDes("流程部署");
		apptree18.setXh(7);
		apptree18.setPath("/processdefinitionbean/main");
		apptree18.setComponent("/activiti/processdefinitionbean/Main");
		apptree18.setSuperior(apptree11);
		apptree18.setLxh(apptree11.getLxh()*10+apptree18.getXh());
		baseService.save(apptree18, false);
		list.add(apptree18);
		
		AppTree apptree21 = new AppTree();
		apptree21.setCode("0003");
		apptree21.setClc("0003");
		apptree21.setWn("数据字典");
		apptree21.setName("数据字典");
		apptree21.setLeaf(false);
		apptree21.setDes("数据字典");
		apptree21.setXh(3);
		apptree21.setLxh(3L);
		apptree21.setComponent("PageView");
		baseService.save(apptree21, false);
		
		AppTree apptree22 = new AppTree();
		apptree22.setCode("00030001");
		apptree22.setClc("0001");
		apptree22.setWn("数据字典_数据字典");
		apptree22.setName("数据字典");
		apptree22.setLeaf(true);
		apptree22.setDes("数据字典");
		apptree22.setXh(1);
		apptree22.setPath("/dictionary/main");
		apptree22.setComponent("/dictionary/dictionary/Main");
		apptree22.setSuperior(apptree21);
		apptree22.setLxh(apptree21.getLxh()*10+apptree22.getXh());
		baseService.save(apptree22, false);
		list.add(apptree22);
		
		AppTree apptree23 = new AppTree();
		apptree23.setCode("00030002");
		apptree23.setClc("0001");
		apptree23.setWn("数据字典_行政区划");
		apptree23.setName("行政区划");
		apptree23.setLeaf(true);
		apptree23.setDes("行政区划");
		apptree23.setXh(1);
		apptree23.setPath("/canton/main");
		apptree23.setComponent("/dictionary/canton/Main");
		apptree23.setSuperior(apptree21);
		apptree23.setLxh(apptree21.getLxh()*10+apptree23.getXh());
		baseService.save(apptree23, false);
		list.add(apptree23);

		// 创建一个SYS_ADMINISTRATOR的角色
		Role role = new Role();
		role.setName("SYS_ADMINISTRATOR");
		role.setDes("超级管理员");
		baseService.save(role, false);

		// 将角色与菜单关联
		for (AppTree app : list) {
			RoleApp roleapp = new RoleApp();
			roleapp.setApp(app);
			roleapp.setRole(role);
			baseService.save(roleapp, false);
		}

		// 创建一个admin账号
		User user = new User();
		user.setLoginName("admin");
		user.setName("系统管理员");
		Map<String, String> pwds = PassWordUtil.entryptPassword("1", true);
		user.setPwd(pwds.get("pwd"));
		user.setSalt(pwds.get("salt"));
		baseService.save(user, false);
		
		Dictionary ZZJGLX = new Dictionary();
		ZZJGLX.setTypeCode("ZZJGLX");
		ZZJGLX.setCode("0001");
		ZZJGLX.setClc("0001");
		ZZJGLX.setWn("组织机构类型");
		ZZJGLX.setName("组织机构类型");
		ZZJGLX.setLeaf(false);
		baseService.save(ZZJGLX,false);
		
		Dictionary ZZJGLX_1 = new Dictionary();
		ZZJGLX_1.setTypeCode("ZZJGLX");
		ZZJGLX_1.setCode("00010001");
		ZZJGLX_1.setClc("0001");
		ZZJGLX_1.setWn("组织机构类型_科技信息部");
		ZZJGLX_1.setName("科技信息部");
		ZZJGLX_1.setLeaf(true);
		ZZJGLX_1.setSuperior(ZZJGLX);
		baseService.save(ZZJGLX_1,false);
		
		Dictionary POSTTYPE = new Dictionary();
		POSTTYPE.setTypeCode("POSTTYPE");
		POSTTYPE.setCode("0002");
		POSTTYPE.setClc("0002");
		POSTTYPE.setWn("在职情况");
		POSTTYPE.setName("在职情况");
		POSTTYPE.setLeaf(false);
		baseService.save(POSTTYPE,false);
		
		Dictionary POSTTYPE_1 = new Dictionary();
		POSTTYPE_1.setTypeCode("POSTTYPE");
		POSTTYPE_1.setCode("00020001");
		POSTTYPE_1.setClc("0001");
		POSTTYPE_1.setWn("在职情况_在职");
		POSTTYPE_1.setName("在职");
		POSTTYPE_1.setLeaf(true);
		POSTTYPE_1.setSuperior(POSTTYPE);
		baseService.save(POSTTYPE_1,false);
		
		Dictionary POSTSTATE = new Dictionary();
		POSTSTATE.setTypeCode("POSTSTATE");
		POSTSTATE.setCode("0003");
		POSTSTATE.setClc("0003");
		POSTSTATE.setWn("员工状态");
		POSTSTATE.setName("员工状态");
		POSTSTATE.setLeaf(false);
		baseService.save(POSTSTATE,false);
		
		Dictionary POSTSTATE_1 = new Dictionary();
		POSTSTATE_1.setTypeCode("POSTSTATE");
		POSTSTATE_1.setCode("00030001");
		POSTSTATE_1.setClc("0001");
		POSTSTATE_1.setWn("员工状态_正式");
		POSTSTATE_1.setName("正式");
		POSTSTATE_1.setLeaf(true);
		POSTSTATE_1.setSuperior(POSTSTATE);
		baseService.save(POSTSTATE_1,false);
		
		Dictionary RACE = new Dictionary();
		RACE.setTypeCode("RACE");
		RACE.setCode("0004");
		RACE.setClc("0004");
		RACE.setWn("民族");
		RACE.setName("民族");
		RACE.setLeaf(false);
		baseService.save(RACE,false);
		
		Dictionary RACE_1 = new Dictionary();
		RACE_1.setTypeCode("RACE");
		RACE_1.setCode("00040001");
		RACE_1.setClc("0001");
		RACE_1.setWn("民族_汉");
		RACE_1.setName("汉");
		RACE_1.setLeaf(true);
		RACE_1.setSuperior(RACE);
		baseService.save(RACE_1,false);
		
		Dictionary MARITALSTATUS = new Dictionary();
		MARITALSTATUS.setTypeCode("MARITALSTATUS");
		MARITALSTATUS.setCode("0005");
		MARITALSTATUS.setClc("0005");
		MARITALSTATUS.setWn("婚姻状况");
		MARITALSTATUS.setName("婚姻状况");
		MARITALSTATUS.setLeaf(false);
		baseService.save(MARITALSTATUS,false);
		
		Dictionary MARITALSTATUS_1 = new Dictionary();
		MARITALSTATUS_1.setTypeCode("MARITALSTATUS");
		MARITALSTATUS_1.setCode("00050001");
		MARITALSTATUS_1.setClc("0001");
		MARITALSTATUS_1.setWn("婚姻状况_未婚");
		MARITALSTATUS_1.setName("未婚");
		MARITALSTATUS_1.setLeaf(true);
		MARITALSTATUS_1.setSuperior(MARITALSTATUS);
		baseService.save(MARITALSTATUS_1,false);
		
		Dictionary EDUCATION = new Dictionary();
		EDUCATION.setTypeCode("EDUCATION");
		EDUCATION.setCode("0006");
		EDUCATION.setClc("0006");
		EDUCATION.setWn("学历");
		EDUCATION.setName("学历");
		EDUCATION.setLeaf(false);
		baseService.save(EDUCATION,false);
		
		Dictionary EDUCATION_1 = new Dictionary();
		EDUCATION_1.setTypeCode("EDUCATION");
		EDUCATION_1.setCode("00060001");
		EDUCATION_1.setClc("0001");
		EDUCATION_1.setWn("学历_博士");
		EDUCATION_1.setName("博士");
		EDUCATION_1.setLeaf(true);
		EDUCATION_1.setSuperior(EDUCATION);
		baseService.save(EDUCATION_1,false);
	}
}
