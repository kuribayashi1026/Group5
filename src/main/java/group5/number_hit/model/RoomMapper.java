package group5.number_hit.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomMapper {
  @Select("SELECT * FROM yubisuma_room")
  ArrayList<Room> selectAll();

  @Select("SELECT no FROM yubisuma_room WHERE id = #{id}")
  int selectNoById(int id);

  @Select("SELECT id FROM yubisuma_room WHERE no = #{no}")
  int selectIdByNo(int no);

  @Select("SELECT COUNT( * ) FROM  yubisuma_room ;")
  int countAllUsers();

  @Insert("INSERT INTO yubisuma_room(id) VALUES(#{id});")
  @Options(useGeneratedKeys = true, keyColumn = "no", keyProperty = "no")
  void insert_user(Room room);
}
