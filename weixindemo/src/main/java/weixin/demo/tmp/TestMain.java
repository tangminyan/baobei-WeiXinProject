package weixin.demo.tmp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by tangminyan on 2019/3/5.
 */
//TODO redis测试
@Slf4j
@Component
public class TestMain {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 通过id查询，如果查询到则进行缓存
     * @return 查询到的实现类
     */
    @Cacheable(value = "hello")
    public void findOne() {
        String key = "hello";
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            log.info("从缓存中获取了用户！");
        }
        log.info("ended");
    }

    @Test
    public void main() {
        findOne();
    }
}
