package cn.tedu.ttms.product.service;
import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.product.entity.Team;
public interface TeamService {
    /**
     * 返回值map要封装两个对象
     * 1)List<Map> :当前页数据
     * 2)PageObject:分页数据
     * */
	Map<String,Object> findPageObjects(
			String projectName,
			Integer pageCurrent);
	
	void saveObject(Team entity); 
	
	/**获得有效项目的id和name*/
	List<IdName> findIdAndNames();
	
}








