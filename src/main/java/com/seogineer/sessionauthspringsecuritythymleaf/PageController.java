package com.seogineer.sessionauthspringsecuritythymleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PageController {
    @GetMapping({"/", "/login"})
    public String login(){
        return "login";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }
}
