package cn.tedu.ttms.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.user.service.UserInfoService;


@Controller
public class LoginController {
	@Autowired
	private  UserInfoService userInfoService;
	
	@RequestMapping("/tologin.do")
	public String tologin(){
		return "login";
	}
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult indexUI(){
		System.out.println("login.do");
		
		return new JsonResult();
	}
}
