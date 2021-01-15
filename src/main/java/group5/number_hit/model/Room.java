package group5.number_hit.model;

public class Room {
  // ユーザーNO ルームでの入室順を示す
  private int no;
  private int id;
  private String name;

  public Room(int no, int id, String name) {
    this.no = no;
    this.id = id;
    this.name = name;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
