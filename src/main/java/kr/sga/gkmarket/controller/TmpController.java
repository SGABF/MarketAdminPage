package kr.sga.gkmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TmpController {
	
	@RequestMapping(value = "/hello")
	@ResponseBody
    public String firstPage() {
        return "Hello. you have valid JWT (JSon Web Token)!";
	}
}
