package org.warmsheep.service;

import org.warmsheep.entity.User;
import org.warmsheep.framework.db.exception.BaseCoreException;

public interface IUserService {
	public User getById(Long id) throws BaseCoreException ;
	public void updateById(User user) throws BaseCoreException ;
}
