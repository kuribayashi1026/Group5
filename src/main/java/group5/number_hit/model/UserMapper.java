package group5.number_hit.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("SELECT * from users")
  ArrayList<User> selectAllUsers();

  @Select("SELECT id from users where name = #{name}")
  ArrayList<User> selectIdByName(String name);

  @Select("SELECT name from users where id = #{id}")
  User selectNameById(int id);

}
