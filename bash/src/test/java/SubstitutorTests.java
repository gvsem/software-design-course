
import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.substitution.Substitutor;
import org.junit.jupiter.api.Test;

public class SubstitutorTests {

    @Test
    void substitutionWithSingleAndDoubleQuotes() {
//        // Currently, no Context support
//        Substitutor subs = new Substitutor();
//        String[][] inputAndOutput = {
//                { "echo 'dude \"cat\"'", "echo 'dude \"cat\"'" },
//                // If we meet single quotes with no content, we eliminate them
//                { "echo ''\"\"''", "echo" },
//                { "echo 'dude'\"dude\"'dude'", "echo 'dude''dude''dude'" },
//
//        };
//        for (String[] expectedIo : inputAndOutput) {
//            UnresolvedCommandExpression expr = new UnresolvedCommandExpression(expectedIo[0]);
//            ResolvedCommandExpression resExpr = subs.resolve(expr, null);
//
//            System.out.println(resExpr.getCommand());
//            assert (resExpr.getCommand().equals(expectedIo[1]));
//        }

    }

}