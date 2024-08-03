package hamsung.hamsung_project.domain.refresh.controller;

import hamsung.hamsung_project.global.config.jwt.JWTUtil;
import hamsung.hamsung_project.domain.refresh.service.RefreshService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    public ReissueController(JWTUtil jwtUtil, RefreshService refreshService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        return refreshService.reissue(request, response);
    }




}