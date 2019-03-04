package weixin.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum EventEnum {
    SUBSCRIBE("subscribe", "关注"),
    UNSUBSCRIBE("unsubscribe", "取消关注"),
    LOCATION("location", "上传地理位置"),
    CLICK("click", "点击菜单获取消息时触发click"),
    VIEW("view", "点击菜单跳转链接时触发view"),
    SCAN("scan", "已关注时触发scan"),
    ;

    private String type;
    private String value;

}
