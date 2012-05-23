package tankery.test.expr.interpret;

import static org.junit.Assert.*;

import org.junit.Test;

import tankery.expr.interpret.ExprBetter;
import tankery.expr.interpret.ExprNomalizer;

public class ExprNomalizerTest {

	@Test
	public void testExprNomalizer() {
		final String srcExpr[] = {
				"1+2",
				"1 +4/ 2",
				"1 +4/ (1+1)",
				"1 +4/ 2 /1 + 3",
				"-5  /((-5)-(4.0))--1",
				};
		final String dstExpr[] = {
				"1 + 2",
				"1 + 4 / 2",
				"1 + 4 / (1 + 1)",
				"1 + 4 / 2 / 1 + 3",
				"(-5) / ((-5) - 4.0) - (-1)",
				};
		
		for (int i = 0; i < srcExpr.length; i++) {
			ExprBetter cleaner = new FakeExprCleaner(srcExpr[i]);
			ExprNomalizer nomalizer = new ExprNomalizer(cleaner);
			assertEquals(dstExpr[i], nomalizer.getResult());
		}
	}

}
