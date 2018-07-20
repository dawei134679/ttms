package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

/**
 * 在实际项目中要通过业务层对象处理具体业务, 例如: 1)业务数据验证 2)事务的处理 3)缓存的操作 4)日志操作 5)..........
 * 
 * @author adminitartor
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	/**
	 * @Resource (默认先按名字匹配, 名字没找到按类型) @Autowired(默认按类型匹配, 假如需要按名字需要借助@Qualifier注解)
	 */
	@Autowired
	private ProjectDao projectDao;

	/** 更新项目数据 */
	@Override
	public void updateObject(Project entity) {
		if (entity == null)
			throw new ServiceException("保存对象不能为空");
		int rows = projectDao.updateObject(entity);
		if (rows != 1)
			throw new ServiceException("update error");
	}

	@Override
	public Project findObjectById(Integer id) {
		if (id == null || id < 1)
			throw new ServiceException("id的值不合法,id=" + id);
		Project project = projectDao.findObjectById(id);
		if (project == null)
			throw new ServiceException("记录已经不存在");
		return project;
	}

	/** 保存项目信息 */
	@Override
	public void saveObject(Project entity) {
		if (entity == null)
			throw new ServiceException("保存对象不能为空");
		int rows = projectDao.insertObject(entity);
		if (rows != 1)
			throw new ServiceException("insert error");
	}

	@Override
	public void validById(String checkedIds, // 1,2,3,4
			Integer valid) {
		// 1.对数据进行业务验证
		if (checkedIds == null || checkedIds.trim().length() == 0)
			throw new ServiceException("请选择修改对象");
		if (valid != 0 && valid != 1)
			throw new ServiceException("valid值无效");
		// 2.转换业务数据
		String[] ids = checkedIds.split(",");
		// 3.执行修改动作(返回值表示修改的行数)
		int rows = projectDao.validById(ids, valid);
		System.out.println("rows=" + rows);
		if (rows == -1)
			throw new ServiceException("update error");

	}

	/**
	 * 通过此方法进行分页查询
	 * 
	 * @param name
	 *            页面输入的项目名
	 * @param valid
	 *            页面输入的启用禁用状态
	 * @pageCurrent 表示页面传入的当前页面
	 */
	@Override
	public Map<String, Object> findPageObjects(String name, Integer valid, Integer pageCurrent) {
		// 对数据进行业务验证
		if (valid != null && valid != 1 && valid != 0)
			throw new ServiceException("valid 的值不合法");
		// 定义pageSize(每页显示多少条)
		int pageSize = 2;
		/*
		 * 定义pageSize,此值会通过计算获得,计算事需要: 1)pageCurrent (当前页,此值从页面传递) 2)pageSize
		 */
		int startIndex = (pageCurrent - 1) * pageSize;
		// 获得总记录数?
		int rowCount = projectDao.getRowCount(name, valid);
		// 根据总记录数以及pageSize计算总页数?
		int pageCount = rowCount / pageSize;
		if (rowCount % pageSize != 0) {
			pageCount++;
		}
		// 将分页信息封装到PageObject对象(参考ttms1.0)
		PageObject pageObject = new PageObject();
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount(pageCount);

		// 获得当前页数据
		List<Project> list = projectDao.findPageObjects(name, valid, startIndex, pageSize);
		// return list;
		// 将分页信息和每页要显示的数据封装到map一起返回
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

}
