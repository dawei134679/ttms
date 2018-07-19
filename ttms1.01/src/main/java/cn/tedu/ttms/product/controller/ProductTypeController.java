package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@RequestMapping("/type/")
@Controller
public class ProductTypeController {
	@Autowired
	private ProductTypeService productTypeService;

	@RequestMapping("listUI")
	public String listUI() {
		return "product/type_list";
	}

	@RequestMapping("editUI")
	public String editUI() {
		return "product/type_edit";
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(ProductType entity) {
		productTypeService.updateObject(entity);
		return new JsonResult("update ok");
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		Map<String, Object> map = productTypeService.findObjectById(id);
		return new JsonResult(map);
	}

	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		List<Map<String, Object>> list = productTypeService.findZTreeNodes();
		return new JsonResult(list);
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType entity) {
		productTypeService.saveObject(entity);
		return new JsonResult("insert ok");
	}

	@RequestMapping("doFindGridTreeNodes")
	@ResponseBody
	public JsonResult doFindGridTreeNodes() {
		List<Map<String, Object>> list = productTypeService.findGridTreeNodes();
		return new JsonResult(list);
	}

	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		productTypeService.deleteObject(id);
		return new JsonResult("delete ok");
	}
}
