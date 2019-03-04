package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class LitCode extends ByteCode {
    private int value;
    private String variable;

    @Override
    public void init(ArrayList<String> arguments){
        value = Integer.parseInt(arguments.get(0));
        variable = arguments.get(1);
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.pushRunStack(value);
    }

//    public String getVariable(){
//        return this.variable;
//    }
}
