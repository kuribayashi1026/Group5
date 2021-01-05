package group5.number_hit.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DataMapper {
  @Select("SELECT * FROM data WHERE id = #{id};")
  Data selectDataById(int id);

  @Select("SELECT SUM(hand) from data;")
  int selectSumHands();

  @Select("SELECT hp from data WHERE id = #{id};")
  int selectHpById(int id);

  @Select("SELECT hit from data WHERE id = #{id};")
  int selectHitById(int id);

  @Select("SELECT * from data;")
  ArrayList<Data> selectAll();

  @Insert("INSERT INTO data(id, hp) VALUES(#{id}, 2);")
  void insertData(int id);

  @Update("UPDATE data SET hand = #{hand} WHERE id = #{id};")
  void updateHand(int id, int hand);

  @Update("UPDATE data SET hit = #{hit} WHERE id = #{id};")
  void updateHit(int id, int hit);

  @Update("UPDATE data SET hp = #{hp} WHERE id = #{id};")
  void updateHp(int id, int hp);

  @Update("UPDATE data SET flag = #{flag} WHERE id = #{id};")
  void updateFlag(int id, int flag);

}
