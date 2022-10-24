package web.service;

import web.model.User;
import web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void edit(User user) {
        userRepository.saveAndFlush(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
