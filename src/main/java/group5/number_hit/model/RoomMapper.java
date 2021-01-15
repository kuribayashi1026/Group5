package group5.number_hit.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomMapper {
  @Select("SELECT * FROM yubisuma_room")
  ArrayList<Room> selectAll();

  @Select("SELECT * FROM yubisuma_room WHERE no = #{no}")
  Room selectRoomByNo(int no);

  @Select("SELECT * FROM yubisuma_room WHERE id = #{id}")
  Room selectRoomById(int id);

  @Select("SELECT * FROM yubisuma_room WHERE name = #{name}")
  Room selectRoomByName(String name);

  @Select("SELECT COUNT( * ) FROM yubisuma_room")
  int countAllUsers();

  @Insert("INSERT INTO yubisuma_room(no, id, name) VALUES(#{no}, #{id}, #{name})")
  void insertUser(int no, int id, String name);

  @Delete("DELETE FROM yubisuma_room WHERE id = #{id}")
  void deleteUserById(int id);
}
