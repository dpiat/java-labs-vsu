import org.junit.Assert;
import org.junit.Test;

public class ParserTests {
    @Test
    public void testSummation() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("2+2"), 4, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("1+2+3"), 6, 0.001);
    }

    @Test
    public void testSubtraction() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("2-2"), 0, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("3-4-1"), -2, 0.001);
    }

    @Test
    public void testMultiplication() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("2*2"), 4, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("1*2*3"), 6, 0.001);
    }

    @Test
    public void testDivision() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("2/2"), 1, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("6/3/2"), 1, 0.001);
    }

    @Test
    public void testExponentiation() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("2^2"), 4, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("4^3"), 64, 0.001);
    }

    @Test
    public void testFunction() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("sqrt(4)"), 2, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("sin(0)"), 0, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("cos(0)"), 1, 0.001);
    }

    @Test
    public void testExpressionWithSubexrpession() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("sqrt(sqrt(16))"), 2, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("sin(sin(0))"), 0, 0.001);
    }

    @Test
    public void someTests() throws Exception {
        Assert.assertEquals(Parser.CalculateExpression("2+4*3+sqrt(9)"), 17, 0.001);
        Assert.assertEquals(Parser.CalculateExpression("(2+1)*4"), 12, 0.001);
    }
}
