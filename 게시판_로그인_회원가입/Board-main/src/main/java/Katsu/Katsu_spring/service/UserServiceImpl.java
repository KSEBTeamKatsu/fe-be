package Katsu.Katsu_spring.service;

import Katsu.Katsu_spring.dto.UserDTO;
import Katsu.Katsu_spring.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // @Service로 등록해야 스프링 빈이 됨
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findById(String id) {
        return userMapper.findById(id);
    }
}
