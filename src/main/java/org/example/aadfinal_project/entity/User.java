package org.example.aadfinal_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User implements Serializable{
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID uid;
 @Column(unique = true)
 private String email;
 private String password;
 private String name;
 private String role;

}
