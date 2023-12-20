package designMode.strusts.bridge.service.impl;

import designMode.strusts.bridge.service.AbstractCoke;

public class SmallCoke extends AbstractCoke {
    @Override
    public void distributeCoke() {
        System.out.println("small coke");
        CokeImpl coke = this.getCokeImpl();
        coke.distributeCokeImpl();
    }
}
