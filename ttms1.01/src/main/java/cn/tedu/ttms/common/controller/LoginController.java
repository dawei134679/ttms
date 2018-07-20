package cn.tedu.ttms.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.user.entity.UserInfo;
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
	public JsonResult indexUI(HttpServletRequest request){
		System.out.println("login.do");
		String account = request.getParameter("username");
		String password = request.getParameter("password");
		UserInfo userInfo = userInfoService.getUserInfoByAcount(account);
		if(userInfo == null) {
			return new JsonResult(new ServiceException("账号不存在"));
		}
		if(!userInfo.getPassword().equals(password)) {
			return new JsonResult(new ServiceException("密码错误"));
		}
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		session.setAttribute("currentUser", userInfo);
		return new JsonResult();
	}
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		//注销用户
		session.invalidate();
		return "login";
	}

}
