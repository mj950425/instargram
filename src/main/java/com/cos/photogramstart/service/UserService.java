package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User userUpdate(int id, User user) {
        //1. 영속화
        System.out.println(userRepository.findById(id));
        User userEntitiy = userRepository.findById(id).get();
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        //2. 영속화된 Object를 수정
        userEntitiy.setName(user.getName());
        userEntitiy.setPassword(user.getPassword());
        userEntitiy.setBio(user.getBio());
        userEntitiy.setWebsite(user.getWebsite());
        userEntitiy.setPhone(user.getPhone());
        userEntitiy.setGender(user.getGender());


        return userEntitiy;
    } //더티체킹이 일어나서 업데이트가 완료됨.
}
