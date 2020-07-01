package com.shree.intergration.model.custom.entity;

import com.shree.intergration.model.major.entity.IdpAppResource;
import lombok.Data;

@Data
public class AppResource extends IdpAppResource {
    private String parentName;
}