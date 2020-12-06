package group5.number_hit.model;

public class MatchUser extends User {

  // ルーム入室順のNo
  private int no;

  // 手の数(体力)
  private int handNum;

  // アクションを行ったか
  private boolean isActed;

  public MatchUser(User user) {
    super(user.getId(), user.getName());
    setNo(0);
    setHandNum(2);
    setIsActed(false);
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getHandNum() {
    return handNum;
  }

  public void setHandNum(int handNum) {
    this.handNum = handNum;
  }

  public boolean getIsActed() {
    return isActed;
  }

  public void setIsActed(boolean isActed) {
    this.isActed = isActed;
  }

}
