package org.example.aadfinal_project.controller;

import org.example.aadfinal_project.dto.AuthDTO;
import org.example.aadfinal_project.dto.ResponseDTO;
import org.example.aadfinal_project.dto.UserDTO;
import org.example.aadfinal_project.service.impl.UserServiceImpl;
import org.example.aadfinal_project.utill.JwtUtil;
import org.example.aadfinal_project.utill.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;

    // Constructor injection
    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserServiceImpl userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (Exception e) {
            responseDTO.setCode(VarList.Unauthorized);
            responseDTO.setMessage("Invalid email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        }

        UserDTO loadedUser = userService.loadUserDetailsByUsername(userDTO.getEmail());
        if (loadedUser == null) {
            responseDTO.setCode(VarList.Unauthorized);
            responseDTO.setMessage("User not found.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            responseDTO.setCode(VarList.Unauthorized);
            responseDTO.setMessage("Token generation failed. Please try again.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        }

        // Create AuthDTO with email and token
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail(loadedUser.getEmail());
        authDTO.setToken(token);

        responseDTO.setCode(VarList.Created);
        responseDTO.setMessage("Login successful.");
        responseDTO.setData(authDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
