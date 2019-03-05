package com.zobel.springbootdemo.mapper;


import com.zobel.springbootdemo.domain.DataSourceDomain;
import com.zobel.springbootdemo.domain.User;
import com.zobel.springbootdemo.domain.User2;
import org.apache.ibatis.annotations.*;

/**
 * 功能描述：访问数据库的接口
 */
public interface DataSourceMapper {
	
	

	 
	 
	
//
//    @Select("SELECT * FROM user")
//    @Results({
//        @Result(column = "create_time",property = "createTime")  //javaType = java.util.Date.class        
//    })
//    List<User2> getAll();
//  
//    
//
    @Select("SELECT * FROM datasource WHERE id = #{id}")
    @Results({
    	 @Result(column = "db_driver",property = "driver"),
    	 @Result(column = "db_url",property = "url"),
    	 @Result(column = "db_username",property = "username"),
    	 @Result(column = "db_password",property = "password")
    })
	DataSourceDomain findById(Long id);
//
//   
//
//    @Update("UPDATE user SET name=#{name} WHERE id =#{id}")
//    void update(User2 user);
//
//    @Delete("DELETE FROM user WHERE id =#{userId}")
//    void delete(Long userId);
//
}