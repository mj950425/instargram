package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    @Value("${file.path}")
    private String uploadFolder;
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename(); // 1.jpg
        System.out.println("이미지 파일이름 : "+imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId).orElseThrow(()->{
            throw new CustomApiException("유저를 찾을 수 없습니다.");
        });
        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
    } // 더티체킹으로 업데이트 됨.

    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId, int principalId) {
        UserProfileDto dto = new UserProfileDto();
        User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });
        dto.setUser(userEntity);
        dto.setImageCount(userEntity.getImages().size());
        dto.setPageOwnerState(pageUserId==principalId);

        int subscribeState = subscribeRepository.mSubscribeState(principalId,pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeCount(subscribeCount);
        dto.setSubscribeState(subscribeState==1);

        // 좋아요 카운트 추가하기
        userEntity.getImages().forEach((image)->{
            image.setLikeCount(image.getLikes().size());
        });

        return dto;
    }

    @Transactional
    public User userUpdate(int id, User user) {


        User userEntitiy = userRepository.findById(id).orElseThrow(()->{return new CustomValidationApiException("찾을 수 없는 id입니다.");});

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntitiy.setPassword(encPassword);
        userEntitiy.setName(user.getName());
        userEntitiy.setBio(user.getBio());
        userEntitiy.setWebsite(user.getWebsite());
        userEntitiy.setPhone(user.getPhone());
        userEntitiy.setGender(user.getGender());


        return userEntitiy;
    } //더티체킹이 일어나서 업데이트가 완료됨.
}
