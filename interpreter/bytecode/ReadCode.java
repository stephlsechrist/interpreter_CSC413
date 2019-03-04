package interpreter.bytecode;

import java.util.Scanner;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReadCode extends ByteCode {
    private int input;
    Scanner stdin = new Scanner(System.in);

    @Override
    public void init(ArrayList<String> arguments) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        // should appear before BC printed with DUMP on
        boolean badInput = false;

        do {
            System.out.print("Enter an integer: ");
            try {
                input = stdin.nextInt();
                badInput = false;
            } catch (NumberFormatException error) {
                badInput = true;
                System.out.println("Improper input.");
                System.out.print("Enter an integer: ");
            }
        } while (badInput);

        vm.pushRunStack(input);
    }
}
