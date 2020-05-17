package com.tdt.wheel.myspringmvc.controller;

import com.tdt.wheel.myspringmvc.annotation.Controller;
import com.tdt.wheel.myspringmvc.annotation.Qualifier;
import com.tdt.wheel.myspringmvc.annotation.RequestMapping;
import com.tdt.wheel.myspringmvc.service.UserService;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: UserController
 * @since 2020/05/17 14:37
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {

	@Qualifier("userServiceImpl")
	private UserService userService;

	@RequestMapping("/insert")
	public void insert() {
		userService.insert();
	}
}
