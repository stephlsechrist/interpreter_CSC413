package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private int break;

    @Override
    public void init(ArrayList<String> arguments){
        break = (int) arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm){
    }
}
