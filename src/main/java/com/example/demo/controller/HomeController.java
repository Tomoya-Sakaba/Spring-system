package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String home() {
        return "home";  // src/main/resources/templates/home.htmlを表示
    }
}
