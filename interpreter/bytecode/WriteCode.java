/* ************************************************
WriteCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
peek the RTS
display result to console
************************************************* */
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

    @Override
    public String printBC(){
        return ("WRITE");
    }
}
