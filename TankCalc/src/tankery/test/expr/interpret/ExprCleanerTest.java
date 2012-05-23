package tankery.test.expr.interpret;

import static org.junit.Assert.*;
import tankery.expr.interpret.ExprCleaner;

import org.junit.Test;

public class ExprCleanerTest {

	@Test
	public void testExprCleaner() {
		String srcExpr = "(-5 / (5 % 3 - sqrt(4.0))";
		String dstExpr = "(-5 / (5  3 - (4.0)))";
		ExprCleaner cleaner = new ExprCleaner(srcExpr);
		assertEquals(dstExpr, cleaner.getResult());
	}

}
