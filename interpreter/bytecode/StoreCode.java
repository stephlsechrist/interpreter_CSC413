/* ************************************************
StoreCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
perform store RTS as described in runTimeStack.java
if source code included variable, this is saved in
instance of StoreCode
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private int value;
    private String variable = "";

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

    @Override
    public String printBC(){
        return ("STORE " + value + " " + variable);
    }
}
