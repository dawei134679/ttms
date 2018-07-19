package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeDao extends BaseDao<ProductType> {
	List<Map<String, Object>> findGridTreeNodes();

	int hasChildType(Integer id);

	int deleteObject(Integer id);

	/** 获取所有node节点 */
	List<Map<String, Object>> findZTreeNodes();

	Map<String, Object> findObjectById(Integer id);

	int updateObject(ProductType entity);
}
