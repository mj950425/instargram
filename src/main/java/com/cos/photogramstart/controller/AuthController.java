package com.cos.photogramstart.controller;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.SignupDto;
import com.cos.photogramstart.web.UserFindPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor //final 붙은애를 DI할 때 사용
@Controller
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }


    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
        }
        log.info(signupDto.toString());
        User user = signupDto.toEntity();
        User userEntity = authService.join(user);
        System.out.println(userEntity);
        return "auth/signin";
    }

    @PostMapping(value = "/auth/findpw")
    public Object findpw(@RequestBody UserFindPasswordDTO userFindPasswordDTO) { // username, email,주식경력
        Map<String, String> result = new HashMap<>();

        String username_temp = userFindPasswordDTO.getUsername();
        User user = authService.findUser(username_temp);

        if(user.getPassword() != null && user.getName().equals(userFindPasswordDTO.getName())  && user.getEmail().equals(userFindPasswordDTO.getEmail()) ) {
            String password = authService.updatePassword(userFindPasswordDTO);
            result.put("code", "200");
            result.put("type", "SUCCESS");
            result.put("data", password);

        }else{
            result.put("code", "400");
            result.put("type", "FAIL");
            result.put("message", "입력 정보 오류");
        }

        return result;
    }
}


