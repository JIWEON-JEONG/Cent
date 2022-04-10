package goingmerry.cent.oauth;

//import goingmerry.cent.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

//    private final JwtTokenProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 전달받은 인증정보 SecurityContextHolder에 저장
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("aaaa");
        response.sendRedirect("/"); //로그인창으로 이동

    }

}

