package com.school.sport_enrollment.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.sport_enrollment.Model.User;
import com.school.sport_enrollment.Repository.UserRepository;

@Service

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userDetailRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email:" + email));
        return CustomUserDetailImpl.build(user);
    }

}
