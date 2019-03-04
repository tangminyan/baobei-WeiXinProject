package weixin.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface WeixinService {
    void getConnect(String tooken, HttpServletRequest request, HttpServletResponse response) throws IOException;

    void replayContent(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void autoAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
