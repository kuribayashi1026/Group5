package group5.number_hit.model;

public class Data {
  private int id;
  private int hp;
  private int hand;
  private int hit;
  private int flag;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getHand() {
    return hand;
  }

  public void setHand(int hand) {
    this.hand = hand;
  }

  public int getHit() {
    return hit;
  }

  public void setHit(int hit) {
    this.hit = hit;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    if (flag != 0)
      flag = 1;
    this.flag = flag;
  }

}
