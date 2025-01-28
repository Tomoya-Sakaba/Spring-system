package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.RoleType;

/**
 * ホームコントローラー
 * 
 * @author sakaba
 */
@Controller
public class HomeController {

	/**
	 * 初期表示
	 * 
	 * @return 表示画面
	 */
    @GetMapping("/home")
    public String homeView(@AuthenticationPrincipal User user, Model model) {
        
        // roleが1だったらtrue それ以外はfalse
        boolean hasUserManegeAuth = user.getAuthorities().stream()
            .allMatch(authority -> authority.getAuthority()
                .equals(RoleType.ADMIN.getRoleType()));
        model.addAttribute("hasUserManegeAuth", hasUserManegeAuth);
        
        return "home";  // src/main/resources/templates/home.htmlを表示
    }
}
