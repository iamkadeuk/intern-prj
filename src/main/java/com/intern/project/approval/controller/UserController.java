package com.intern.project.approval.controller;

import com.intern.project.approval.domain.User;
import com.intern.project.approval.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[테스트] 사용자등록관리")
@RestController
@RequestMapping("/approval")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(description = "[테스트] 사용자 조회")
    @GetMapping(path = "/searchUser")
    public ResponseEntity<User> searchUser(String userId) {
        User user = userService.getUser(userId);
        log.info("### user={}", user.toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(description = "[테스트] 사용자 삭제")
    @DeleteMapping(path = "/deleteUser")
    public ResponseEntity deleteUser(String userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "[테스트] 사용자 추가")
    @PostMapping(path = "/addUser")
    public ResponseEntity<User> addUser(User user) {
        userService.putUser(user);
        User tmpUser = userService.getUser(user.getUserId());
        return new ResponseEntity<>(tmpUser, HttpStatus.OK);
    }

    @Operation(description = "[테스트] userId로 사용자 이름 수정")
    @PutMapping(path = "/modifyUserName")
    public ResponseEntity<User> modifyUser(User user) {
        userService.modifyUser(user);
        User tmpUser = userService.getUser(user.getUserId());
        return new ResponseEntity<>(tmpUser, HttpStatus.OK);
    }
}
