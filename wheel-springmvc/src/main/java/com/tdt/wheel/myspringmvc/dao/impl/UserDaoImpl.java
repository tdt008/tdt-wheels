package com.tdt.wheel.myspringmvc.dao.impl;

import com.tdt.wheel.myspringmvc.annotation.Repository;
import com.tdt.wheel.myspringmvc.dao.UserDao;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: UserDaoImpl
 * @since 2020/05/17 14:42
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

	public void insert() {
		System.out.println("execute UserDaoImpl.insert....");
	}
}
