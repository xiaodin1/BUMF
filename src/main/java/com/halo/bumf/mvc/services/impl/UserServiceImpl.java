/**
 * 
 */
package com.halo.bumf.mvc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halo.bumf.mvc.beans.User;
import com.halo.bumf.mvc.daos.UserDao;
import com.halo.bumf.mvc.services.ServiceConstants;
import com.halo.bumf.mvc.services.UserService;
import com.halo.spring.utils.SpringUtils;

/**
 * 
 * @author zyl
 * @date 2016年7月5日 下午7:43:32
 * @version
 * @since
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao<User> userDao;

	/**
	 * 用户登录认证方法
	 * 
	 * @param user
	 *            含有用户帐号和密码的用户对象
	 * @return 认证通过返回字符串“ok”，否则返回认证失败原因
	 */
	@Override
	public String login(User user) {
		User userRst = userDao.selectOne("com.halo.bumf.UserMapper.selectByUserAccount", user);
		if (null == userRst) {
			return SpringUtils.getMessage("user.denied", null);
		}

		SpringUtils.addIntoServletContext(ServiceConstants.SESSION_ID_USER, userRst);
		return ServiceConstants.RETURN_LOGIN_GRANTED;
	}

}