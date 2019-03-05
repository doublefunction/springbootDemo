package com.springbootdemo.error;

public enum EmBusinessError implements CommonError{
    //通用错误类型00001
    PARAMETER_validation_error(00001,"参数不合法"),

    //用户错误信息相关的定义
    USER_NOT_EXIST(10001,"用户不存在"),

    //未知错误
    UNKNOWN_ERROR(10002,"未知错误")
    ;

    private  EmBusinessError(int errCode,String errMsg) {
        this.ErrCode=errCode;
        this.errMsg=errMsg;
    }

    private  int ErrCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.ErrCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
         this.errMsg=errMsg;
         return this;
    }
}
