package Katsu.Katsu_spring.service;

import Katsu.Katsu_spring.dto.UserDTO;
import Katsu.Katsu_spring.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // 🔥 핵심: @Service로 등록해야 스프링 빈이 됨
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO findById(long id) {
        return userMapper.findById(id);
    }
}
