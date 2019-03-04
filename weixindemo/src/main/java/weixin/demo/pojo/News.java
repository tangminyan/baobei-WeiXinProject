package weixin.demo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by tangminyan on 2019/3/4.
 */

@NoArgsConstructor
@Getter
@Setter
public class News {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
}
