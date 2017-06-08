package panda.netease.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panda.netease.course.dao.UserDao;
import panda.netease.course.meta.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public User getUser(String userName) {
		return userDao.getUser(userName);
	}

}
