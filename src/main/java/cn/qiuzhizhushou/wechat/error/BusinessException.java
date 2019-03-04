package cn.qiuzhizhushou.wechat.error;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/4
 * Time: 上午10:39
 */
/**
 * 包装器业务异常实现
 */
public class BusinessException extends Exception implements CommonError
{
    private CommonError commonError;

    public BusinessException(CommonError commonError)
    {
        super();
        this.commonError = commonError;
    }


    public BusinessException(CommonError commonError, String errMsg)
    {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode()
    {
        return commonError.getErrCode();
    }

    @Override
    public String getErrMsg()
    {
        return commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg)
    {
        commonError.setErrMsg(errMsg);
        return this;
    }
}

