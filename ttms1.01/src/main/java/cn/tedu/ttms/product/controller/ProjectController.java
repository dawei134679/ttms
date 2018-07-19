package cn.tedu.ttms.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

@Controller
@RequestMapping("/project/")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@RequestMapping("listUI")
	public String listUI() {
		return "product/project_list";
	}

	/** 通过此方法返回编辑页面 */
	@RequestMapping("editUI")
	public String editUI() {
		return "product/project_edit";
	}

	/** 根据id执行查找操作 */
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		Project project = projectService.findObjectById(id);
		return new JsonResult(project);
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Project entity) {
		projectService.updateObject(entity);
		return new JsonResult("update Ok");
	}

	/**
	 * 实现数据的写入操作
	 * 
	 * @param entity
	 *            用于封装页面中 表单数据(name,code,....) 客户端:name="渤海游"&code="tt-..."
	 *            SpringMVC:从参数中获取名字和值,然后 底层会通过反射将这值通过对象 的set方法注入到具体的参数对象
	 *            DispatcherServlet-->HandlerMapping--> HandleExecutionChain
	 *            --HandlerAdapter-->Handler-->HttpMessageConvert
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Project entity) {
		projectService.saveObject(entity);
		return new JsonResult("insert ok");
	}

	/**
	 * @ResponseBody注解可以将方法返回的对象( 例如实体对象,map对象,list对象)转换成json 字符串 问题:控制层的方法中接收页面数据时,
	 * 是如何实现的? 1)HttpServletRequest:request.getParameter("参数名")
	 * 2)直接通过变量(名字必须与页面参数名相同) 3)通过实体对象(参数必须与对象用对应的set方法一致) 4)...
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(String name, Integer valid, Integer pageCurrent) {
		System.out.println("pageCurrent=" + pageCurrent);
		Map<String, Object> map = projectService.findPageObjects(name, valid, pageCurrent);
		return new JsonResult(map);
	}

	/** 通过此方法执行启用禁用操作 */
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds, Integer valid) {
		projectService.validById(checkedIds, valid);
		return new JsonResult("valid ok");
	}
}
