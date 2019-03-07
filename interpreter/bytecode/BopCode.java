/* ************************************************
BopCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
Pop the top two values off the stack and perform
the given operation with the second value popped as the
first operand. Push the result to the stack.
&, |, <, >, <=, >=, ==, != are binary operators
0 is pushed for false, 1 for true
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class BopCode extends ByteCode {
    private String operation;
    private int op1, op2;

    @Override
    public void init(ArrayList<String> arguments){
        try{
            operation = arguments.get(0);
        } catch(Exception error){
            System.out.println("Error in BopCode init");
        }
    }

    @Override
    public void execute(VirtualMachine vm){
        op2 = vm.popRunStack();
        op1 = vm.popRunStack();

        switch (operation){
            case "+":
                vm.pushRunStack(op1 + op2);
                break;
            case "-":
                vm.pushRunStack(op1 - op2);
                break;
            case "/":
                vm.pushRunStack(op1 / op2);
                break;
            case "*":
                vm.pushRunStack(op1 * op2);
                break;
            case "==":
                if (op2 == op1){
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "!=":
                if (op2 == op1) {
                    vm.pushRunStack(0);
                }
                else{
                    vm.pushRunStack(1);
                }
                break;
            case "<=":
                if (op1 <= op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case ">":
                if (op1 > op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case ">=":
                if (op1 >= op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "<":
                if (op1 < op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "|":
                if ((op1 + op2) > 0){
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "&":
                if (((op1 + op2) == 0) || ((op1 + op2) == 2)) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
        }
    }

    @Override
    public String printBC(){
        return ("BOP " + operation);
    }
}
