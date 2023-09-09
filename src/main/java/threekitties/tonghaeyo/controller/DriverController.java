package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import threekitties.tonghaeyo.domain.Authority;
import threekitties.tonghaeyo.domain.Location;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.domain.Route;
import threekitties.tonghaeyo.service.MemberService;
import threekitties.tonghaeyo.service.RouteService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/driver")
public class DriverController {

    private final MemberService memberService;
    private final RouteService routeService;

    @GetMapping("/main")
    public String showMain(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(session.getAttribute("sessionId").toString());
        Member driver = memberService.findById(id);

        if (driver.getAuthority().equals(Authority.DRIVER)) {
            return "pages/driver/main";
        } else {
            return "pages/error/authority";
        }
    }

    @GetMapping("/map")
    public String showMap(Model model) {
        // 하드 코딩 된 사용자(수정 필요)
        Member driver = memberService.findByName("driver1");

        model.addAttribute("route", routeService.findByDriver(driver));
        return "pages/driver/map";
    }

    @GetMapping("/start")
    public String setStartPoint(@RequestParam Long lat, @RequestParam Long lng) {
        // 하드 코딩 된 사용자(수정 필요)
        Member driver = memberService.findByName("driver1");

        Location location = new Location(lat, lng);
        Route route = routeService.findByDriver(driver);
        route.addStartPoint(location);

        return "redirect:/driver/map";
    }

    @GetMapping("/stopover")
    public String setStopover(@RequestParam Long lat, @RequestParam Long lng) {
        // 하드 코딩 된 사용자(수정 필요)
        Member driver = memberService.findByName("driver1");

        Location location = new Location(lat, lng);
        Route route = routeService.findByDriver(driver);
        route.addStopovers(location);

        return "redirect:/driver/map";
    }

    @GetMapping("/destination")
    public String setDestination(@RequestParam Long lat, @RequestParam Long lng) {
        // 하드 코딩 된 사용자(수정 필요)
        Member driver = memberService.findByName("driver1");

        Location location = new Location(lat, lng);
        Route route = routeService.findByDriver(driver);
        route.addDestination(location);

        return "redirect:/driver/map";
    }

}
