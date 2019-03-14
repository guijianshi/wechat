package cn.qiuzhizhushou.wechat.util;

import java.util.*;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/14
 * Time: 下午2:45
 */
public class TokenParam
{
    // 签名由谁生成
    private String issuer = "service";

    // 主题
    private String subject = "微信小程序";

    // 受众
    private String audience = "wechat";

    // 生成时间
    private Date issuedAt = new Date();

    // 过期时间
    private Date expiresAt;

    private Map<String, Object> claims;

    public TokenParam()
    {
        claims = new HashMap<>();
    }

    public String getIssuer()
    {
        return issuer;
    }

    public TokenParam setIssuer(String issuer)
    {
        this.issuer = issuer;
        return this;
    }

    public String getSubject()
    {
        return subject;
    }

    public TokenParam setSubject(String subject)
    {
        this.subject = subject;
        return this;
    }

    public String getAudience()
    {
        return audience;
    }

    public TokenParam setAudience(String audience)
    {
        this.audience = audience;
        return this;
    }

    public Date getIssuedAt()
    {
        return issuedAt;
    }

    public TokenParam setIssuedAt(Date issuedAt)
    {
        this.issuedAt = issuedAt;
        return this;

    }

    public Date getExpiresAt()
    {
        return expiresAt;
    }

    public TokenParam setExpiresAt(Date expiresAt)
    {
        this.expiresAt = expiresAt;
        return this;
    }

    public Map<String, Object> getClaims()
    {
        return claims;
    }

    public TokenParam setClaims(Map<String, Object> claims)
    {
        this.claims = claims;
        return this;
    }

    public TokenParam addClaim(String key, Object claim)
    {
        this.claims.put(key, claim);
        return this;
    }

    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second)
    {
        return getDate(date, year, month, day, hour, minute, second);
    }

    static Date getDate(Date date, int year, int month, int day, int hour, int minute, int second)
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
