package com.example.demo.form;

import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * ログイン画面 Form
 * 
 * @author sakaba
 */
@Data
public class LoginForm {
	
	/** メールアドレス */
    @NotEmpty(message = "{email.required}")
    private String email;

    /** パスワード */
    @NotEmpty(message = "{password.required}")
    private String password;
}
