package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDTO findById(long id);
}
