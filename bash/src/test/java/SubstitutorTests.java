
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.ast.concrete.token.AbstractToken;
import org.example.ast.concrete.token.EnvVariableToken;
import org.example.ast.concrete.token.StringToken;
import org.example.execution.context.Context;
import org.example.interfaces.ISubstitutor;
import org.example.substitution.Substitutor;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SubstitutorTests {

    void checkRightSubstitution(List<AbstractToken> expected, List<AbstractToken> actual) {
        assertEquals(expected.size(), actual.size());
        Iterator<AbstractToken> i = expected.iterator(), j = actual.iterator();
        while (i.hasNext()) {
            AbstractToken a = i.next();
            AbstractToken b = j.next();
            if (a instanceof EnvVariableToken && b instanceof EnvVariableToken) {
                assertEquals(((EnvVariableToken) a).getVariableName(), ((EnvVariableToken) b).getVariableName());
                assertEquals(((EnvVariableToken) a).getValue(), ((EnvVariableToken) b).getValue());
            } else if (a instanceof StringToken && b instanceof  StringToken) {
                assertEquals(((StringToken) a).getValue(), ((StringToken) b).getValue());
            } else {
                assertEquals(1, 0,"Different types");
            }
        }
    }

    @Test
    void substitutionWithSingleAndDoubleQuotes() {

        ISubstitutor s = new Substitutor();
        Context context = new Context();

        checkRightSubstitution(List.of(
                new EnvVariableToken("LOL", "123"),
                new EnvVariableToken("KEK", "ke k'11 23'2'3'"),
                new StringToken("echo"),
                new StringToken("5"),
                new StringToken("123'222'222"),
                new StringToken("1232"),
                new StringToken("1\"1"),
                new StringToken("22")
                ), s.resolve(new UnresolvedCommandExpression(
                        "LOL=123 KEK=\"ke k'1\"\"1 23'2'3'\" echo 5 \"123'222'222\" '1232' '1\"1' '2''2'"
        ), context).getTokens());

        checkRightSubstitution(List.of(
                new EnvVariableToken("LOL", "123"),
                new EnvVariableToken("KEK", "ke k'11 23'2'3'")
        ), s.resolve(new UnresolvedCommandExpression(
                "LOL=123 KEK=\"ke k'1\"\"1 23'2'3'\""
        ), context).getTokens());

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