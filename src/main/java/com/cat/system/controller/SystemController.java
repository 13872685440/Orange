package com.cat.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cat.boot.catconst.RedisConst;
import com.cat.boot.config.JedisUtil;
import com.cat.boot.jsonbean.ResultBean;
import com.cat.boot.util.StringUtil;
import com.cat.system.jsonbean.PathBean;

@RestController
@RequestMapping("/system")
public class SystemController{
	
	@Autowired
	JedisUtil jedisUtil;
	
	static int db = RedisConst.reload_db;
	
	static int path_db = RedisConst.path_db;
	
	static String path = "path";
	
	@RequestMapping(value = "/config/edit", method = RequestMethod.GET)
	public String configedit() {
		JSONObject j = new JSONObject();
		initJson(j, "reload");
		initJson(j, "namequeryreload");
		j.put("whitelist", jedisUtil.get("whitelist", db));

		return ResultBean.getSucess(j);
	}
	
	@RequestMapping(value = "/config/save", method = RequestMethod.POST)
	public String configsave(@RequestParam boolean reload,@RequestParam boolean namequeryreload,@RequestParam String whitelist) {
		jedisUtil.set("reload", String.valueOf(reload), db);
		jedisUtil.set("namequeryreload", String.valueOf(namequeryreload), db);
		jedisUtil.set("whitelist", whitelist, db);
		
		return ResultBean.getSucess("");
	}
	
	@RequestMapping(value = "/path/query_path", method = RequestMethod.GET)
	public String query_path() {
		Map<String, String> map = jedisUtil.hgetall(path, path_db);
		List<PathBean> beans = new ArrayList<PathBean>();
		if(!StringUtil.isMapEmpty(map)) {
			for (String s : map.keySet()) {
				PathBean bean = new PathBean();
				bean.setKey(s);
				bean.setPath(map.get(s));
				beans.add(bean);
			}
		}
		return ResultBean.getSucess(beans);
	}
	
	@RequestMapping(value = "/path/delete_path", method = RequestMethod.POST)
	public String delete_path(@RequestParam String id) {
		jedisUtil.hdel(path, path_db, id);
		return ResultBean.getSucess("");
	}
	
	@RequestMapping(value = "/path/edit_path", method = RequestMethod.GET)
	public String edit_path(@RequestParam String id) {
		PathBean bean = new PathBean();
		bean.setKey(id);
		bean.setPath(jedisUtil.hget(path, id, path_db));
		return ResultBean.getSucess(bean);
	}
	
	@RequestMapping(value = "/path/save_path", method = RequestMethod.POST)
	public String save_path(PathBean bean) {
		jedisUtil.hset(path, bean.getKey(), bean.getPath(), path_db);
		return ResultBean.getSucess("");
	}
	
	void initJson(JSONObject j,String key) {
		if(jedisUtil.exists(key, db)) {
			j.put(key, Boolean.valueOf(jedisUtil.get(key, db)));
		} else {
			j.put(key, true);
		}
	}
}
