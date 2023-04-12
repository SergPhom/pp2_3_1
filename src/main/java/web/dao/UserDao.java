package web.dao;

import java.util.List;
import web.model.User;

public interface UserDao {
    //CREATE
    public void saveUser(User user);
    //READ
    public List<User> getAllUsers();
    public User getUserById(Long id);
   //UPDATE
    public void updateUser(User user);
    //DELETE
    public void deleteUser(Long id);
}
