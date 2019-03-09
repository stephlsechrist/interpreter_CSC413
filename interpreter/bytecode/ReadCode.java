/* ************************************************
ReadCode.java

Created by: Stephanie Sechrist
Last Edited: March 9, 2019

Asks the user to input an integer and does input
checking. Keeps asking if user does not enter an integer.
Then, it requests the VM to push the value to the RTS
************************************************* */
package interpreter.bytecode;

import java.util.InputMismatchException;
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

        System.out.print("Enter an integer: ");
        do {
            try {
                input = stdin.nextInt();
                badInput = false;
            } catch (InputMismatchException error) {
                badInput = true;
                System.out.println("Not an integer. Please try again.");
                System.out.print("Enter an integer: ");
                stdin.nextLine();
            }
        } while (badInput);
        vm.pushRunStack(input);
    }

    @Override
    public String printBC(){
        return ("READ");
    }
}
