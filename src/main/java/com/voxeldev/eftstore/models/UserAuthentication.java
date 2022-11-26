package com.voxeldev.eftstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAuthentication {
    private String email;
    private String password;
}
