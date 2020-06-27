package hotel.business;

import hotel.business.model.Privilege;
import hotel.business.model.Room;
import hotel.business.model.User;
import hotel.controller.constants.Constants;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FacadeImplementation implements Facade {

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        EntityManager entityManager = getEntityManager();
        //Named queries 
        Query query = entityManager.createNamedQuery("User.findByUsernameAndPassword");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException noResultException) {
            System.err.println("Not exist user: " + noResultException.getMessage());
            return null;
        } catch (NonUniqueResultException nonUniqueResultException) {
            throw new RuntimeException(nonUniqueResultException.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = getEntityManager();
        Query namedQuery = em.createNamedQuery("User.findAll");
        return namedQuery.getResultList();
    }

    @Override
    public List<Privilege> getAllPrivileges() {
        return getEntityManager()
                .createNamedQuery("Privilege.findAll")
                .getResultList();
    }

    @Override
    public List<Room> getAllRooms() {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Room.findAll").getResultList();
    }

    @Override
    public User findUserByUsername(String username) {
        User user = null;
        EntityManager em = getEntityManager();
        Query namedQuery = em.createNamedQuery("User.findByUsername");
        try {
            user = (User) namedQuery.getSingleResult();
        } catch (NoResultException noResultException) {

        } catch (NonUniqueResultException nonUniqueResultException) {
            System.err.println("[Nije unique username]" + nonUniqueResultException.getMessage());
        }
        return user;
    }

    @Override
    public User saveUser(User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    private EntityManager getEntityManager() {
        //SessionBeanFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PU_NAME);
        //EntityManager
        return entityManagerFactory.createEntityManager();
    }

}
