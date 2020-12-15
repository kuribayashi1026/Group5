package group5.number_hit.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import group5.number_hit.model.Match;
import group5.number_hit.model.MatchUser;
import group5.number_hit.model.Room;
import group5.number_hit.model.RoomMapper;
import group5.number_hit.model.User;
import group5.number_hit.model.UserMapper;
import group5.number_hit.model.YubisumaRoom;
//import group5.number_hit.model.Data;
import group5.number_hit.model.DataMapper;

@Controller
@RequestMapping("/yubisuma")
public class yubisumaController {

  @Autowired
  UserMapper userMapper;

  @Autowired
  RoomMapper roomMapper;

  @Autowired
  DataMapper dataMapper;

  @Autowired
  YubisumaRoom room;

  @Autowired
  Match match;

  @GetMapping("index")
  public String yubisuma01(ModelMap model, Principal prin) {

    // ログインユーザ情報
    User user = userMapper.selectUserByName(prin.getName());

    // 入室
    Room r = new Room(user.getId());
    roomMapper.insert_user(r);
    ArrayList<Room> room = roomMapper.selectAll();
    int userNum = roomMapper.countAllUsers();

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("userNum", userNum);

    return "yubisuma.html";
  }

  @GetMapping("start")
  public String yubisuma02(@RequestParam Integer no, ModelMap model, Principal prin) {

    int id = roomMapper.selectIdByNo(no);
    User user = userMapper.selectUserById(id);
    ArrayList<Room> room = roomMapper.selectAll();
    int userNum = roomMapper.countAllUsers();

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("userNum", userNum);
    model.addAttribute("no", no);
    return "match.html";
  }

  @PostMapping("judge")
  public String yubisuma03(@RequestParam Integer hit, ModelMap model, Principal prin) {
    dataMapper.insert_hit(hit);
    model.addAttribute("hit", hit);
    return "judge.html";
  }

  @GetMapping("judge")
  public String yubisuma04(@RequestParam Integer hand, ModelMap model, Principal prin) {

    dataMapper.insert_hand(hand);

    model.addAttribute("hand", hand);

    return "judge.html";
  }

}
