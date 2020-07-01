package com.shree.intergration.model.custom.serivce;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shree.intergration.common.web.rest.layui.LayPagination;
import com.shree.intergration.model.custom.entity.UserInfoVo;
import com.shree.intergration.model.major.entity.IdpAppResource;
import com.shree.intergration.model.major.entity.IdpUserInfo;
import com.shree.intergration.model.major.entity.IdpUserInfoSearch;

import java.util.List;

public interface UserService {
    Page<UserInfoVo> getPage(LayPagination layPagination, IdpUserInfoSearch search);

    IdpUserInfo webUserLogin(String userName, String userPwd);

    List<IdpAppResource> getPermissionResource(String userName);

    List<String> getPermissionCode(String userName);
}
