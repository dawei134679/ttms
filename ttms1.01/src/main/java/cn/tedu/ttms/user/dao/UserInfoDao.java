package cn.tedu.ttms.user.dao;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.user.entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo>{
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
   
    UserInfo getUserInfoByAcount(@Param("account")String account);
}