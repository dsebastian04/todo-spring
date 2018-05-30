package com.project.todo.tasks.configuration;

import com.project.todo.tasks.Exception.MyUserAuthNotFound;
import com.project.todo.tasks.document.UserAuth;
import com.project.todo.tasks.repository.UserAuthRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {

    private UserAuthRepository repository;

    public MyUserDetailService(UserAuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAuth one = repository.findOne(username);
        if (one == null) {
            throw new MyUserAuthNotFound();
        }

        if(username.equals(one.getUsername())) {
            List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.createAuthorityList(username);
            return new User(username,one.getPassword(),grantedAuthorityList);
        }
        else{
            throw new UsernameNotFoundException("username was not found "+username);
        }
    }
}
