package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.CMRespDto;
import com.cos.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 모든 exception error를 다 낚아챈다
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
    }
//    @ExceptionHandler(CustomValidationException.class) // 모든 RuntimeException을 아래 함수가 잡아준다.
//    public CMRespDto <Map<String,String>>validationExcdeption(CustomValidationException e) {
//        return new CMRespDto(500,e.getMessage(),e.getErrorMap());
//    }

}
