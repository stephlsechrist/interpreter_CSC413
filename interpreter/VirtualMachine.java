package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.RunTimeStack;

import java.util.ArrayList;
import java.util.EmptyStackException;
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

    public void executeProgram() {
        // sample base function below
//        System.out.println("Starting virtual machine");
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        dumpState = false;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
//            System.out.println("About to execute " + pc + " " + code.getClass());
            code.execute(this);
            if (dumpState && !code.getClass().toString().contains("Dump")) {
//                System.out.println(pc + " " + code.getClass().getSimpleName());
                System.out.println(code.printBC());
                runStack.dump();
            } // used to dump runstack state.
            pc++;

//            System.out.println(runStack.size());
//            for (int i = 0; i < runStack.size(); i++) {
//                System.out.print(runStack.get(i) + " ");
//            }
//            System.out.println();
        }
    }

    public void setIsRunning(boolean state) {
        isRunning = state;
    }

    public void setDumpState(boolean dumpSwitch){ dumpState = dumpSwitch;}

    public void setPC(int pc) {
        this.pc = pc;
    }

    public int getPC() {
        return this.pc;
    }

    public void pushReturnAddrs(int newAddrs) {
        this.returnAddrs.push(newAddrs);
    }

    public int popReturnAddrs() {
        return (int) this.returnAddrs.pop();
    }

    public int popRunStack() {
        return runStack.pop();
    }

    public int peekRunStack() {
        return runStack.peek();
    }

    public void newFrameRunStack(int offset) {
        runStack.newFrameAt(offset);
    }

    public void popFrameRunStack() {
        runStack.popFrame();
    }

    public String peekFrameRunStack(){
        return runStack.peekFrame().toString();
    }

    public int storeRunStack(int offset) {
        return runStack.store(offset);
    }

    public int loadRunStack(int offset) {
        return runStack.load(offset);
    }

    public Integer pushRunStack(Integer val) {
        return runStack.push(val);
    }
}
