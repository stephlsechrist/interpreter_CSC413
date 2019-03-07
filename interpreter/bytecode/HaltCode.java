/* ************************************************
HaltCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
change boolean isRunning in VM to false so that
execute function in VM stops running
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class HaltCode extends ByteCode {
    @Override
    public void init(ArrayList<String> arguments){
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.setIsRunning(false);
    }

    @Override
    public String printBC(){
        return ("HALT");
    }
}
