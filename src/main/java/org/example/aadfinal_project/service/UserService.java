package org.example.aadfinal_project.service;


import org.example.aadfinal_project.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    int saveUser(UserDTO userDTO);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserDTO searchUser(String username);
}