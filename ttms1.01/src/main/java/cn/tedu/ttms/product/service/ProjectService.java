package cn.tedu.ttms.product.service;
import java.util.Map;

import cn.tedu.ttms.product.entity.Project;
public interface ProjectService {
	 Map<String,Object> findPageObjects(
	    String name,
	    Integer valid,
	    Integer pageCurrent);
	 /**实现禁用启用功能*/
	 void validById(
			 String checkedIds,
			 Integer valid);
	 /**实现数据的保存*/
	 void saveObject(Project entity);
	 
	 /**根据id执行查询操作*/
	 Project findObjectById(Integer id);
	 
	 /**修改项目信息*/
	 void updateObject(Project entity);
	 
	 
}





