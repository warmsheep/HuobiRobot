package org.warmsheep.dao.impl;

import org.springframework.stereotype.Repository;
import org.warmsheep.dao.IUserDao;
import org.warmsheep.entity.User;
import org.warmsheep.framework.db.dao.BaseDao;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

}
