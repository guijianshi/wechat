package cn.qiuzhizhushou.wechat.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.util.*;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/13
 * Time: 上午9:33
 */
public class JWTUtil
{
    public String createToken()
    {
        String token = null;
        try {

            String secret = "secret";
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("user", 1);
            map.put("test", 1);
            Date  nowDate = new Date();
            Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);
            token = JWT.create()
                    .withHeader(map)
                    .withIssuer("service") //签名是有谁生成 例如 服务器
                    .withSubject("this is token") //签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience("wechat") //签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return token;
    }


    public String createTokenWithClaim()
    {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);//2小过期
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            token = JWT.create()
                    /*设置头部信息 Header*/
                    .withHeader(map)
                    /*设置 载荷 Payload*/
                    .withClaim("loginName", "lijunkui")
                    .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
                    .withSubject("this is test token")//签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience("APP")//签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate) //生成签名的时间
                    .withExpiresAt(expireDate)//签名过期的时间
                    /*签名 Signature */
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return token;
    }

    private Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second)
    {
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}
