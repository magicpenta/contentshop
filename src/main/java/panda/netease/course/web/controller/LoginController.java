package panda.netease.course.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import panda.netease.course.meta.User;
import panda.netease.course.service.UserService;

/**
 * 登陆相关
 * @author panda
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	/**
	 * 显示登陆页
	 * @return
	 */
	@RequestMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	/**
	 * 处理登陆逻辑
	 * @param user, request, session
	 * @return
	 */
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(User user, HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 1.获取用户登陆信息
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// 2.从数据库获取用户信息
		user = userService.getUser(userName);
		// 3.比对用户信息，作相应处理
		if ( user == null ) {
			map.put("code", 400);
			map.put("message", "用户不存在！");
			map.put("result", false);
		} else if ( !user.getPassword().equals(password) ) {
			map.put("code", 400);
			map.put("message", "密码错误！");
			map.put("result", false);
		} else {
			map.put("code", 200);
			map.put("message", "success");
			map.put("result", true);
			session.setAttribute("user", user);
		}
		return map;
	}
	
	/**
	 * 用户登出
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
}
