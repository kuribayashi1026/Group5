package group5.number_hit.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomMapper {
  @Select("SELECT COUNT( * ) FROM  yubisuma_room ;")
  int countAllUsers();

  @Insert("INSERT INTO yubisuma_room(USER_ID) VALUES(#{USER_ID});")
  void insert_user();
}
