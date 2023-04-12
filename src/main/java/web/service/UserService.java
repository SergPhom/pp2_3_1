package web.service;

import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;
import web.model.User;

import java.util.List;

public interface UserService {
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
