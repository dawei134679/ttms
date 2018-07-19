package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;

@Controller
@RequestMapping("/team/")
public class TeamController {
	@Autowired
	@Qualifier("teamServiceImpl")
	private TeamService teamService;

	@RequestMapping("listUI")
	public String listUI() {
		return "product/team_list";
	}

	@RequestMapping("editUI")
	public String editUI() {
		return "product/team_edit";
	}

	@RequestMapping("doFindIdAndNames")
	@ResponseBody
	public JsonResult doFindIdAndNames() {
		List<IdName> list = teamService.findIdAndNames();
		return new JsonResult(list);
	}

	/**
	 * 假如我们需要用指定名字接收 页面数据可以考虑使用
	 * 
	 * @RequestParam注解,尤其是页面 参数名和控制层方法参数名不一致 时,它的含义等同于 request.getParamteger("参数名")
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(@RequestParam("projectName") String pName, Integer pageCurrent) {
		System.out.println("projectName=" + pName);
		Map<String, Object> map = teamService.findPageObjects(pName, pageCurrent);
		return new JsonResult(map);
	}

	/**
	 * 当使用实体对象作为参数接收页面数据 时,页面参数名应与实体对象中的setXXX 相关方法有对应关系
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Team entity) {
		teamService.saveObject(entity);
		return new JsonResult("insert ok");
	}
}
