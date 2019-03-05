package com.zobel.springbootdemo.service.impl;

import com.zobel.springbootdemo.common.db.annotation.SwitchDataSource;
import com.zobel.springbootdemo.domain.DataSourceDomain;
import com.zobel.springbootdemo.mapper.DataSourceMapper;
import com.zobel.springbootdemo.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSourceServiceImpl implements DataSourceService {

	@Autowired
	private DataSourceMapper mapper;
	 


//	@SwitchDataSource(MybatisConfig.MASTERDATASOURCE)
	@SwitchDataSource()
	@Override
	public DataSourceDomain query(long l) {
		return mapper.findById(l);
	}
}
