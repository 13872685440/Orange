package com.cat.boot.service;

import java.io.File;

import com.cat.boot.catconst.RedisConst;
import com.cat.boot.jsonbean.BaseQueryHelp;
import com.cat.boot.jsonbean.ResultBean;
import com.cat.file.model.FileInfo;
import com.cat.file.model.YeWuBLZLWD;

public abstract class BaseNqtQuery<T> extends BaseQuery<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 736515704643423088L;

	public abstract String query(BaseQueryHelp parms) throws Exception;

	public void excuteQuery(BaseQueryHelp parms) throws Exception {
		iniQhb(parms);
		executeQuery(2);
		executeCountQuery(2);
		parms.setTotalRecordCount(getQhb().getTotalRecordCount());
	};

	private void iniQhb(BaseQueryHelp parms) {
		getQhb().setParams(parms.getParams());
		getQhb().setPageSize(parms.getPageSize() == 0 ? 20 : parms.getPageSize());
		getQhb().setPageNo(parms.getPageNo()== 0 ? 1 : parms.getPageNo());
		getQhb().setSortOrder(parms.getSortOrder());
		getQhb().setSortField(parms.getSortField());
		getQhb().setUserId(parms.getUserId());
	}

	@SuppressWarnings("unchecked")
	protected String delete(String id) {
		T entity = (T) baseService.findById(getEntityClass(), id);
		boolean flag = baseService.noAnnotationDelete(entity);
		if (flag) {
			return ResultBean.getSucess("删除成功");
		} else {
			return ResultBean.getResultBean("400", "", "删除失败");
		}
	}
	
	@SuppressWarnings("unchecked")
	protected String deleteTr(String id) {
		T entity = (T) baseService.findById(getEntityClass(), id);
		boolean flag = baseService.delete(entity);
		if (flag) {
			return ResultBean.getSucess("删除成功");
		} else {
			return ResultBean.getResultBean("400", "", "删除失败");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected String delete(Class clazz,String id) {
		T entity = (T) baseService.findById(clazz, id);
		boolean flag = baseService.noAnnotationDelete(entity);
		if (flag) {
			return ResultBean.getSucess("删除成功");
		} else {
			return ResultBean.getResultBean("400", "", "删除失败");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected String deleteTr(Class clazz,String id) {
		T entity = (T) baseService.findById(clazz, id);
		boolean flag = baseService.delete(entity);
		if (flag) {
			return ResultBean.getSucess("删除成功");
		} else {
			return ResultBean.getResultBean("400", "", "删除失败");
		}
	}
	
	protected void deleteFile(String id) {
		YeWuBLZLWD data = (YeWuBLZLWD) baseService.findById(YeWuBLZLWD.class, id);
		FileInfo info = (FileInfo)data.getFileinfo();
		File file = new File(jedisUtil.getPath("uploadpath")
				+ File.separator + info.getUrlName() + File.separator + info.getOriginalName());
		if(file.exists()) {
			file.delete();
		}
		baseService.delete(info);
		baseService.delete(data);
	}
}
