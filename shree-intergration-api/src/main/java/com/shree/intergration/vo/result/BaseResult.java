package com.shree.intergration.vo.result;

import lombok.Data;

@Data
public class BaseResult {

    public static final int FAILED = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = -1;

    protected int status;

    protected String message;
}
