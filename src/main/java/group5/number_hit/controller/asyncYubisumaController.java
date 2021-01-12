package group5.number_hit.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import group5.number_hit.model.Room;
import group5.number_hit.model.RoomMapper;
import group5.number_hit.model.User;
import group5.number_hit.model.UserMapper;
import group5.number_hit.sevice.AsyncYubisumaRoom;
//import group5.number_hit.model.Data;
import group5.number_hit.model.DataMapper;
//import group5.number_hit.model.ManeageOya;

@Controller
@RequestMapping("/yubisuma_async")
public class asyncYubisumaController {

  @Autowired
  UserMapper userMapper;

  @Autowired
  RoomMapper roomMapper;

  @Autowired
  DataMapper dataMapper;

  @Autowired
  AsyncYubisumaRoom aRoom;

  @GetMapping("index")
  public String yubisuma01(ModelMap model, Principal prin) {

    // ログインユーザ情報
    User user = userMapper.selectUserByName(prin.getName());

    // 入室
    aRoom.syncJoinRoom(user.getId());

    ArrayList<Room> room = aRoom.syncShowRoomList();
    int userNum = roomMapper.countAllUsers();

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("userNum", userNum);

    return "yubisuma_async.html";
  }

  /**
   * JavaScriptからEventSourceとして呼び出されるGETリクエスト SseEmitterを返すことで，PUSH型の通信を実現する
   *
   * @return
   */
  @GetMapping("room_event_source")
  public SseEmitter roomEvent() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.aRoom.asyncShowRoomList(sseEmitter);
    return sseEmitter;
  }
}
