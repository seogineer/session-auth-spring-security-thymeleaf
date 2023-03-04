package com.seogineer.sessionauthspringsecuritythymeleaf.config;

import com.seogineer.sessionauthspringsecuritythymeleaf.member.domain.Member;
import com.seogineer.sessionauthspringsecuritythymeleaf.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        if (findMember.isEmpty()) throw new UsernameNotFoundException("존재하지 않는 username 입니다.");

        log.info("loadUserByUsername member.username = {}", username);

        Member member = findMember.get();
        return new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
    }
}
