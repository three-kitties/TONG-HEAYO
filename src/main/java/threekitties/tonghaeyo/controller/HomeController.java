package threekitties.tonghaeyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import threekitties.tonghaeyo.DTO.LoginRequest;
import threekitties.tonghaeyo.service.HomeService;

@Controller
public class HomeController {

    @Autowired
    private HomeService service;

    @GetMapping("/")
    public String chooseRole() {
        return "pages/main";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        service.login(loginRequest.getId());
    }

}
