package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
	private TeamDao teamDao;
    @Autowired
    private ProjectDao projectDao;
    /**获取有效项目的id和name*/
    @Override
    public List<IdName> findIdAndNames() {
    	return projectDao.findIdAndNames();
    }
    
    @Override
    public void saveObject(Team entity) {
    	if(entity==null)
        throw new ServiceException("保存对象不能为空");
        int rows=
        teamDao.insertObject(entity);
        if(rows!=1)
        throw new ServiceException("insert error");
    } 
	@Override
	public Map<String, Object> findPageObjects(
		String projectName,
		Integer pageCurrent) {
		//1.验证参数的合法性?
		if(pageCurrent==null||pageCurrent<1)
		throw new ServiceException(
		"pageCurrent的值不合法:pageCurrent"+pageCurrent);
		//2.计算startIndex的值
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		//3.获得总记录数,然后计算总页数
		int rowCount=
		teamDao.getRowCount(projectName);
		int pageCount=rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}
		//4.通过PageObject对象封装分页信息
		PageObject pageObject=new PageObject();
		pageObject.setPageCount(pageCount);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		pageObject.setPageSize(pageSize);
		//5.查询当前页数据
	    List<Map<String,Object>> list=
	    		teamDao.findPageObjects(projectName,
	    		startIndex, pageSize);
	    //6.封装分页数据和当前数据
		Map<String,Object> map=
		new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject",pageObject);
		return map;
	}

}
