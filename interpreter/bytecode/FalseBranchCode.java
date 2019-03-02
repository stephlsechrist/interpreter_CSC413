package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private String label;

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
}
