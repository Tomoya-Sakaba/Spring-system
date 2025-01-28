package com.example.demo.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報生成
 * 
 * @author sakaba
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    /** ユーザー情報テーブルRepository */
    private final UserInfoRepository repository;
    
    /**
     *  ユーザー情報生成
     *  
     *  @param username メールアドレス
     *  @throws UsernameNotFondExeption
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = repository.findByEmail(username)  //RepositoryクラスのEmailから特定のユーザー情報を取得
            .orElseThrow(() -> new UsernameNotFoundException(username));  // 取得できなければ例外を返し、FailureHandler(認証エラー)が呼ばれる
        
        return User.withUsername(userInfo.getName())  //TODO 認証したユーザーは3つの情報（name, password, role）しか保持できないので考える
            .password(userInfo.getPassword())
            .authorities(userInfo.getRole()) // 権限の数字を取得
            .build();
    }

}
