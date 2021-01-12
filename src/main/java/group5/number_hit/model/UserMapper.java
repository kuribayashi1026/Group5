package group5.number_hit.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("SELECT * FROM users")
  ArrayList<User> selectAllUsers();

  @Select("SELECT * FROM users WHERE id = #{id}")
  User selectUserById(int id);

  @Select("SELECT * FROM users WHERE name = #{name}")
  User selectUserByName(String name);

  @Select("SELECT name FROM users WHERE id = #{id}")
  String selectNameById(int id);

  @Select("SELECT id FROM users WHERE name = #{name}")
  int selectIdByName(String name);
}
