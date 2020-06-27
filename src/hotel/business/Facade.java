package hotel.business;

import hotel.business.model.Privilege;
import hotel.business.model.Room;
import hotel.business.model.User;
import java.util.List;


public interface Facade {
    /**
     * Login method
     * @param username
     * @param password
     * @return user or null if user not exist
     */
    public User login(String username, String password);
    
    public List<User> getAllUsers();
    
    public List<Privilege> getAllPrivileges();
    
    public List<Room> getAllRooms();
    
    public User findUserByUsername(String username);
    
    public User saveUser(User user);
}
