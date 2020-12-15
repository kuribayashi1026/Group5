package group5.number_hit.model;

public class Room {
  // ユーザーID
  private int no;
  private int id;

  public Room(int no, int id) {
    this.no = no;
    this.id = id;
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

}
