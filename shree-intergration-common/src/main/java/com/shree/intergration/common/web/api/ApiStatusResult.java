package com.shree.intergration.common.web.api;


import com.shree.intergration.common.web.rest.RestResult;

public class ApiStatusResult {

    public static final RestResult OK = RestResult.createSuccessResult(ApiStatus.OK.getCode(), null, ApiStatus.OK.getDescription());
    public static final RestResult NO_CONTENT = RestResult.createSuccessResult(ApiStatus.NO_CONTENT.getCode(), null, ApiStatus.NO_CONTENT.getDescription());

    public static final RestResult INVALID_REQUEST = RestResult.createFailedResult(ApiStatus.INVALID_REQUEST.getCode(), null, ApiStatus.INVALID_REQUEST.getDescription());
    public static final RestResult INVALID_REQUEST_PARAMS = RestResult.createFailedResult(ApiStatus.INVALID_REQUEST_PARAMS.getCode(), null, ApiStatus.INVALID_REQUEST_PARAMS.getDescription());
    public static final RestResult INVALID_CLIENT = RestResult.createFailedResult(ApiStatus.INVALID_CLIENT.getCode(), null, ApiStatus.INVALID_CLIENT.getDescription());
    public static final RestResult INVALID_ACCESS_TOKEN = RestResult.createFailedResult(ApiStatus.INVALID_ACCESS_TOKEN.getCode(), null, ApiStatus.INVALID_ACCESS_TOKEN.getDescription());

    public static final RestResult FAILED = RestResult.createFailedResult(ApiStatus.FAILED.getCode(), null, ApiStatus.FAILED.getDescription());

    public static final RestResult UNAUTHORIZED_REQUEST = RestResult.createFailedResult(ApiStatus.UNAUTHORIZED_REQUEST.getCode(), null, ApiStatus.UNAUTHORIZED_REQUEST.getDescription());
    public static final RestResult EXPIRED_CLIENT_GRANT = RestResult.createFailedResult(ApiStatus.EXPIRED_CLIENT_GRANT.getCode(), null, ApiStatus.EXPIRED_CLIENT_GRANT.getDescription());
    public static final RestResult DISABLED_CLIENT = RestResult.createFailedResult(ApiStatus.DISABLED_CLIENT.getCode(), null, ApiStatus.DISABLED_CLIENT.getDescription());

    public static final RestResult ERROR = RestResult.createErrorResult(ApiStatus.ERROR.getCode(), null, ApiStatus.ERROR.getDescription());

}
