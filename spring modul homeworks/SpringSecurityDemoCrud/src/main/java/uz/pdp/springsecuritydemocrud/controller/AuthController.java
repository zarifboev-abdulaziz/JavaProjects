package uz.pdp.springsecuritydemocrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecuritydemocrud.payload.ApiResponse;
import uz.pdp.springsecuritydemocrud.payload.authPayloads.LoginDto;
import uz.pdp.springsecuritydemocrud.payload.authPayloads.RegisterDto;
import uz.pdp.springsecuritydemocrud.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }


}
