package cn.qiuzhizhushou.wechat.util;

import cn.qiuzhizhushou.wechat.model.User;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/15
 * Time: 下午4:41
 */
public class Token
{
    private static Integer userId;

    public static Integer getUserId()
    {
        return userId;
    }

    public static void setUserId(Integer userId)
    {
        Token.userId = userId;
    }

    /**
     * 一个月有效期token
     *
     * @param user
     * @return
     */
    public static TokenParam createToken(User user)
    {
        TokenParam tokenParam = new TokenParam();
        tokenParam.addClaim("user_id", user.getId())
                .addClaim("nickname", user.getNickname())
                .setExpiresAt(TokenParam.getAfterDate(null, 0, 6, 0, 0, 0, 0));
        return tokenParam;
    }

    /**
     * 是否登入
     *
     * @return
     */
    public static boolean isLogin()
    {
        return null != userId;
    }
}
