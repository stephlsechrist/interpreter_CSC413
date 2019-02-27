package interpreter;

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
    }

}
