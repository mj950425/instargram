//package com.cos.photogramstart.service;
//
//package com.tmax.eTest.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FindPasswordService {
//
//    @Autowired
//    private UserService userService;
//
//    public void sendMail(User user) throws Exception{
//
//        //Mail Server 설정
//        String charSet = "utf-8";
//        String hostSMTP = "smtp.naver.com";		//SMTP 서버명
//        String hostSMTPid = ""; 		//네이버 아이디
//        String hostSMTPpw = ""; 	//네이버 비밀번호
//
//        //보내는 사람
//        String fromEmail = "";	//보내는 사람 메일
//        String fromName = "";			//보내는 사람 이름
//
//
//        String subject = ""; 	//메일 제목
//        String msg = "";		//메일 내용
//
//
//        subject = "[HASTAGRAM] 임시 비밀번호 발급 안내";
//        msg += "<div align='left'>";
//        msg += "<h3>";
//        msg += user.getUsername() + "님의 임시 비밀번호입니다.<br>비밀번호를 변경하여 사용하세요.</h3>";
//        msg += "<p>임시 비밀번호 : ";
//        msg += user.getPassword() + "</p></div>";
//
//
//        //email 전송
//        String mailRecipient = user.getEmail(); //받는 사람 이메일 주소
//        try {
//            //객체 선언
//            HtmlEmail mail = new HtmlEmail();
//            mail.setDebug(true);
//            mail.setCharset(charSet);
//            mail.setSSLOnConnect(true); //SSL 사용 (TLS가 없는 경우에 SSL사용)
//            mail.setHostName(hostSMTP);
//            mail.setSmtpPort(587); 		//SMTP 포트 번호
//            mail.setAuthentication(hostSMTPid, hostSMTPpw);
//            mail.setStartTLSEnabled(true); //TLS 사용
//            mail.addTo(mailRecipient, charSet);
//            mail.setFrom(fromEmail, fromName, charSet);
//            mail.setSubject(subject);
//            mail.setHtmlMsg(msg);
//            mail.send();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String findPw(User userDTO) throws Exception {
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String result = null;
//
//        System.out.println("login 확인 : " + userDTO);
//
//        //회원정보 불러오기
//        User user = userService.이메일찾기(userDTO.getEmail());
//
//        System.out.println("유저 정보 확인 : " + user);
//
////        //가입된 이메일이 존재한다면 이메일 발송
////        if(user != null) {
////
////            //임시 비밀번호 생성(UUID 이용 - 특수문자는 못넣음 ㅠㅠ)
////            String tempPw = UUID.randomUUID().toString().replace("-", ""); // -를 제거
////            tempPw = tempPw.substring(0,10); //tempPw를 앞에서부터 10자리 잘라줌
////
////            System.out.println("임시 비밀번호 확인 : " + tempPw);
////
////            //user객체에 임시 비밀번호 담기
////            user.setPw(tempPw);
////
////            //메일 전송
////            MailUtil mail = new MailUtil();
////            mail.sendMail(user);
////
////            //회원 비밀번호를 암호화하여  user객체에 다시 저장
////            String securePw = encoder.encode(user.getPw());
////            user.setPw(securePw);
////
////            //비밀번호 변경
////            usersDAO.modifyPw(user);
////
////            result = "Success";
////
////        } else {
////
////            result = "Fail";
////        }
//
//
//        return result;
//    }
//}
//
