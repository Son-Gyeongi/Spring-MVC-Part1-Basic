package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// HTTP 요청 파라미터 (쿼리 파라미터, HTML Form)
// http://localhost:8080/request-param-v1?username=hello&age=20
@Slf4j
//@RestController
@Controller
public class RequestParamController {

    //@Controller
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HttpServletRequest가 제공하는 getParameter() 쓰는거다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); // String에서 int로 타입 변환
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // "ok"문자를 뷰 조회를 진행하지 않고 HTTP 응답 메세지에 바로 넣어서 반환 가능(@RestController와 비슷)
    @RequestMapping("request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    // HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
    @ResponseBody
    @RequestMapping("request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    // String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
    @ResponseBody
    @RequestMapping("request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }
}
