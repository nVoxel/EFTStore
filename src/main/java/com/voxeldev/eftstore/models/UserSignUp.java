package com.voxeldev.eftstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSignUp {
    private String email;
    private String nickname;
    private String password;
}
