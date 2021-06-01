package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.web.auth.UserFindPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

//    @Transactional(readOnly = true)
//    public User findUser(String username) {
//        User userEntity = userRepository.findByUsername(username).orElseGet(()->{
//            return new User();
//        });
//        return userEntity;
//    }
//    @Transactional
//    public String updatePassword(UserFindPasswordDTO userFindPasswordDTO) {
//
//        User persistance = findUser(userFindPasswordDTO.getUsername());
//        String tempPw = UUID.randomUUID().toString().replace("-", "");
//        tempPw = tempPw.substring(0,10);
//        String encPassword = encoder.encode(tempPw);
//        persistance.setPassword(encPassword);
//        return tempPw;
//    }

    @Transactional
    public User join(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
