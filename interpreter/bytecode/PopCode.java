package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class PopCode extends ByteCode {
    private int value;

    @Override
    public void init(ArrayList<String> arguments){
        value = Integer.parseInt(arguments.get(0));
    }

    @Override
    public void execute(VirtualMachine vm){
        for (int i = 1; i <= value; i++){
            vm.popRunStack();
        }
    }

    @Override
    public String printBC(){
        return ("POP " + value);
    }
}
