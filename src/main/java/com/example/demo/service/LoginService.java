package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Service
 * 
 * @author sakaba
 */
@Service
@RequiredArgsConstructor
public class LoginService {
	/** 
	 * {@link UserInfoRepository}：ユーザー情報テーブルDAO 
	 * fainalで定義したことによって@RequiredArgsConstructorが機能して、
	 * newしたものを注入するコンストラクタを自動生成
	 */
	private final UserInfoRepository reqository;
	
	/**
	 * パスワードハッシュ化
	 */
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報テーブルから、指定されたメールアドレスに基づいてユーザーを検索し、
	 * 入力されたパスワードがデータベースに保存されているパスワードと一致するかを確認します。
	 * 一致すれば認証成功となり、`true` を返します。それ以外の場合は `false` を返します。
	 *
	 * @param inputEmail ユーザーが入力したメールアドレス
	 * @param inputPassword ユーザーが入力したパスワード
	 * @return パスワードが一致すれば `true`、一致しなければ `false`。ユーザーが見つからない場合も `false` を返します。
	 * @see UserInfo#findByEmail(String) ユーザー情報をメールアドレスで検索するメソッド
	 * @see UserInfo#getPassword() ユーザー情報のパスワードを取得するメソッド
	 */
	public boolean authenticateUser(String inputEmail, String inputPassword) {
		Optional<UserInfo> user = reqository.findByEmail(inputEmail);

		// ハッシュ化されたパスワードとの照合
	    return user.map(u -> passwordEncoder.matches(inputPassword, u.getPassword()))
	               .orElse(false);
	}
	
	
	public Optional<UserInfo> searchUserById(Integer id) {
		return reqository.findById(id);
	}
}
