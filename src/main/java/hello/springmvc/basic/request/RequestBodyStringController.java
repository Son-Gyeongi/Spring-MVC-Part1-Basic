package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    // 데이터가 넘어와야 하니깐 Get이 아닌 Post를 쓴다.
    // Get에도 body에 데이터 넣을 수 있다. 최근 스펙에서는 그런걸 다 허용하게 되어있다.
    // 그런데 실무에서 그렇게 잘 안 쓴다.
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        // String은 바이트 코드이다.
        // 항상 바이트 코드를 문자로 받을 때는 어떤 인코딩으로 해서 문자를 바꿀건지 항상 지정해줘야 한다. (StandardCharsets.UTF_8)
        // 지정 안하면 default 값을 쓴다. OS에 기본으로 설정된 값이라던가 자바 실행할 때 기본으로 뜬거라던가
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    // InputStream, OutputStream, Reader
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    // 이제부터 진짜가 나온다.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        // HTTP 컨텐트에 있는 http 메시지에 있는 body를 꺼내는 거다.
        // 변환된 바디를 꺼낼 수 있다.
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }
    // HttpEntity 를 상속받은 다음 객체들도 같은 기능을 제공한다. : RequestEntity, ResponseEntity
//    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
//
//        // HTTP 컨텐트에 있는 http 메시지에 있는 body를 꺼내는 거다.
//        // 변환된 바디를 꺼낼 수 있다.
//        String messageBody = httpEntity.getBody();
//        log.info("messageBody={}", messageBody);
//
//        return new ResponseEntity<>("ok", HttpStatus.CREATED);
//    }
}
