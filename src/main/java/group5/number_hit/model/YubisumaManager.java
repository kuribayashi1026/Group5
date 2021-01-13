package group5.number_hit.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YubisumaManager {
  @Autowired
  RoomMapper roomMapper;

  @Autowired
  DataMapper dataMapper;

  // 現在の親を示す
  private int oyaNo;

  // 親が数を当ててHPが変更されたか
  private boolean isHpUpdated;

  // judge にて判定が行われたか
  private boolean isJudged;

  // ゲームが終了したか
  private boolean isEnd;

  public YubisumaManager() {
    this.reset();
  }

  // 初期化
  public void reset() {
    this.oyaNo = 0;
    this.isHpUpdated = false;
    this.isJudged = false;
    this.isEnd = false;
  }

  // 全員が行動(上げる指の数の選択, 指の合計の予想)したかどうか
  public boolean isAllActed() {
    boolean isAllActed = true;
    for (Data d : dataMapper.selectAll()) {
      isAllActed = isAllActed && d.getIsActed();
    }
    return isAllActed;
  }

  // 全員がnextへ移動したかどうか
  public boolean isAllNext() {
    boolean isAllNext = true;
    for (Data d : dataMapper.selectAll()) {
      isAllNext = isAllNext && d.getIsNext();
    }
    return isAllNext;
  }

  // 親を示す番号を次へ進める
  public void oyaNoNext(int userNum) {

    // ルームリストを取得
    ArrayList<Room> room = roomMapper.selectAll();

    for (int i = 0; i < userNum; i++) {
      this.oyaNo++;
      this.oyaNo %= userNum;
      for (Room r : room) {
        if (r.getNo() == this.oyaNo) {
          return;
        }
      }
    }
  }

  public int getOyaNo() {
    return oyaNo;
  }

  public void setOyaNo(int oya_num) {
    this.oyaNo = oya_num;
  }

  public boolean isHpUpdated() {
    return isHpUpdated;
  }

  public void setHpUpdated(boolean isHpUpdated) {
    this.isHpUpdated = isHpUpdated;
  }

  public boolean isEnd() {
    return isEnd;
  }

  public void setEnd(boolean isEnd) {
    this.isEnd = isEnd;
  }

  public boolean isJudged() {
    return isJudged;
  }

  public void setJudged(boolean isJudged) {
    this.isJudged = isJudged;
  }

}
