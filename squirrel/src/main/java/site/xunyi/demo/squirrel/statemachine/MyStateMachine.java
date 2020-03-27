package site.xunyi.demo.squirrel.statemachine;

import org.springframework.context.ApplicationContext;
import org.squirrelframework.foundation.fsm.annotation.AsyncExecute;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;
import site.xunyi.demo.squirrel.enums.Event;
import site.xunyi.demo.squirrel.enums.State;

import java.util.HashMap;
import java.util.Map;

@StateMachineParameters(stateType = State.class, eventType = Event.class, contextType = Map.class)
public class MyStateMachine extends AbstractUntypedStateMachine {
    private ApplicationContext applicationContext;

    public MyStateMachine(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @AsyncExecute
    public void run(State from, State to, Event event, Map data){
        System.out.println("run");

        Map<String, String> map = new HashMap<>();
        map.put("event", "run");
        this.fire(Event.over, map);
    }

    @AsyncExecute
    public void rest(State from, State to, Event event, Map data){
        System.out.println("rest");

        System.out.println(data.get("event"));

        this.terminate();
    }
}
