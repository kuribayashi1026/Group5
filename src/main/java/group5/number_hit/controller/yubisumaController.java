package group5.number_hit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import group5.number_hit.model.Match;
import group5.number_hit.model.MatchUser;
import group5.number_hit.model.User;
import group5.number_hit.model.UserMapper;
import group5.number_hit.model.YubisumaRoom;

@Controller
@RequestMapping("/yubisuma")
public class yubisumaController {

  @Autowired
  UserMapper userMapper;

  @Autowired
  YubisumaRoom room;

  @Autowired
  Match match;

  @GetMapping("index")
  public String yubisuma01(ModelMap model, Principal prin) {

    // ログインユーザ情報
    User user = userMapper.selectUserByName(prin.getName());
    MatchUser m_user = new MatchUser(user);

    // 入室
    this.room.addUser(m_user);

    model.addAttribute("user", m_user);
    model.addAttribute("room", this.room);
    model.addAttribute("match", this.match);

    return "yubisuma.html";
  }

  @GetMapping("start")
  public String yubisuma02(@RequestParam Integer no, ModelMap model, Principal prin) {

    MatchUser m_user = this.room.getUser(no);

    // match開始
    this.match.matchInit(room);

    model.addAttribute("user", m_user);
    model.addAttribute("room", this.room);
    model.addAttribute("match", this.match);

    return "match.html";
  }

}
