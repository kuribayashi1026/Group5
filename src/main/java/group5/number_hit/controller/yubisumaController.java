package group5.number_hit.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import group5.number_hit.model.Data;
import group5.number_hit.model.DataMapper;
import group5.number_hit.model.Room;
import group5.number_hit.model.RoomMapper;
import group5.number_hit.model.UserMapper;
import group5.number_hit.model.YubisumaManager;

@Controller
@RequestMapping("/yubisuma")
@Transactional
public class YubisumaController {

  final private int MAX_USER_NUM = 4;

  @Autowired
  UserMapper userMapper;

  @Autowired
  RoomMapper roomMapper;

  @Autowired
  DataMapper dataMapper;

  @Autowired
  YubisumaManager gameManager;

  @GetMapping("index")
  public String yubisuma01(ModelMap model, Principal prin) {

    // ログインユーザ情報
    int id = userMapper.selectIdByName(prin.getName());

    // 入室
    Room user = roomMapper.selectRoomById(id);
    ArrayList<Room> room = roomMapper.selectAll();
    if (room == null) {
      roomMapper.insertUser(0, id, prin.getName());
    } else if (user == null) {
      // room にnoが存在するか
      boolean f;
      for (int i = 0; i < MAX_USER_NUM; i++) {
        f = false;
        for (Room r : room) {
          if (r.getNo() == i)
            f = true;
        }

        if (!f) {
          roomMapper.insertUser(i, id, prin.getName());
          break;
        }
      }
    }

    // ルームに入室している数
    model.addAttribute("userNum", roomMapper.countAllUsers());

    // ルーム
    model.addAttribute("room", roomMapper.selectAll());

    model.addAttribute("user", roomMapper.selectRoomById(id));

    return "yubisuma.html";
  }

  @GetMapping("start")
  public String yubisuma02(ModelMap model, Principal prin) {

    // ログインユーザ情報
    Room user = roomMapper.selectRoomByName(prin.getName());

    // ユーザに対応するDataを追加
    Data data = dataMapper.selectDataById(user.getId());

    if (data == null) {
      dataMapper.insertData(user.getId());
      data = dataMapper.selectDataById(user.getId());
    }

    // 初期化
    gameManager.reset();

    // HPの合計
    model.addAttribute("sumHp", dataMapper.selectSumHp());

    // 親を示すNo
    model.addAttribute("oyaNum", gameManager.getOyaNo());

    // ルーム
    model.addAttribute("room", roomMapper.selectAll());

    model.addAttribute("user", user);
    model.addAttribute("data", data);

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

    // ルームに入室している数を取得
    int userNum = roomMapper.countAllUsers();

    // nextへ移動したことを記録
    dataMapper.updateIsNext(user.getId(), true);

    if (gameManager.isAllActed()) {
      gameManager.oyaNoNext(userNum);
      gameManager.setHpUpdated(false);
      for (Room r : room) {
        dataMapper.updateIsActed(r.getId(), false);
      }
      data = dataMapper.selectDataById(user.getId());
    }

    // ルームの全ユーザーがnextへ移動し, isJudgeが立っているときisJudgeをおろす
    if (gameManager.isJudged() && gameManager.isAllNext()) {
      gameManager.setJudged(false);
    }

    model.addAttribute("user", user);
    model.addAttribute("room", room);
    model.addAttribute("data", data);
    model.addAttribute("sumHp", dataMapper.selectSumHp());
    model.addAttribute("userNum", userNum);
    model.addAttribute("oyaNum", gameManager.getOyaNo());

    return "match.html";
  }

  // 親の手選択
  @PostMapping("selecthand")
  public String yubisuma03(@RequestParam(required = false) Integer hit, ModelMap model, Principal prin) {

    // ログインユーザ情報
    Room user = roomMapper.selectRoomByName(prin.getName());

    // hitの更新
    if (hit != null)
      dataMapper.updateHit(user.getId(), hit);

    // ユーザーのデータを取得
    Data data = dataMapper.selectDataById(user.getId());
    model.addAttribute("hp", data.getHp());

    model.addAttribute("user", user);

    return "selecthand.html";
  }

  // 判定
  @GetMapping("judge")

  public String yubisuma04(@RequestParam(required = false) Integer hand, ModelMap model, Principal prin) {

    // ログインユーザ情報
    Room user = roomMapper.selectRoomByName(prin.getName());

    // ユーザーのデータを取得
    Data data = dataMapper.selectDataById(user.getId());

    // ユーザーが親かどうか
    boolean isOya = user.getNo() == gameManager.getOyaNo();

    // nextへの移動フラグを初期化
    dataMapper.updateIsNext(user.getId(), false);

    // handの更新
    if (hand != null)
      dataMapper.updateHand(user.getId(), hand);

    if (!data.getIsActed()) {
      dataMapper.updateIsActed(user.getId(), true);
      data = dataMapper.selectDataById(user.getId());
    }

    // 親の予想した数を取得
    Room oya = roomMapper.selectRoomByNo(gameManager.getOyaNo());
    Integer oyaHit = dataMapper.selectHitById(oya.getId());

    if (gameManager.isAllActed()) {

      // 指の数の合計
      int handsNum = dataMapper.selectSumHands();
      model.addAttribute("handsNum", handsNum);

      if (isOya && data.getHit() == handsNum && !gameManager.isHpUpdated()) {
        // hpを1減らす(0以下にならないようにする)
        dataMapper.updateHp(user.getId(), Math.max(0, data.getHp() - 1));
        data = dataMapper.selectDataById(user.getId());
        gameManager.setHpUpdated(true);

        if (data.getHp() <= 0)
          gameManager.setEnd(true);
      }

      if (oyaHit != null) {
        gameManager.setJudged(true);

        if (oyaHit == handsNum)
          model.addAttribute("result", "当たり");
        else
          model.addAttribute("result", "はずれ");

      }
    }

    // ルームに入室している数
    model.addAttribute("userNum", roomMapper.countAllUsers());

    model.addAttribute("user", user);
    model.addAttribute("data", data);
    model.addAttribute("isOya", isOya);
    model.addAttribute("oyaHit", oyaHit);
    model.addAttribute("isJudged", gameManager.isJudged());
    model.addAttribute("isEnd", gameManager.isEnd());

    return "judge.html";
  }

  @GetMapping("result")
  public String yubisuma05(ModelMap model, Principal prin) {

    // ログインユーザ情報
    Room user = roomMapper.selectRoomByName(prin.getName());
    Data data = dataMapper.selectDataById(user.getId());

    // ユーザーが親かどうか
    boolean isOya = user.getNo() == gameManager.getOyaNo();

    // 親ユーザーの取得
    Room oya = roomMapper.selectRoomByNo(gameManager.getOyaNo());

    model.addAttribute("user", user);
    model.addAttribute("data", data);
    model.addAttribute("isOya", isOya);
    model.addAttribute("oya", oya);

    return "result.html";
  }
}
