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
//        pc = 0;
//        runStack = new RunTimeStack();
//        returnAddrs = new Stack<Integer>();
//        isRunning = true;
//        while (isRunning){
//            ByteCode code = program.getCode(pc);
//            code.execute(this);
//            //runStack.dump(); // used to dump runstack state.
//            pc++;
//        }
    }

    public void setIsRunning(boolean state)
    {
        isRunning = state;
    }

    public void setPC (int pc){
        this.pc = pc;
    }

    public int getPC(){
        return this.pc;
    }

    public void pushReturnAddrs(int newAddrs){
        this.returnAddrs.push(newAddrs);
    }

    public int getReturnAddrs(){
        return (int) this.returnAddrs.pop();
    }

    public int popRunStack(){
        return runStack.pop();
    }

    public int peekRunStack(){
        return runStack.peek();
    }

    public void newFrameRunStack(int offset){
        runStack.newFrameAt(offset);
    }

    public void popFrameRunStack(){
        runStack.popFrame();
    }

    public int storeRunStack(int offset){
        return runStack.store(offset);
    }

    public int loadRunStack(int offset){
        return runStack.load(offset);
    }

    public Integer pushRunStack(Integer val){
        return runStack.push(val);
    }
}
