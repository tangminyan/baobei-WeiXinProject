package weixin.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by tangminyan on 2019/3/4.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
}
