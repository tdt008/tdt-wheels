package com.tdt.wheel.myspringmvc.service.impl;

import com.tdt.wheel.myspringmvc.annotation.Qualifier;
import com.tdt.wheel.myspringmvc.annotation.Service;
import com.tdt.wheel.myspringmvc.dao.UserDao;
import com.tdt.wheel.myspringmvc.service.UserService;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: UserServiceImpl
 * @since 2020/05/17 14:40
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Qualifier("userDaoImpl")
	private UserDao userDao;

	public void insert() {
		System.out.println("UserServiceImpl.insert() start");
		userDao.insert();
		System.out.println("UserServiceImpl.insert() end");
	}
}
