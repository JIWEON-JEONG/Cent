package goingmerry.cent.jwt;

import goingmerry.cent.exception.AccountException;
import goingmerry.cent.exception.ErrorType;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Date;

@Deprecated
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    //AccessToken 만료날짜 확인
    public boolean validateAccessToken(String accessToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(accessToken);
            return true;
        } catch (SignatureException | MalformedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID", ex);
        } catch (ExpiredJwtException exception) {
            throw new AccountException(ErrorType.TOKEN_IS_NOT_FOUND);
        }
    }

    // 2022/03/26 오늘날짜
    // 2022/03/25 만료날짜
    // 만료되면 401 Error 내려보내기 , 실제 이 서비스를 가져다 쓸때에는 true 로 전달해서 조건문이 발동하도록 해야한다.

    //RefreshToken 만료날짜 확인
    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(refreshToken);
            return true;
        } catch (SignatureException | MalformedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID", ex);
        } catch (ExpiredJwtException exception) {
            throw new AccountException(ErrorType.TOKEN_IS_NOT_FOUND);
        }
    }

    //True면 업데이트 해줘야함
    public boolean isNeedToUpdateRefreshToken(String refreshToken) {
        Date claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(refreshToken).getBody().getExpiration();

        //만료기간이 7일 남았으면 True
        return claims.before(Date.from(Instant.now().minusSeconds(604800)));
    }
}