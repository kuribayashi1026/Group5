package group5.number_hit.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomMapper {
  @Select("SELECT * FROM yubisuma_room")
  ArrayList<Room> selectAll();

  @Select("SELECT no FROM yubisuma_room WHERE id = #{id}")
  int selectNoById(int id);

  @Select("SELECT id FROM yubisuma_room WHERE no = #{no}")
  int selectIdByNo(int no);

  @Select("SELECT id FROM yubisuma_room WHERE id = #{id}")
  int selectIdById(int id);

  @Select("SELECT * FROM yubisuma_room WHERE id = #{id}")
  Room selectRoomById(int id);

  @Select("SELECT COUNT( * ) FROM  yubisuma_room ;")
  int countAllUsers();

  @Insert("INSERT INTO yubisuma_room(no, id) VALUES(#{no}, #{id});")
  // @Options(useGeneratedKeys = true, keyColumn = "no", keyProperty = "no")
  void insertUser(int no, int id);
}
