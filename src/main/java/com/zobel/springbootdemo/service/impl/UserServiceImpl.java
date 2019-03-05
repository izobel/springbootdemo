package com.zobel.springbootdemo.service.impl;

import com.zobel.springbootdemo.domain.User;
import com.zobel.springbootdemo.mapper.UserMapper;
import com.zobel.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 private UserMapper userMapper;

	@Override
	public int add(User user) {
		userMapper.insert(user);
		int id = user.getId();
		return id;
	}
	
	
	

	
	
}
