package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import threekitties.tonghaeyo.DTO.LoginRequest;
import threekitties.tonghaeyo.domain.Authority;
import threekitties.tonghaeyo.domain.Member;
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
    @ResponseBody
    public String login(HttpServletRequest request, @RequestBody LoginRequest loginRequest)throws Exception{
        Member member = service.login(loginRequest.getName());
        HttpSession session = request.getSession();
        session.setAttribute("sessionId",member.getId());
        if(member.getAuthority().equals(Authority.MANAGER)){
            return "/manager/main";
        }
        if(member.getAuthority().equals(Authority.DRIVER)){
            return "/driver/main";
        }
        if(member.getAuthority().equals(Authority.USER)){
            return "/user/main";
        }
        return "/error/authority";
    }

    @GetMapping("/error/authority")
    public String authorityErrorPage() throws Exception{
        return "pages/error/authority";
    }

}
