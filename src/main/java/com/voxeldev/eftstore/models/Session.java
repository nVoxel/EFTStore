package com.voxeldev.eftstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Session {
    private int userId;
    private String sessionKey;
}
