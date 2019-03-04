package cn.qiuzhizhushou.wechat.error;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/4
 * Time: 上午10:39
 */
public enum  EmBusinessError implements CommonError
{
    // 通用错误类型
    PARAMETER_VALIDATION_ERROR(10001, "参数错误"),

    USER_NOT_EXIST_OR_PWD_ERROR(10002, "用户不存在或密码错误"),
    // 系统错误
    SYSTEM_ERROR(50000, "系统错误"),

    ;

    private EmBusinessError(int errCode, String errMsg)
    {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    private int errCode;

    private String errMsg;

    @Override
    public int getErrCode()
    {
        return errCode;
    }

    @Override
    public String getErrMsg()
    {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
        return this;
    }
}

