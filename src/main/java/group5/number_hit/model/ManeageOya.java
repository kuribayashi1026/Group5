package group5.number_hit.model;

import org.springframework.stereotype.Component;

@Component
public class ManeageOya {
  private int oyaNum;

  public ManeageOya() {
    this.oyaNum = 0;
  }

  public void next(int userNum) {
    this.oyaNum++;
    this.oyaNum %= userNum;
  }

  public int getOyaNum() {
    return oyaNum;
  }

  public void setOyaNum(int oya_num) {
    this.oyaNum = oya_num;
  }

}
