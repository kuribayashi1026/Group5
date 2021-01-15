package group5.number_hit.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import group5.number_hit.model.Room;
import group5.number_hit.model.RoomMapper;
import group5.number_hit.model.Data;
import group5.number_hit.model.DataMapper;

@Component
public class MyLogoutHandler implements LogoutHandler {

  @Autowired
  RoomMapper roomMapper;

  @Autowired
  DataMapper dataMapper;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    // 何かの処理
    // authentication.getName() でログインIDが取れる
    if (authentication.getName() != null) {
      Room user = roomMapper.selectRoomByName(authentication.getName());
      Data data = dataMapper.selectDataById(user.getId());

      if (data != null) {
        dataMapper.deleteDataById(user.getId());
      }
      if (user != null) {
        roomMapper.deleteUserById(user.getId());
      }
    }
  }
}
