package com.dexter.attendanceSystem.audit;

import com.dexter.attendanceSystem.entity.AppUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AppAuditAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() ||
        authentication instanceof AnonymousAuthenticationToken
        ){
            return Optional.empty();
        }

        AppUser userPrincipal =  (AppUser)  authentication.getPrincipal();
        return  Optional.ofNullable(userPrincipal.getId());

    }


    public Optional<AppUser> getCurrentUserAuditor(){
       Optional <Authentication> authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        if(authentication.isPresent()){
            if(!(authentication.get() instanceof AnonymousAuthenticationToken) && authentication.get().isAuthenticated()){
                AppUser userPrincipal =  (AppUser)  authentication.get().getPrincipal();
                return Optional.of(userPrincipal);

            }else{
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
    }



}
