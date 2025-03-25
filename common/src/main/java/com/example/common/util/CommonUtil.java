package com.example.common.util;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommonUtil {
    
    public String getPortalName() {
        return "스프링 부트 포털";
    }
    
    public String getVersion() {
        return "1.0.0";
    }
    
    /**
     * 네비게이션 바 항목을 반환합니다.
     * @return 네비게이션 바 항목 목록
     */
    public List<Map<String, String>> getNavigationItems(boolean isLoggedIn) {
        List<Map<String, String>> navItems = new ArrayList<>();
        
        // 포털 홈
        Map<String, String> home = new HashMap<>();
        home.put("name", "홈");
        home.put("url", "http://localhost:8087");
        home.put("icon", "fa-home");
        navItems.add(home);
        
        // 모듈 1
        Map<String, String> module1 = new HashMap<>();
        module1.put("name", "모듈 1");
        module1.put("url", "http://localhost:8081/module1/");
        module1.put("icon", "fa-cube");
        navItems.add(module1);
        
        // 모듈 2
        Map<String, String> module2 = new HashMap<>();
        module2.put("name", "모듈 2");
        module2.put("url", "http://localhost:8082/module2/");
        module2.put("icon", "fa-cogs");
        navItems.add(module2);


        if (isLoggedIn) {
            // 게시판
            Map<String, String> board = new HashMap<>();
            board.put("name", "게시판");
            board.put("url", "http://localhost:5433");
            board.put("icon", "fa-comments");
            navItems.add(board);

            // 로그아웃 (자바스크립트 호출)
            Map<String, String> logout = new HashMap<>();
            logout.put("name", "로그아웃");
            logout.put("url", "javascript:void(0);");
            logout.put("icon", "fa-sign-out-alt");
            logout.put("jsFunction", "logout()");
            navItems.add(logout);

        } else {
            // 회원가입
            Map<String, String> signup = new HashMap<>();
            signup.put("name", "회원가입");
            signup.put("url", "http://localhost:5173/");
            signup.put("icon", "fa-user-plus");
            navItems.add(signup);

            // 로그인
            Map<String, String> login = new HashMap<>();
            login.put("name", "로그인");
            login.put("url", "http://localhost:5173/login");
            login.put("icon", "fa-sign-in-alt");
            navItems.add(login);
        }
        
        return navItems;
    }
} 