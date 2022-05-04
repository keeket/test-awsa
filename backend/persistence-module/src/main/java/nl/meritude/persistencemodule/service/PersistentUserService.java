package nl.meritude.persistencemodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.persistencemodule.domain.User;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersistentUserService {

    @Autowired
    private UserRepo userRepo;

    public Long newUser(User user) {
        user = userRepo.save(user);
        return user.getId();
    }

    public User getUser(Long userId) throws PersistenceException {
        log.info("Get user with id: {}", userId);
        return userRepo.findById(userId).orElseThrow(() -> new PersistenceException("User does not exist with id: " + userId));
    }

    public User getUser(String email) throws PersistenceException {
        return userRepo.findByEmail(email).orElseThrow(() -> new PersistenceException("No user found with email: " + email));
    }

    public void updateUser(User user) {
        userRepo.save(user);
    }
}
