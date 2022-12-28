package com.divergentsl.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;

public class AuditorAwareImpl  implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        String uname= SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object[] objects = authorities.toArray();
        String authoriy = objects[0].toString();
        uname=uname+" "+authoriy;
        return Optional.of(uname);
    }
}
