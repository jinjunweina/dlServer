package example.service.impl;

import example.entity.User;
import example.dao.UserDao;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinjw on 2017/5/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String add(User user) {
        String result ="0";
        userDao.save(user);
        return result;
    }
}
