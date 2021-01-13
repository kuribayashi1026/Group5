package group5.number_hit.model;

public class Data {
  private int id;
  private int hp;
  private int hand;
  private int hit;

  // 行動(上げる指の数の選択, 指の合計の予想)したかどうか
  private boolean isActed;

  // nextへ移動したか
  private boolean isNext;

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

  public boolean getIsActed() {
    return isActed;
  }

  public void setIsActed(boolean isActed) {
    this.isActed = isActed;
  }

  public boolean getIsNext() {
    return isNext;
  }

  public void setIsNext(boolean isNext) {
    this.isNext = isNext;
  }

}
