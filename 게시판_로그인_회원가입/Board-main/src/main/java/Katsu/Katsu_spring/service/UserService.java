package Katsu.Katsu_spring.service;

import Katsu.Katsu_spring.dto.UserDTO;

public interface UserService {

    UserDTO findById(String id);
}
