package goingmerry.cent.jwt;

import goingmerry.cent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static io.jsonwebtoken.Jwts.parser;

@Component
@RequiredArgsConstructor
public class JwtTokenResolver {

    @Value("${jwt.secret}")
    private String secretKey;

    //Header 에서 AccessToken 꺼내기
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("token");
    }

    //AccessToken 에서 userIdx 꺼내기
    public Long getId(String token) {
        Integer integerId = (Integer) parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().get("id");
        return integerId.longValue();
    }

    //AccessToken loginId 꺼내기
    public String getLoginId(String token) {
        return (String) parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().get("loginId");
    }

    //AccessToken Role 꺼내기
    public String getUserRole(String token) {
        return (String) parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().get("role");
    }

    //AccessToken Restricted 꺼내기
    public boolean getUserIsRestricted(String token) {
        return (boolean) parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().get("restricted");
    }
}
