package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao dao;

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }

    @Override
    @Transactional
    public void updateUser( User user) {
        dao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        dao.deleteUser(id);
    }
}
