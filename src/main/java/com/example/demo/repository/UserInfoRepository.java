package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserInfo;

/**
 * ユーザー情報テーブルDAO
 * 
 * @author sakaba
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	/**
	 * 
	 * @param email 検索対象のメールアドレス
	 * @return 指定されたメールアドレスに一致するユーザー情報が存在する場合は {@link Optional} にラップされた {@link UserInfo} を返し、存在しない場合は空の {@link Optional} を返します。
	 */
    Optional<UserInfo> findByEmail(String email);
}
