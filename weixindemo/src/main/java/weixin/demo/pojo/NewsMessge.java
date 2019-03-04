package weixin.demo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by tangminyan on 2019/3/4.
 */

@NoArgsConstructor
@Setter
@Getter
public class NewsMessge extends BaseMessage {
    private int ArticleCount;
    private List<News> Articles;
}
