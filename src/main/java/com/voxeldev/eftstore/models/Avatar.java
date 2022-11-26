package com.voxeldev.eftstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Avatar {
    private int id;
    private String fileName;
    private String contentType;
    private int size;
    private String originalFileName;
    private int uploaderId;
}
