package org.warmsheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.warmsheep.dao.IUserDao;
import org.warmsheep.entity.User;
import org.warmsheep.framework.db.exception.BaseCoreException;

@Service
@Transactional
public class UserService implements IUserService{

	public User getById(Long id) throws BaseCoreException {
		return userDao.getById(id);
	}
	
	@Override
	public void updateById(User user) throws BaseCoreException {
		userDao.updateById(user);
	}
	
	@Autowired
	private IUserDao userDao;

	
}
