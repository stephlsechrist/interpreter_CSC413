package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class CallCode extends ByteCode {
    private String label;
    private int branchAddr;

    @Override
    public void init(ArrayList<String> arguments){
        label = arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm){
    }

    public String getLabel(){
        return this.label;
    }

    public void setBranchAddr(int addr){
        this.branchAddr = addr;
    }

    public int getBranchAddr(){
        return this.branchAddr;
    }
}
