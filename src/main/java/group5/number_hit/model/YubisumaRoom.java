package group5.number_hit.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class YubisumaRoom {
  private ArrayList<MatchUser> users = new ArrayList<>();
  private int memberNum = 0;

  public void addUser(MatchUser user) {
    // 同名のユーザが居たら何もせずにreturn
    for (User u : this.users) {
      if (u.getId() == user.getId()) {
        return;
      }
    }
    // 同名のユーザが居なかった場合はusersにuserを追加する
    user.setNo(memberNum);
    this.users.add(user);
    memberNum++;
  }

  public MatchUser getUser(int index) {
    return getUsers().get(index);
  }

  public int getMemberNum() {
    return memberNum;
  }

  public void setMemberNum(int memberNum) {
    this.memberNum = memberNum;
  }

  public ArrayList<MatchUser> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<MatchUser> users) {
    this.users = users;
  }

}
