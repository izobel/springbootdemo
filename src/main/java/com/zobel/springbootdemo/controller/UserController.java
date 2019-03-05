package com.zobel.springbootdemo.controller;


import com.zobel.springbootdemo.domain.User;
import com.zobel.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 功能描述: user 保存接口
	 * @return
	 */
	@GetMapping("add")
	public Object add(){
		
		User user = new User();
		user.setAge(18);
		user.setCreateTime(new Date());
		user.setName("xiaxiaxia");
		user.setPhone("10010000");
		int id = userService.add(user);
		
       return id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private UserMapper userMapper;
//	
//	
//	
//	@GetMapping("findAll")
//	public Object findAll(){
//		
//       return JsonData.buildSuccess(userMapper.getAll());
//	}
//	
//	
//	
//	@GetMapping("findById")
//	public Object findById(long id){
//       return JsonData.buildSuccess(userMapper.findById(id));
//	}
//	
//	
//	@GetMapping("del_by_id")
//	public Object delById(long id){
//	userMapper.delete(id);
//       return JsonData.buildSuccess();
//	}
//	
//	@GetMapping("update")
//	public Object update(String name,int id){
//		User user = new User();
//		user.setName(name);
//		user.setId(id);
//		userMapper.update(user);
//	    return JsonData.buildSuccess();
//	}
//	
	
	
	
	
	
//	//测试事务
//	@GetMapping("transac")
//	public Object transac(){
//		int id = userService.addAccount();
//	    return JsonData.buildSuccess(id);
//	}
//	
//	
	
	
}
