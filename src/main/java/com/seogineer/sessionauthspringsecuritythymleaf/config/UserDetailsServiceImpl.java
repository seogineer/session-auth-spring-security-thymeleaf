package com.seogineer.sessionauthspringsecuritythymleaf.config;

import com.seogineer.sessionauthspringsecuritythymleaf.user.User;
import com.seogineer.sessionauthspringsecuritythymleaf.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isEmpty()) throw new UsernameNotFoundException("존재하지 않는 email 입니다.");

        log.info("loadUserByUsername member.username = {}", email);

        User user = findUser.get();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
