package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.RunTimeStack;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning; // set false by HALT
    private boolean dumpState; // dump method should be in RunTimeStack

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram(){
        // sample base function below
        System.out.println("Starting virtual machine");
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        while (isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            //runStack.dump(); // used to dump runstack state.
            pc++;
        }
    }

    public void setIsRunning(boolean state)
    {
        isRunning = state;
    }

}
