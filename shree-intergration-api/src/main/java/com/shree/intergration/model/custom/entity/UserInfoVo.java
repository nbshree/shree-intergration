package com.shree.intergration.model.custom.entity;

import com.shree.intergration.model.major.entity.IdpUserInfo;
import lombok.Data;

@Data
public class UserInfoVo extends IdpUserInfo {
    private String roleName;
}