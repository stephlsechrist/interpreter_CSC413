package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class LabelCode extends ByteCode {
    private String label;
//    private int pc;

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

//    @Override
//    public String toString(){
//        return ("LABEL " + label);
//    }
}
