package hello.proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;

public class ConcreteClient {

    private final ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public void execute() {
        concreteLogic.operation();
    }
}
