package com.example.demo.service;

import java.util.Optional;

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
	 * 
	 * @param id
	 * @return
	 */
	public Optional<UserInfo> searchUserById(Integer id) {
		return reqository.findById(id);
	}
}
