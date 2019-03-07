/* ************************************************
ReturnCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
- Pop the top of the returnAddrs in VM and set pc
  to return address.
- Pop the top frame
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    private String label = "";
    private int newAddr;

    @Override
    public void init(ArrayList<String> arguments){
        if (!arguments.isEmpty()){
            label = arguments.get(0);
        }
    }

    @Override
    public void execute(VirtualMachine vm){
        newAddr = vm.popReturnAddrs();
        vm.setPC(newAddr);
        vm.popFrameRunStack();
    }


    @Override
    public String printBC(){
        return ("RETURN " + label);
    }
}
