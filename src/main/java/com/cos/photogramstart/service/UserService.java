package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User userUpdate(int id, User user) {

        System.out.println(userRepository.findById(id));

        User userEntitiy = userRepository.findById(id).orElseThrow(()->{return new CustomValidationApiException("찾을 수 없는 id입니다.");});

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntitiy.setPassword(encPassword);
        System.out.println(userEntitiy.getPassword());
        userEntitiy.setName(user.getName());
        userEntitiy.setBio(user.getBio());
        userEntitiy.setWebsite(user.getWebsite());
        userEntitiy.setPhone(user.getPhone());
        userEntitiy.setGender(user.getGender());


        return userEntitiy;
    } //더티체킹이 일어나서 업데이트가 완료됨.
}
