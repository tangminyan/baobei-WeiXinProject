package weixin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import weixin.demo.service.WeixinService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
    public static final String tooken = "baobei";

    @Autowired
    WeixinService weixinService;

    /**
     *  doGet方法测试连接
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/servlet/connect", method = RequestMethod.POST)
    public void weixinConnect(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        WeiXinServlet weiXinServlet = new WeiXinServlet();
//        weiXinServlet.doGet(tooken, request, response);
//        weiXinServlet.doPost(request, response);
//        weixinService.getConnect(tooken, request, response);
//        weixinService.replayContent(request, response);
        weixinService.autoAnswer(request, response);
    }

    /**
     *  doPost 回复基本信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/servlet/replay")
    public void weixinReplay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        weixinService.replayContent(request, response);
    }

    /**
     * 被关注回复与关键词回复
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/servlet/autoanswer")
    public void weixinAutoAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        weixinService.autoAnswer(request, response);
    }
}
