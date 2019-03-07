/* ************************************************
LitCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
Push a literal value to the top of the RTS
- if the source code included a variable, this is
  saved in instance of LitCode
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {
    private int value;
    private String variable = "";

    @Override
    public void init(ArrayList<String> arguments) {
        value = Integer.parseInt(arguments.get(0));
        if (arguments.size() > 1) {
            variable = arguments.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushRunStack(value);
    }


    @Override
    public String printBC(){
        return ("LIT " + value + " " + variable);
    }
}
