package org.example;

import org.example.ast.base.AbstractExpression;
import org.example.execution.Executor;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;
import org.example.interfaces.IParser;
import org.example.parsing.Parser;
import org.example.parsing.exception.ParseException;

import java.util.Scanner;

public class Interpreter {

    public static void main(String[] args) {

        IParser parser = new Parser();
        IExecutor executor = new Executor();
        Context context = new Context();

        Scanner scanner = new Scanner(System.in);
        System.out.print("bashik > ");

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.trim().equals("exit")) {
                break;
            }

            try {
                AbstractExpression expression = parser.parse(str);
                expression.run(executor, context);
            } catch (ParseException e) {
                System.out.println("\tbash syntax: " + e.getMessage());
            } catch (ExecutionException e) {
                System.out.println("\tbash exec: " + e.getMessage());
            } catch (UnsupportedOperationException e) {
                System.out.println("\tbash experimental: feature not supported");
            }

            System.out.print("bashik > ");

        }
    }
}