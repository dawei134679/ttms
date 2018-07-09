package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.product.entity.Project;

/**项目模块的数据访问对象
 * 1)负责数据的持久化
 * 2)负责数据的查询然后执行映射操作*/
public interface ProjectDao extends BaseDao<Project>{
     /**定义此方法用于实现分页查询操作
      * 1)第一步先查询所有数据
      * 2)第二步重构方法实现分页查询
      * 3)第三步重构方法实现条件查询
      * @param startIndex 从startIndex+1的位置开始取记录
      * @Param pageSize 每页显示多少条记录
      * 当一个方法中有多个参数时,请使用
      * @Param 注解进行声明,在mapper文件中
      * 使用参数时需要与@Param注解定义的名字
      * 保持一致.
      * */
	 List<Project> findPageObjects(
	    @Param("name") String name,
	    @Param("valid") Integer valid,
		@Param("startIndex")Integer startIndex,
		@Param("pageSize")Integer pageSize);
	 //.....
	 /**通过此方法获得记录总数,根据条件进行统计*/
	 int getRowCount(
		@Param("name") String name,
	    @Param("valid") Integer valid);
	 /**
	  * 通过此方法实现禁用启用操作
	  * @param ids 表示要禁用的对象的id
	  * @param valid 表示状态(1,0)
	  * */
	 int validById(
			 @Param("ids")String[] ids,
             @Param("valid")Integer valid);
	 
	 /**
	  * 通过此方法实现数据的插入操作
	  * @param entity 通过此对象封装了要
	  * 写入的数据
	  * @return 表示写入数据的行数
	  */
	  //int insertObject(Project entity);
	  /**根据ID获得project对象
	   * @param id 对象的唯一标识
	   */
	  Project findObjectById(Integer id);
	  
	  /**用于更新项目信息*/
	  int updateObject(Project entity);
	  
	  /**获得项目的id和name*/
	  List<IdName> findIdAndNames();
	  
	 
}
/**
 * 知识点回顾?
 * 1.SSM架构中这个DAO对象的实现方式有哪些形式?
 * 1)自己实现此DAO实现类
 * 2)在配置文件中通过配置由底层自动创建dao的
 * 实现类。
 * 例如借助MapperScannerConfigurer对象扫描对应包
 * 中的实现类?
 * 2.SSM架构中这个DAO对象与具体mapper是如何
 * 绑定的?
 * 1)命名空间的绑定
 * 2)方法与元素id的绑定
 * 3)方法参数与具体SQL的绑定
 * 4)方法返回值的匹配
 */





