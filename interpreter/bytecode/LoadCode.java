/* ************************************************
LoadCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
perform load RTS as described in runTimeStack.java
if source code included variable, this is saved in
instance of LoadCode
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class LoadCode extends ByteCode {
    private int value;
    private String variable = "";

    @Override
    public void init(ArrayList<String> arguments){
        value = Integer.parseInt(arguments.get(0));
        if (arguments.size() > 1){
            variable = arguments.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.loadRunStack(value);
    }

    @Override
    public String printBC(){
        return ("LOAD " + value + " " + variable);
    }
}
