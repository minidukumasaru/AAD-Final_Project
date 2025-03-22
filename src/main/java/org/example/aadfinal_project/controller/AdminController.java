package org.example.aadfinal_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @GetMapping("/checkRole")
    public ResponseEntity<?> checkRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if authentication is null or not authenticated
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(403).body("{\"error\": \"User is not authenticated\"}");
        }

        // Extract all roles and make sure they are in valid JSON format
        List<String> roles = authentication.getAuthorities().stream()
                .map(authority -> "\"" + authority.getAuthority() + "\"")  // Add quotes around roles
                .collect(Collectors.toList());

        return ResponseEntity.ok("{\"roles\": [" + String.join(",", roles) + "]}");
    }


    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> checkAdmin() {
        return ResponseEntity.ok("Admin Access Granted!");
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> checkUser1() {
        return ResponseEntity.ok("User Access Granted! (Test 2)");
    }

    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> checkUser2() {
        return ResponseEntity.ok("User Access Granted! (Test 3)");
    }
}
