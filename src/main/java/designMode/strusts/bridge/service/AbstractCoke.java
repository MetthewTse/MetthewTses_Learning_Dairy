package designMode.strusts.bridge.service;

import designMode.strusts.bridge.service.impl.CokeImpl;
import lombok.Data;

@Data
public abstract class AbstractCoke {
    CokeImpl cokeImpl;

    public abstract void distributeCoke();
}
