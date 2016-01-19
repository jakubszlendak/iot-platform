package com.jmssolutions.iot.services;

import com.jmssolutions.iot.dao.UserDAO;
import com.jmssolutions.iot.domain.Role;
import com.jmssolutions.iot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jakub on 19.01.16.
 */
@Service
@Transactional
public class UserAuthenticationDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(s);
        if(user == null)
            throw new UsernameNotFoundException("No user found with username "+s);

        boolean enabled = true;
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNotLocked = true;

        return new org.springframework.security.core.userdetails.User(  user.getUsername(),
                                                                        user.getPassword().toLowerCase(),
                                                                        enabled,
                                                                        accountNotExpired,
                                                                        credentialsNotExpired,
                                                                        accountNotLocked,
                                                                        getAuthorities(user.getRoles()));
    }

   private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
       List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       for(Role role : roles){
           authorities.add(new SimpleGrantedAuthority(role.getRole()));
       }
       return authorities;
   }
}
