//package com.cos.photogramstart.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//public class MailController {
//
//    @Autowired
//    private FindPasswordService findPasswordService;
//
//    @PostMapping("pw-find")
//    public String findPw(@RequestBody User user) throws Exception {
//
//        System.out.println("폼에서 받아온 email값 : " + user.getEmail());
//
//        return findPasswordService.findPw(user);
//    }
//
//}
