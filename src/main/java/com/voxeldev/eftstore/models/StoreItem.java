package com.voxeldev.eftstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StoreItem {
    private int id;
    private String nameRu;
    private String nameEn;
    private String imageName;
    private String imageNameDet;
    private String descriptionRu;
    private String descriptionEn;
    private String wikiLink;
    private int priceRub;
}
