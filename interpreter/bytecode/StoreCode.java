package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private int value;
    private String variable;

    @Override
    public void init(ArrayList<String> arguments){
        if (!arguments.isEmpty()) {
            value = Integer.parseInt(arguments.get(0));
            if (arguments.size() > 1){
                variable = arguments.get(1);
            }
        }
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.storeRunStack(value);
    }
}
