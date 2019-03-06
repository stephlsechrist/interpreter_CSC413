package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private String label;
    private int branchAddr;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (vm.popRunStack() == 0) {
            vm.setPC(branchAddr-1);
        }
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
    public String printBC(){
        return ("FALSEBRANCH " + label);
    }
}
