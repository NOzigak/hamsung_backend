package hamsung.hamsung_project.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hamsung.hamsung_project.config.auth.CustomUserDetails;
import hamsung.hamsung_project.entity.RefreshToken;
import hamsung.hamsung_project.repository.RefreshRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.*;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    // JSON 파싱을 위해 사용
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshRepository refreshRepository) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String email = null;
        String password = null;


        // JSON 요청 처리
        if (request.getContentType().equals("application/json")) {
            try {
                System.out.println("=====json ======");
                // JSON 요청을 Map으로 파싱
                Map<String, String> requestMap = objectMapper.readValue(request.getInputStream(), Map.class);
                System.out.println("requestMap :" + requestMap);
                // userInfo 키의 값을 추출하고 타입 캐스팅
//                Map<String, String> userInfo = (Map<String, String>) requestMap.get("userInfo");
//                System.out.println("userInfo :" + userInfo);
                // email과 password 값을 추출
                email = requestMap.get("email");
                password = requestMap.get("password");
                System.out.println(email);
                System.out.println(password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 요청이 form-data 형식인경우 obtain으로 바로 꺼낼 수 있다.
            System.out.println("=====form-data======");
            email = obtainUsername(request);
            password = obtainPassword(request);
        }


        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request
            , HttpServletResponse response, FilterChain chain, Authentication authentication) {

        //유저 정보
        String username = authentication.getName();

        //내가 커스텀한 UserDetails에서 user_id 꺼내오기
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        //토큰 생성
        String access = jwtUtil.createJwt(userId,"access", username, role, 1000 * 60 * 10L); // (1초 * 60) * 10 = 10분
        String refresh = jwtUtil.createJwt(userId,"refresh", username, role, 86400000L);

        //Refresh 토큰 저장
        addRefreshEntity(userId ,username, refresh, 86400000L);

        //응답 설정
        response.setHeader("access", access);
        response.addCookie(jwtUtil.createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);
    }

    private void addRefreshEntity(Long userId,String username, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshToken refreshEntity = new RefreshToken();
        refreshEntity.setUser_id(userId);
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date);

        refreshRepository.save(refreshEntity);
    }

//    private Cookie createCookie(String key, String value) {
//
//        Cookie cookie = new Cookie(key, value);
//        cookie.setMaxAge(24*60*60);
//        //cookie.setSecure(true);
//        //cookie.setPath("/");
//        cookie.setHttpOnly(true);
//
//        return cookie;
//    }
}
