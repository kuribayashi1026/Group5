package group5.number_hit.model;

import org.springframework.stereotype.Component;

@Component
public class Match {
  private boolean isActive = false;
  private int matchMemberNum;
  private int houseNum;

  public Match() {
    setIsActive(false);
    setMatchMemberNum(0);
    setHouseNum(0);
  }

  public void matchInit(YubisumaRoom room) {
    setIsActive(true);
    setMatchMemberNum(room.getMemberNum());
    setHouseNum(0);
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public int getHouseNum() {
    return houseNum;
  }

  public void setHouseNum(int houseNum) {
    this.houseNum = houseNum;
  }

  public int getMatchMemberNum() {
    return matchMemberNum;
  }

  public void setMatchMemberNum(int matchMemberNum) {
    this.matchMemberNum = matchMemberNum;
  }
}
