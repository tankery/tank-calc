package tankery.test.expr.interpret;

import static org.junit.Assert.*;

import org.junit.Test;

import tankery.expr.interpret.ExprBetter;
import tankery.expr.interpret.ExprNomalizer;

public class ExprNomalizerTest {

	@Test
	public void testExprNomalizer() {
		String srcExpr = "-5  /(5-(4.0))";
		String dstExpr = "((-5) / (5 - 4.0))";
		ExprBetter cleaner = new FakeExprCleaner(srcExpr);
		ExprNomalizer nomalizer = new ExprNomalizer(cleaner);
		assertEquals(dstExpr, nomalizer.getResult());
	}

}
