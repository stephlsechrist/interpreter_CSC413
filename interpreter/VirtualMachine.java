/* *************************************************
VirtualMachine.java

Modified by: Stephanie Sechrist
Last Edited: March 6, 2019

Constructor provided
executeProgram provided in PDF and slightly modified
   to allow dumping

Virtual Machine has a RTS and byte codes request the
VM to perform operations on its behalf. In order not to
break encapsulation, I've added many functions to this
class that correspond to functions in runTimeStack.java
so that the BC do not touch the RTS

+setIsRunning(boolean state): void
+setDumpState(boolean dumpSwitch): void
+setPC(int pc): void
+getPC(): int
+pushReturnAddrs(int newAddrs): void
+popReturnAddrs(): int
+popRunStack(): int
+peekRunStack(): int
+newFrameRunStack(int offset): void
+popFrameRunStack(): void
+peekFrameRunStack(): String
+storeRunStack(int offset): int
+loadRunStack(int offset): int
+pushRunStack(Integer val): Integer
************************************************* */

package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.RunTimeStack;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack runStack; // our RTS
    private Stack returnAddrs; // contains address to return to when RETURN called
    private Program program; // contains all bytecode instructions
    private int pc; // controls flow of program
    private boolean isRunning; // set false by HALT
    private boolean dumpState; // dump method should be in RunTimeStack

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram() {
        // sample base function below from PDF . added if statement for dump
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        dumpState = false;
        // used to dump runstack state.
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            // don't want to display RTS after DUMP is turned on, so needed
            //   the second half of if statement
            if (dumpState && !code.getClass().toString().contains("Dump")) {
                System.out.println(code.printBC());
                // call dump() from runTimeStack.java
                runStack.dump();
            }
            pc++;
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
        return runStack.peekFrame();
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
