package tankery.test.expr;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tankery.test.expr.interpret.AllInterpretTests;
import tankery.test.expr.tree.ExprTest;

@RunWith(Suite.class)
@SuiteClasses({
	AllInterpretTests.class,
	ExprTest.class,
	})
public class AllTests {

}
