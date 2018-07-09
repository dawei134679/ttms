package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;
@Service
public class ProductTypeServiceImpl 
          implements ProductTypeService{
    @Autowired
	private ProductTypeDao productTypeDao;
    
    @Override
    public Map<String, Object> findObjectById(Integer id) {
    	if(id==null||id<1)
    	throw new ServiceException("id的值无效,id="+id);
        Map<String,Object> map=productTypeDao.findObjectById(id);
        if(map==null)
        throw new ServiceException("对象已经不存在");
        return map;
    }
    @Override
    public void updateObject(ProductType entity) {
    	if(entity==null)
    	throw new ServiceException("保存对象不能为空");
    	int rows=productTypeDao.updateObject(entity);
    	if(rows!=1)
    	throw new ServiceException("update error");
    }
    @Override
    public List<Map<String, Object>> findZTreeNodes() {
    	return productTypeDao.findZTreeNodes();
    }
    @Override
    public void saveObject(ProductType entity) {
    	if(entity==null)
    	throw new ServiceException("保存对象不能为空");
    	int rows=
    	productTypeDao.insertObject(entity);
    	if(rows!=1)
    	throw new ServiceException("insert error");
    }
	@Override
	public List<Map<String, Object>> 
	findGridTreeNodes() {
	return productTypeDao.findGridTreeNodes();
	}
	@Override
	public void deleteObject(Integer id) {
		//1.判定id有效性
		if(id==null||id<=0)
		throw new ServiceException("id的值无效,id="+id);
		//2.判定是否存在子分类
		int count=
		productTypeDao.hasChildType(id);
		if(count>0)
		throw new ServiceException("有子分类不能删除");
		//3.执行删除动作
		int rows=productTypeDao.deleteObject(id);
		//4.判定结果
		if(rows!=1)
		throw new ServiceException("删除失败");
	}
}




