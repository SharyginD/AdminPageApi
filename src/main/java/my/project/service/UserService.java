package my.project.service;

import my.project.domain.dto.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    Collection<User> getAll();

    User save(User user);

    User delete(int id);
}
