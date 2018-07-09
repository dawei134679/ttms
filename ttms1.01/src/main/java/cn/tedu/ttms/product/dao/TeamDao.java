package cn.tedu.ttms.product.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.product.entity.Team;

public interface TeamDao extends BaseDao<Team> {
	  /**
	   * @param projectName (模糊查询时传递的参数名)
	   * @param startIndex
	   * @param pageSize
	   * @return
	   * 1)map对应表中查询的一行记录
	   * 当从多张表取数据进行封装时候,数据
	   * 通常会封装到:
	   * a)VO(值对象):具体有哪些字段由select语句
	   * b)Map:key为字段名,值为字段对应的值
	   * 2)多个map需要存储到list集合.
	   */
	  List<Map<String,Object>>
	  findPageObjects(
	   @Param("projectName")String projectName,
	   @Param("startIndex")Integer startIndex,
	   @Param("pageSize")Integer pageSize);
	  /**根据条件统计结果*/
	  int getRowCount(
	  @Param("projectName")String projectName);
	     
	  
	  
	  
	  
	  
	  
}
