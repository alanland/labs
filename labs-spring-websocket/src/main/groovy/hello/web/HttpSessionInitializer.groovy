package hello.web

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer
import org.springframework.stereotype.Component

/**
 * @author 王成义
 * @version 7/21/16
 */
@Component
public class HttpSessionInitializer extends AbstractHttpSessionApplicationInitializer {

    public HttpSessionInitializer() {
        super(RedisHttpSessionConfig.class);
    }
}