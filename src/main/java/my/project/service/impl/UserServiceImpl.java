package my.project.service.impl;

import my.project.domain.dto.User;
import my.project.domain.entity.UserEntity;
import my.project.repository.UserRepository;
import my.project.service.UserService;
import my.project.util.mapStruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> getAll() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        return StreamSupport.stream(userEntities.spliterator(), false)
                .map(entity -> UserMapper.INSTANCE.toDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(user);
        return UserMapper.INSTANCE.toDTO(userRepository.save(userEntity));
    }

    @Override
    public User delete(int id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(RuntimeException::new);
        userRepository.delete(userEntity);
        return UserMapper.INSTANCE.toDTO(userEntity);
    }
}
