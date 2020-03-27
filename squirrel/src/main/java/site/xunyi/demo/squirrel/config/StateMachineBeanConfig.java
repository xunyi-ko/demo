package site.xunyi.demo.squirrel.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import site.xunyi.demo.squirrel.statemachine.MyStateMachine;
import site.xunyi.demo.squirrel.enums.Event;
import site.xunyi.demo.squirrel.enums.State;

@Configuration
public class StateMachineBeanConfig {
    @Bean(name = "StateMachineBuilder")
    UntypedStateMachineBuilder createStateMachineBuilder(){
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(MyStateMachine.class, ApplicationContext.class);

        // 定时触发事件
        builder.defineTimedState(State.start, 1, 0, Event.run, null);

        // 触发事件，调用方法
        builder.internalTransition().within(State.start).on(Event.run).callMethod("run");

        // 触发事件，调用方法，状态变化
        builder.externalTransition().from(State.start).to(State.end).on(Event.over).callMethod("rest");

        return builder;
    }
}
