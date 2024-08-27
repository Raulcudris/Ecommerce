package com.makiia.userservice.service;

import com.makiia.userservice.dto.EntityUsersDto;
import com.makiia.userservice.dto.NewUserDto;
import com.makiia.userservice.dto.RequestDto;
import com.makiia.userservice.dto.TokenDto;
import com.makiia.userservice.entity.EntityUsers;
import com.makiia.userservice.repository.EntityUsersRepository;
import com.makiia.userservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntityUsersService {
    @Autowired
    EntityUsersRepository entityUsersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    public EntityUsers save(NewUserDto dto) {
        Optional<EntityUsers> user = entityUsersRepository.findByUserName(dto.getUsername());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());

        EntityUsers entityUsers = EntityUsers.builder()
                .username(dto.getUsername())
                .password(password)
                .role(dto.getRole())
                .build();
        return entityUsersRepository.save(entityUsers);
    }

    public TokenDto login(EntityUsersDto dto) {
        Optional<EntityUsers> user = entityUsersRepository.findByUserName(dto.getUsername());
        if(!user.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token, RequestDto dto) {
        if(!jwtProvider.validate(token, dto))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!entityUsersRepository.findByUserName(username).isPresent())
            return null;
        return new TokenDto(token);
    }
}
