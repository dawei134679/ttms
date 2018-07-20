package cn.tedu.ttms.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.user.dao.UserInfoDao;
import cn.tedu.ttms.user.entity.UserInfo;
import cn.tedu.ttms.user.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public UserInfo getUserInfoByAcount(String account) {
		
		return userInfoDao.getUserInfoByAcount(account);
		
	}


}
