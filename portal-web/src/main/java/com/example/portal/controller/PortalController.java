package com.example.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.example.common.util.CommonUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PortalController {

    @Autowired
    private CommonUtil commonUtil;

    @GetMapping("/")
    public String home(@RequestParam(value = "token", required = false)String token, @CookieValue(value = "jwt", required = false)String jwtCookie, HttpServletResponse response, Model model) {
        String jwt = jwtCookie;

        //React에서 token이 파라미터로 온경우
        if(token != null && !token.isEmpty()) {
            Cookie cookie = new Cookie("jwt", token);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60); // 1시간 유지
            response.addCookie(cookie);
            return "redirect:/"; // 쿠키가 파라미터에 안남게 설정
        }

        boolean isLoggedIn = (jwt != null && !jwt.isEmpty());

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("portalName", commonUtil.getPortalName());
        model.addAttribute("version", commonUtil.getVersion());
        model.addAttribute("navigationItems", commonUtil.getNavigationItems(isLoggedIn));
        return "portal/home";
    }

    // 직접 리디렉션이 필요한 경우를 위한 메서드 유지
    @GetMapping("/redirect/module1")
    public RedirectView redirectModule1() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/module1/");
        return redirectView;
    }

    @GetMapping("/redirect/module2")
    public RedirectView redirectModule2() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8082/module2/");
        return redirectView;
    }
}