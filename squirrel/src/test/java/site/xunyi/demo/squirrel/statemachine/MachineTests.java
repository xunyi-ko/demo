package site.xunyi.demo.squirrel.statemachine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import org.springframework.context.ApplicationContextAware;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import site.xunyi.demo.squirrel.enums.State;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootTest
public class MachineTests implements ApplicationContextAware {
    @Autowired
    @Qualifier("StateMachineBuilder")
    private UntypedStateMachineBuilder builder;

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testStateMachine(){
        UntypedStateMachine stateMachine = builder.newStateMachine(State.start, applicationContext);
        stateMachine.start();
    }
}
