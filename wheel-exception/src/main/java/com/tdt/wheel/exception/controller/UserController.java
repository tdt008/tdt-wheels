package com.tdt.wheel.exception.controller;

import com.tdt.wheel.exception.domain.User;
import com.tdt.wheel.exception.response.constant.ResponseEnum;
import com.tdt.wheel.exception.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: LicenceController
 * @since 2020/05/12 22:41
 */
@RestController
@RequestMapping("/licence")
public class UserController {
	@Autowired
	private UserService userService;


	private void checkNotNull(User user) {
		ResponseEnum.USER_NOT_FOUND.assertNotNull(user);
	}
}
