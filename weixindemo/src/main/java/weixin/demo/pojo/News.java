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
    private String title;
    private String description;
    private String picUrl;
    private String url;
}
