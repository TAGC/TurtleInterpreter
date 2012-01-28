package testsuite.internal;

import java.security.Permission;

public class CatchExitManager extends SecurityManager {

    private final SecurityManager previous;

    public CatchExitManager(SecurityManager previous) {
        this.previous = previous;
    }

    @Override
    public void checkPermission(Permission perm) {
        if (perm.getName().contains("exitVM")) {
            throw new SecurityException("System.exit called.");
        }
        if (previous != null) {
            previous.checkPermission(perm);
        }
    }

}
