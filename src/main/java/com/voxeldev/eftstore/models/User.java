package com.voxeldev.eftstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    public static final String SESSION_ATTRIBUTE_NAME = "user";
    
    private int id;
    private String email;
    private String passwordHash;
    private String nickname;
    private Integer avatarId;
    
    public Optional<Integer> getAvatarId() {
        return Optional.ofNullable(avatarId);
    }
}
