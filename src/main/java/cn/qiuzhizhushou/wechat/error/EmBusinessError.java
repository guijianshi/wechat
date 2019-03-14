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

    QUOTE_NOT_EXIST_ERROR(20002, "名言不存在"),

    USER_EXISTED_ERROR(20003, "用户已存在"),


    JWT_VERIFY_ERROR(400401, "token校验错误"),

    HTTP_GET_ERROR(30001, "请求第三方接口失败"),
    // 30100 系列微信
    WECHAT_LOGIN_ERROR(30101, "微信小程序登入异常"),
    WECHAT_SESSION_FORMAT_ERROR(30102, "微信小程序session格式异常"),
    WECHAT_DATA_ILLEGAL_ERROR(30103, "微信数据非法"),

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

