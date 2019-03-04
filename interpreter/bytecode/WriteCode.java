package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class WriteCode extends ByteCode {
    @Override
    public void init(ArrayList<String> arguments){
    }

    @Override
    public void execute(VirtualMachine vm){
        // should appear before BC during dumping
        System.out.println(vm.peekRunStack());
    }

    // from lecture 3/4
//    @Override
//    public String toString(){
//        return "WRITE ";
//    }
}
