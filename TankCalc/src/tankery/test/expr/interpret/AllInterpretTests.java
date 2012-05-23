package tankery.test.expr.interpret;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ExprCleanerTest.class,
	ExprNomalizerTest.class,
	ExprInterpreterTest.class,
	})
public class AllInterpretTests {

}
