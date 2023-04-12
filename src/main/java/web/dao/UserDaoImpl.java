package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void saveUser(User user) {
       try(Session session = sessionFactory.openSession()) {
           session.save(user);
       }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()){
            TypedQuery<User> query = session.createQuery("from User ");
            return query.getResultList();
        }
    }

    @Override
    public User getUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session
                    .createQuery("select u from User as u where u.id = :uId");
            query.setParameter("uId", id);
            return query.getSingleResult();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("update User u set u.name = :uName  where u.id = :uId")
                    .setParameter("uId", user.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void deleteUser(Long id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User u where u.id = :uId")
                   .setParameter("uId", id)
                    .executeUpdate();
            transaction.commit();
        }
    }
}
