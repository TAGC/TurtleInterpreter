package testsuite.internal;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;


public class CatchExitRule implements MethodRule {

    @Override
    public Statement apply(final Statement base, FrameworkMethod method,
            Object arg) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                SecurityManager previous = System.getSecurityManager();
                CatchExitManager next = new CatchExitManager(previous);

                try {
                    System.setSecurityManager(next);
                    base.evaluate();
                } finally {
                    System.setSecurityManager(previous);
                }
            }
        };
    }

}
