package xxljobTest;

import com.sun.istack.internal.NotNull;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 定时任务mock对象
 * 用于解决多个单元测试时导致的xxljob重复实例化的问题
 */
@Component
public class XxlJobMockBean {

    /**
     * Get a mocked xxl-job executor for unit-testing。
     * @return a mocked xxl-job executor
     */
    @Bean
    @Primary
    public XxlJobSpringExecutor getMockedXxlJobExecutor() {
        return new MockedXxlJobExecutor();
    }

    /**
     * Mocked xxl job executor。
     */
    private static class MockedXxlJobExecutor
            extends XxlJobSpringExecutor
            implements ApplicationContextAware, SmartInitializingSingleton, DisposableBean {

        @Override
        public void afterSingletonsInstantiated() {
            // do nothing
        }

        @Override
        public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
            // do nothing
        }
    }
}
