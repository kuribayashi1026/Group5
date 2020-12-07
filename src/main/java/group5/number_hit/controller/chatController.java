package group5.number_hit.controller;

import java.security.Principal;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class chatController {

  @GetMapping("index")
  public String chat(ModelMap model, Principal prin) {

    return "chat.html";
  }
}
