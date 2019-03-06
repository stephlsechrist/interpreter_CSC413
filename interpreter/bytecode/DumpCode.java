package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class DumpCode extends ByteCode {
    private String dumpState;

    @Override
    public void init(ArrayList<String> arguments){
        dumpState = arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm){
        if (dumpState.compareToIgnoreCase("ON") == 0){
            vm.setDumpState(true);
        }

        else
            vm.setDumpState(false);
    }

//    @Override
//    public String printBC(){
//        return ("");
//    }
}
