package panda.netease.course.service;

import panda.netease.course.meta.User;

public interface UserService {

	/**
	 * 通过用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public User getUser(String userName);
}
