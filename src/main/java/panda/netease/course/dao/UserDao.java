package panda.netease.course.dao;

import panda.netease.course.meta.User;

/**
 * 用户数据访问对象
 * @author panda
 *
 */
public interface UserDao {
	/**
	 * 获取指定用户名的账号信息
	 * @param userName
	 * @return
	 */
	public User getUser(String userName);
	
}
