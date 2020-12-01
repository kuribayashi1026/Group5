package group5.number_hit.model;

public class Match {
  int id;
  int user_1;
  int user_2;
  int user_3;
  int user_4;
  boolean is_active;
  int user_1_hand;
  int user_2_hand;
  int user_3_hand;
  int user_4_hand;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser_1() {
    return this.user_1;
  }

  public void setUser_1(int user_1) {
    this.user_1 = user_1;
  }

  public int getUser_2() {
    return this.user_2;
  }

  public void setUser_2(int user_2) {
    this.user_2 = user_2;
  }

  public int getUser_3() {
    return user_3;
  }

  public void setUser_3(int user_3) {
    this.user_3 = user_3;
  }

  public int getUser_4() {
    return user_4;
  }

  public void setUser_4(int user_4) {
    this.user_4 = user_4;
  }

  public int getUser_1_hand() {
    return this.user_1_hand;
  }

  public void setUser_1_hand(int user_1_hand) {
    this.user_1_hand = user_1_hand;
  }

  public int getUser_2_hand() {
    return this.user_2_hand;
  }

  public void setUser_2_hand(int user_2_hand) {
    this.user_2_hand = user_2_hand;
  }

  public int getUser_3_hand() {
    return this.user_3_hand;
  }

  public void setUser_3_hand(int user_3_hand) {
    this.user_3_hand = user_3_hand;
  }

  public int getUser_4_hand() {
    return this.user_4_hand;
  }

  public void setUser_4_hand(int user_4_hand) {
    this.user_4_hand = user_4_hand;
  }

  public boolean isIs_active() {
    return is_active;
  }

  public void setIs_active(boolean is_active) {
    this.is_active = is_active;
  }
}
