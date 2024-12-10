package interaction.cx.masdr.sa.backend.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TokenRequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(TokenRequestUtil.class);
    @Pointcut("execution(* interaction.cx.masdr.sa.backend.util.*.*(..))")
    public void serviceMethods() {}
    @Before("getTenantIdFromToken()")
    public void logBeforeMethodExecution() {
        logger.info("A method in the service package is about to execute....................----------------------..................");
    }
}