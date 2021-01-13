package group5.number_hit.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT id FROM users WHERE name = #{name}")
  int selectIdByName(String name);
}
