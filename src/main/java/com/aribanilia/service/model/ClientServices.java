package com.aribanilia.service.model;

import com.aribanilia.service.entity.TblRestCA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServices implements UserDetailsService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblRestCA client = clientDao.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException(username);
        }
        // Put Role
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(client.getAuthorities()));

        User user = new User(client.getUsername(), client.getPassword(), authList);

        return user;
    }
}

