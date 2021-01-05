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

import group5.number_hit.model.Room;
import group5.number_hit.model.RoomMapper;
import group5.number_hit.model.User;
import group5.number_hit.model.UserMapper;
//import group5.number_hit.model.Data;
import group5.number_hit.model.DataMapper;
import group5.number_hit.model.ManeageOya;

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
  private ManeageOya oyaNum;

  @GetMapping("index")
  public String yubisuma01(ModelMap model, Principal prin) {

    // ログインユーザ情報
    User user = userMapper.selectUserByName(prin.getName());

    // 入室
    Room r = roomMapper.selectRoomById(user.getId());
    if (r == null) {
      roomMapper.insertUser(roomMapper.countAllUsers(), user.getId());
    }

    ArrayList<Room> room = roomMapper.selectAll();
    int userNum = roomMapper.countAllUsers();

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("userNum", userNum);

    return "yubisuma.html";
  }

  @GetMapping("start")
  public String yubisuma02(ModelMap model, Principal prin) {

    // ログインユーザ情報
    User user = userMapper.selectUserByName(prin.getName());

    // no取得
    int no = roomMapper.selectNoById(user.getId());

    // ユーザに対応するDataを追加
    dataMapper.insertData(user.getId());

    // hp取得
    int hp = dataMapper.selectHpById(user.getId());
    model.addAttribute("hp", hp);

    // ルームリストを取得
    ArrayList<Room> room = roomMapper.selectAll();

    // ルームに入室している数を取得
    int userNum = roomMapper.countAllUsers();

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("userNum", userNum);
    model.addAttribute("no", no);
    model.addAttribute("oyaNum", oyaNum.getOyaNum());

    return "match.html";
  }

  // 親
  @PostMapping("selecthand")
  public String yubisuma03(@RequestParam Integer hit, ModelMap model, Principal prin) {

    // ログインユーザ情報
    User user = userMapper.selectUserByName(prin.getName());

    // hitの更新
    dataMapper.updateHit(user.getId(), hit);

    return "oyahand.html";
  }

  // 子
  @GetMapping("judge")
  public String yubisuma04(@RequestParam Integer hand, ModelMap model, Principal prin) {

    // ログインユーザ情報
    User user = userMapper.selectUserByName(prin.getName());
    // handの更新
    dataMapper.updateHand(user.getId(), hand);
    // noの取得
    int no = roomMapper.selectNoById(user.getId());
    // hpの取得
    int hp = dataMapper.selectHpById(user.getId());
    // 手の数の合計の取得
    int handsNum = dataMapper.selectSumHands();

    String txt = "";

    if (no == oyaNum.getOyaNum()) {
      // hitの取得
      int hit = dataMapper.selectHitById(user.getId());

      if (hit == handsNum) {
        txt = "当たり";

        // hpを1減らす
        dataMapper.updateHp(user.getId(), --hp);
      } else {
        txt = "はずれ";
      }
    }

    model.addAttribute("user", user);
    model.addAttribute("no", no);
    model.addAttribute("txt", txt);
    model.addAttribute("hp", hp);
    model.addAttribute("hand", hand);
    model.addAttribute("handsNum", handsNum);

    return "judge.html";
  }

}
