package weixin.demo.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by tangminyan on 2019/3/4.
 */
@Getter
@Setter
public class AccessToken {

    private String token;

    private int expiresIn;
}
