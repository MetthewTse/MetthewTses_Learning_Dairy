package designMode.strusts.bridge;

import designMode.strusts.bridge.service.AbstractCoke;
import designMode.strusts.bridge.service.impl.BigCoke;
import designMode.strusts.bridge.service.impl.NoneIceCokeImpl;

public class BridgeApplication {
    public static void main(String[] args) {
        AbstractCoke coke = new BigCoke();
        coke.setCokeImpl(new NoneIceCokeImpl());
        coke.distributeCoke();
    }
}
