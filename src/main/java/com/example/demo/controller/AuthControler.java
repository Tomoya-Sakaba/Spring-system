package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.ErrorMessageConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * 認証コントローラー
 * 
 * @author sakaba
 */
@Controller
@RequiredArgsConstructor
public class AuthControler {
	
	/** ログインservice */
	private final LoginService service;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
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
	 * ログイン処理
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@PostMapping("/login")
	public String login(Model model, @Validated LoginForm form, BindingResult bdResult) {
		if(bdResult.hasErrors()) {
			return "login";
		}
		
		/*
		 * emailとpasswordが一致しているか true/false で変数に格納
		 * getEmail()とgetPassword()はLoginFormクラスの@Dataと定義しているから自動的にgetメソッドが使える
		*/
		boolean isCorrectUserAuth = service.authenticateUser(form.getEmail(), form.getPassword());
		
		/*
		 * true：ログイン成功
		 * false：ログイン失敗
		 */
		if (isCorrectUserAuth) {
			// redirect:+リダイレクト先のリクエストパス → HomeControllerに「/home」を定義している
			return "redirect:/home";
		} else {
			// メッセージソース取得＆格納
			String errorMsg = AppUtil.getMessage(messageSource, ErrorMessageConst.LOGIN_WRONG_INPUT);
			// リクエストスコープ
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}
	}

	@PostMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
}
