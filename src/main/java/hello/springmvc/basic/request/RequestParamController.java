package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// HTTP 요청 파라미터 (쿼리 파라미터, HTML Form)
// http://localhost:8080/request-param-v1?username=hello&age=20
@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HttpServletRequest가 제공하는 getParameter() 쓰는거다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); // String에서 int로 타입 변환
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }
}
