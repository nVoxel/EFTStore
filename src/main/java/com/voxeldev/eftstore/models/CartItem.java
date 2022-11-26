package com.voxeldev.eftstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CartItem {
    private int id;
    private int userId;
    private int itemId;
}
