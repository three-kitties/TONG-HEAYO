package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import threekitties.tonghaeyo.domain.*;
import threekitties.tonghaeyo.service.MemberService;
import threekitties.tonghaeyo.service.OrganizationService;
import threekitties.tonghaeyo.service.RouteService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/driver")
public class DriverController {

    private final MemberService memberService;
    private final OrganizationService organizationService;
    private final RouteService routeService;

    @GetMapping("/main")
    public String showMain(HttpServletRequest request) throws Exception {
        Member driver = getDriver(request);

        if (driver == null) return "pages/error/authority";

        return "pages/driver/main";
    }

    @GetMapping("/map")
    public String showMap(Model model, HttpServletRequest request) {
        Member driver = getDriver(request);

        if (driver == null) return "pages/error/authority";

        model.addAttribute("route", routeService.findByDriver(driver));
        return "pages/driver/map";
    }

    @GetMapping("/start")
    public String setStartPoint(@RequestParam Long lat, @RequestParam Long lng, HttpServletRequest request) {
        Member driver = getDriver(request);

        if (driver == null) return "pages/error/authority";

        Location location = new Location(lat, lng);
        Route route = routeService.findByDriver(driver);
        route.addStartPoint(location);

        return "redirect:/driver/map";
    }

    @GetMapping("/stopover")
    public String setStopover(@RequestParam Long lat, @RequestParam Long lng, HttpServletRequest request) {
        Member driver = getDriver(request);

        if (driver == null) return "pages/error/authority";

        Location location = new Location(lat, lng);
        Route route = routeService.findByDriver(driver);
        route.addStopovers(location);

        return "redirect:/driver/map";
    }

    @GetMapping("/destination")
    public String setDestination(@RequestParam Long lat, @RequestParam Long lng, HttpServletRequest request) {
        Member driver = getDriver(request);

        if (driver == null) return "pages/error/authority";

        Location location = new Location(lat, lng);
        Route route = routeService.findByDriver(driver);
        route.addDestination(location);

        return "redirect:/driver/map";
    }

    @GetMapping("/save/{id}")
    public String saveRoute(@PathVariable Long id, HttpServletRequest request) {
        Member driver = getDriver(request);

        if (driver == null) return "pages/error/authority";

        Route route = routeService.findByDriver(driver);
        Organization organization = organizationService.findById(id);

        organizationService.saveRoute(organization, route);

        return "redirect:/driver/map";
    }

    private Member getDriver(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(session.getAttribute("sessionId").toString());
        Member driver = memberService.findById(id);

        if (!driver.getAuthority().equals(Authority.DRIVER)) {
            return null;
        }

        return driver;
    }

}
