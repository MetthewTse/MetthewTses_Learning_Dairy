package designMode.strusts.bridge.service.impl;

import designMode.strusts.bridge.service.AbstractCoke;

public class BigCoke extends AbstractCoke {
    @Override
    public void distributeCoke() {
        System.out.print("Big Coke");
        CokeImpl coke = this.getCokeImpl();
        coke.distributeCokeImpl();
    }
}
