package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j // lombok이 제공하는 애노테이션
public class LogTestController {

    // 아래 코드 대신 @Slf4j를 적어주면 자동으로 아래 코드 만들어준다.
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);
//    private final Logger log = LoggerFactory.getLogger(getClass()); // 현재 나의 클래스 지정, 위 코드와 같다.

    @RequestMapping("/log-test")
    private String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // 로그를 찍을 떄 레벨을 정할 수 있다.
        log.trace(" trace log = {} ", name);
        log.debug(" debug log = {} ", name); // 개발 서버에서 보는 거다.
        // log.info 현재로그는 중요한 정보야, 비즈니스 정보거나 운영시스템에서 봐야할 정보야
        log.info(" info log = {} ", name);
        log.warn(" warn log = {} ", name); // 경고, 위험한 거야
        log.error(" error log = {} ", name); // 에러, 빨리 확인해야해

        return "ok";
    }
}
