package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }

    @Override
    @Transactional
    public void updateUser( User user) {
        user.setId(user.getId());
        repository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
