package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.form.LoginForm;

import lombok.RequiredArgsConstructor;

/**
 * 認証コントローラー
 * 
 * @author sakaba
 */
@Controller
@RequiredArgsConstructor
public class AuthControler {
	/** セッション情報 */
	private final HttpSession sesion;
	
	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@GetMapping("/login")
	public String view(Model model, LoginForm form) {
		return "login";
	}
	
	/**
     * ログインエラー表示表示
     * 
     * @param model モデル
     * @param form 入力情報
     * @return 表示画面
     */
    @GetMapping(value = "/login", params = "error")
    public String viewWithErro(Model model, LoginForm form) {
        Exception errorInfo = (Exception)sesion.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        model.addAttribute("errorMsg", errorInfo.getMessage());
        return "login";
    }
}
