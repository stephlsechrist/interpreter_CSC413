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
        switch (operation){
            case "+":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                vm.pushRunStack(op1 + op2);
                break;
            case "-":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                vm.pushRunStack(op1 - op2);
                break;
            case "/":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                vm.pushRunStack(op1 / op2);
                break;
            case "*":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                vm.pushRunStack(op1 * op2);
                break;
            case "==":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if (op2 == op1){
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "!=":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if (op2 == op1) {
                    vm.pushRunStack(0);
                }
                else{
                    vm.pushRunStack(1);
                }
                break;
            case "<=":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if (op1 <= op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case ">":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if (op1 > op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case ">=":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if (op1 >= op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "<":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if (op1 < op2) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "|":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if ((op1 + op2) > 0){
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
            case "&":
                op2 = vm.popRunStack();
                op1 = vm.popRunStack();
                if (((op1 + op2) == 0) || ((op1 + op2) == 2)) {
                    vm.pushRunStack(1);
                }
                else{
                    vm.pushRunStack(0);
                }
                break;
        }
    }
}
