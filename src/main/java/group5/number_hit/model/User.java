package group5.number_hit.model;

public class User {
  // ユーザーID
  private int id;

  // 名前
  private String name;

  public User() {
  }

  public User(int id, String name) {
    setId(id);
    setName(name);
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
