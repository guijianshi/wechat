package cn.qiuzhizhushou.wechat.error;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/4
 * Time: 上午10:37
 */
public interface CommonError
{
    public int getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);
}
