package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    // String으로 반환
//    @ResponseBody를 쓰면 뷰로 가지않고 문자로 반환된다. 문자가 그대로 HTTP응답 코드로 나간다.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello"; // 뷰의 논리적이름이된다.
    }

    // 권장하지 않음 - 불명확함
    /**
     * 경로 /response/hello 이름이랑 hello.html경로(response/hello)랑 같다.
     * 컨트롤러 경로 이름과 뷰의 논리적 이름이 같고 아무것도 반환하지 않으면
     * response/hello 이게 논리적 뷰 이름으로 진행이 된다.
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
