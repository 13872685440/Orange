package com.cat.activiti.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.boot.jsonbean.TaskBean;
import com.cat.boot.service.BaseGetUserService;
import com.cat.system.controller.UserInfoService;

// 可以每个节点定义一个service 也可以整个流程定义一个service并通过shenhebean传入的node来进行判断 也可以两者结合使用
@Service("PpeOrder_Sq_Service")
public class PpeOrderSqService extends BaseGetUserService {

	@Autowired
	public UserInfoService userInfoService;

	@Override
	public Object setVariables(Map<String, Object> variables, TaskBean bean) {
		List<String> userIds = new ArrayList<>();
		userIds.add("4028fc8775978ba401759797a80d0008");
		return userIds;
	}

}
