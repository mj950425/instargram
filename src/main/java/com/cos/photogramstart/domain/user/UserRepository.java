package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 어노테이션이 없어도 JPA를 상속하면 자동 IOC가 된다.
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
