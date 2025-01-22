package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * ユーザー情報テーブル Entity
 * 
 * @author sakaba
 */
@Entity
@Table(name = "users")
@Data
public class UserInfo {
	
	/** ユーザーID */
	@Id
	private Integer id;
	
	/** 権限(1:admin, 2:manager, 3:normal) */
	private Integer role;
	
	/** ユーザー名 */
	private String name;
	
	/** メールアドレス */
	private String email;
	
	/** パスワード */
	private String password;

}
