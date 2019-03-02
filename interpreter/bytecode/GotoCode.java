package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class GotoCode extends ByteCode {
    private String label;

    @Override
    public void init(ArrayList<String> arguments){
    }

    @Override
    public void execute(VirtualMachine vm){
    }

    public String getLabel(){
        return this.label;
    }
}
