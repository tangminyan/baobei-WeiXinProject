package weixin.demo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TextMessage extends BaseMessage{
    private String Content;
    private String MsgId;
}
