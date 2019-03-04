package weixin.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import weixin.demo.servlet.WeiXinServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
    public static final String tooken = "baobei";

    @RequestMapping(value = "/servlet/demo", method = RequestMethod.POST)
    public void weixinConnect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WeiXinServlet weiXinServlet = new WeiXinServlet();
//        weiXinServlet.doGet(tooken, request, response);
        weiXinServlet.doPost(request, response);
    }
}
