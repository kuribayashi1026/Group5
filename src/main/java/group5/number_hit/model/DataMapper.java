package group5.number_hit.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataMapper {
  @Select("SELECT hp FROM data ;")
  Data selectDataById(int id);

  @Insert("INSERT INTO data(hand) VALUES(#{hand});")
  void insert_hand(int hand);
}
