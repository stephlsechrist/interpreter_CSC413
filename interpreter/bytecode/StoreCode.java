package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private int value;
    private String variable;

    @Override
    public void init(ArrayList<String> arguments){
    }

    @Override
    public void execute(VirtualMachine vm){
        value = vm.popRunStack();
        vm.storeRunStack(value);
    }
}
