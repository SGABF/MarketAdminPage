package kr.sga.gkmarket.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;

@Controller
public class WebErrorController implements ErrorController {

	@ApiOperation(value = "에러페이지", notes = "에러가 발생하면 404페이지로 이동합니다.")
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/MainView/404";
            } else {
                return "redirect:/MainView/404";
            }
        }

        return "redirect:/MainView/404";
    }
}
