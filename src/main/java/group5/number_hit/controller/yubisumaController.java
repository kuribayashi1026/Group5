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
import group5.number_hit.model.UserMapper;
import group5.number_hit.model.Data;
import group5.number_hit.model.Endgame;
import group5.number_hit.model.DataMapper;
import group5.number_hit.model.ManeageOya;

@Controller
@RequestMapping("/yubisuma")
public class yubisumaController {

  @Autowired
  UserMapper userMapper;

  @Autowired
  Endgame endgame;

  @Autowired
  RoomMapper roomMapper;

  @Autowired
  DataMapper dataMapper;

  @Autowired
  private ManeageOya oyaNum;

  @GetMapping("index")
  public String yubisuma01(ModelMap model, Principal prin) {

    // ログインユーザ情報
    int id = userMapper.selectIdByName(prin.getName());

    // 入室
    Room user = roomMapper.selectRoomById(id);
    int max;
    if (user == null) {
      if (roomMapper.maxNo() == null)
        max = 0;
      else
        max = (int) roomMapper.maxNo() + 1;
      roomMapper.insertUser(max, id, prin.getName());
      user = roomMapper.selectRoomById(id);
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
    Room user = roomMapper.selectRoomByName(prin.getName());

    // ユーザに対応するDataを追加
    Data d = dataMapper.selectDataById(user.getId());
    if (d == null) {
      dataMapper.insertData(user.getId());
      d = dataMapper.selectDataById(user.getId());
    }

    // ルームリストを取得
    ArrayList<Room> room = roomMapper.selectAll();

    // ルームに入室している数を取得
    int userNum = roomMapper.countAllUsers();

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("userNum", userNum);
    model.addAttribute("data", d);
    model.addAttribute("oyaNum", oyaNum.getOyaNum());

    return "match.html";
  }

  // 2回目以降
  @GetMapping("next")
  public String yubisuma02_1(ModelMap model, Principal prin) {

    // ログインユーザ情報
    Room user = roomMapper.selectRoomByName(prin.getName());

    // ユーザーのデータを取得
    Data data = dataMapper.selectDataById(user.getId());

    // ルームリストを取得
    ArrayList<Room> room = roomMapper.selectAll();

    int flag = 1;

    ArrayList<Data> datalist = dataMapper.selectAll();
    for (Data d : datalist) {
      if (d.getFlag() == 0) {
        flag = 0;
        break;
      }
    }
    // ルームに入室している数を取得
    int userNum = roomMapper.countAllUsers();

    if (flag == 1) {
      oyaNum.next(userNum);
      for (Room r : room) {
        dataMapper.updateFlag(r.getId(), 0);
        data = dataMapper.selectDataById(user.getId());
      }
    }

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("userNum", userNum);
    model.addAttribute("data", data);
    model.addAttribute("oyaNum", oyaNum.getOyaNum());

    return "match.html";
  }

  // 親の手選択
  @PostMapping("selecthand")
  public String yubisuma03(@RequestParam Integer hit, ModelMap model, Principal prin) {

    // ログインユーザ情報
    int id = userMapper.selectIdByName(prin.getName());

    // hitの更新
    dataMapper.updateHit(id, hit);

    return "oyahand.html";
  }

  // 判定
  @GetMapping("judge")
  public String yubisuma04(@RequestParam Integer hand, ModelMap model, Principal prin) {

    // ログインユーザ情報
    Room user = roomMapper.selectRoomByName(prin.getName());

    // ルームに入室している数を取得
    int userNum = roomMapper.countAllUsers();

    // handの更新
    dataMapper.updateHand(user.getId(), hand);

    int handsNum = dataMapper.selectSumHands();

    String result = "";

    dataMapper.updateFlag(user.getId(), 1);

    int flag = 1;
    ArrayList<Data> datalist = dataMapper.selectAll();
    for (Data d : datalist) {
      if (d.getFlag() == 0) {
        flag = 0;
        break;
      }
    }
    // ユーザーのデータを取得
    Data data = dataMapper.selectDataById(user.getId());

    if (user.getNo() == oyaNum.getOyaNum() && flag == 1) {
      if (data.getHit() == handsNum) {
        result = "当たり";

        // hpを1減らす
        dataMapper.updateHp(user.getId(), data.getHp() - 1);
        data = dataMapper.selectDataById(user.getId());
        if (data.getHp() == 0) {
          endgame.setV(1);
          result = "あなたの勝利！";

        }
      } else {
        result = "はずれ";
      }
    }

    if (user.getNo() == oyaNum.getOyaNum()) {
      int oyaHit = data.getHit();
      model.addAttribute("oyaHit", oyaHit);
    }
    model.addAttribute("v", endgame.getV());
    model.addAttribute("user", user);
    model.addAttribute("data", data);
    model.addAttribute("result", result);
    model.addAttribute("handsNum", handsNum);
    model.addAttribute("flag", flag);
    model.addAttribute("userNum", userNum);

    return "judge.html";
  }
}
