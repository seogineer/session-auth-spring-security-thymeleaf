package com.seogineer.sessionauthspringsecuritythymeleaf.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String username, String password, boolean enabled, Role role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
}
