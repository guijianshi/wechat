package cn.qiuzhizhushou.wechat.util;


import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/13
 * Time: 上午9:33
 */
@Component
public class JWTUtil
{
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(TokenParam tokenParam) throws BusinessException
    {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder tokenBuilder = JWT.create();
            Map<String, Object> claims = tokenParam.getClaims();
            for (Map.Entry<String, Object> entry : claims.entrySet()) {
                String key = entry.getKey();
                Object claim = entry.getValue();
                if (claim instanceof String) {
                    tokenBuilder = tokenBuilder.withClaim(key, (String) claim);
                } else if (claim instanceof Integer) {
                    tokenBuilder = tokenBuilder.withClaim(key, (Integer) claim);
                } else if (claim instanceof Date) {
                    tokenBuilder = tokenBuilder.withClaim(key, (Date) claim);
                } else if (claim instanceof Boolean) {
                    tokenBuilder = tokenBuilder.withClaim(key, (Boolean) claim);
                } else {
                    tokenBuilder = tokenBuilder.withClaim(key, "格式未定义");
                }
            }
            token = tokenBuilder.withIssuer(tokenParam.getIssuer()) //签名是有谁生成 例如 服务器
                    .withSubject(tokenParam.getSubject()) //签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience(tokenParam.getAudience()) //签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(tokenParam.getIssuedAt())
                    .withExpiresAt(tokenParam.getExpiresAt())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            throw new BusinessException(EmBusinessError.JWT_VERIFY_ERROR);
        }
    }

    public DecodedJWT verifyToken(String token) throws BusinessException
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("service")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
//            return jwt;
//            Map<String, Claim> claims = jwt.getClaims();
//            claims.forEach((key, value) -> {
//                System.out.println(key + value.asString());
//            });
//            TokenParam param = new TokenParam();
//            Map<String, Object> map = new HashMap<>();
//            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
//                param.addClaim(entry.getKey(), entry.getValue().asString());
//            }
//            param.setSubject(jwt.getSubject());
//            return param;
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
            throw new BusinessException(EmBusinessError.JWT_VERIFY_ERROR);
        }
    }
}
