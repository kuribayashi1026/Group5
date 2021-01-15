package group5.number_hit.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DataMapper {
  @Select("SELECT * from data")
  ArrayList<Data> selectAll();

  @Select("SELECT * FROM data WHERE id = #{id}")
  Data selectDataById(int id);

  @Select("SELECT hit from data WHERE id = #{id}")
  Integer selectHitById(int id);

  @Select("SELECT SUM(hand) from data")
  Integer selectSumHands();

  @Select("SELECT SUM(hp) from data")
  Integer selectSumHp();

  @Insert("INSERT INTO data(id, hp, isActed, isNext) VALUES(#{id}, 2, false, true)")
  void insertData(int id);

  @Delete("DELETE FROM data WHERE id = #{id}")
  void deleteDataById(int id);

  @Update("UPDATE data SET hand = #{hand} WHERE id = #{id}")
  void updateHand(int id, int hand);

  @Update("UPDATE data SET hit = #{hit} WHERE id = #{id}")
  void updateHit(int id, int hit);

  @Update("UPDATE data SET hp = #{hp} WHERE id = #{id}")
  void updateHp(int id, int hp);

  @Update("UPDATE data SET isActed = #{isActed} WHERE id = #{id}")
  void updateIsActed(int id, boolean isActed);

  @Update("UPDATE data SET isNext = #{isNext} WHERE id = #{id}")
  void updateIsNext(int id, boolean isNext);

}
