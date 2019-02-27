package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class HaltCode extends ByteCode {
    @Override
    public ByteCode init(ArrayList<String> arguments){
        return null;
    }

    @Override
    public void execute(VirtualMachine vm){
    }
}
