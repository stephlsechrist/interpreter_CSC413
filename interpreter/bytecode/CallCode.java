package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode {
    private String label;
    private int branchAddr;
    private int currAddr;
    private String argumentsPassed;
    private String formattedLabel;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        currAddr = vm.getPC();
        vm.setPC(branchAddr - 1);
        vm.pushReturnAddrs(currAddr);
        argumentsPassed = vm.peekFrameRunStack();
    }

    public String getLabel() {
        return this.label;
    }

    public void setBranchAddr(int addr) {
        this.branchAddr = addr;
    }

    public int getBranchAddr() {
        return this.branchAddr;
    }

    @Override
    public String printBC() {
        formattedLabel = label;
        try {
            int openBrackets = label.indexOf("<");
            int closeBrackets = label.indexOf(">");
            if (openBrackets > -1 && closeBrackets > -1) {
                formattedLabel = formattedLabel.substring(0, openBrackets);
                formattedLabel = formattedLabel.substring(0, closeBrackets-1);
            }
        } catch (Exception e) {}
        return ("CALL " + formattedLabel + " " + formattedLabel + "(" + argumentsPassed + ")");
    }
}
