package org.example.auth.controller;

import org.example.auth.entity.LoginInfo;
import org.example.auth.entity.LoginUserInfo;
import org.example.basis.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("/login")
    public LoginUserInfo login(LoginInfo loginInfo) {
        return new LoginUserInfo();
    }

    @PostMapping("/logout")
    public R logout() {
        return R.setData("aaa") ;
    }
}
