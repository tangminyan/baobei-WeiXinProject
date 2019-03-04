package weixin.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum MessageType {
    TEST("text", "文本消息"),
    VOICE("voice", "语音消息"),
    IMAGE("image", "图片消息"),
    VIDEO("video", "视频消息"),
    LINK("link", "链接消息"),
    LOCATION("location", "位置消息"),
    SHORTVIDEO("shortvideo", "消息视频消息"),
    ;

    private String type;
    private String value;
}
