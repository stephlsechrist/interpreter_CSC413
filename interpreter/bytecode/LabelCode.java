/* ************************************************
LabelCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Doesn't do anything but is used by other BC to know
where to jump to. labels are saved in HashMap in
Program.java in resolveAddrs() and looked up there
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class LabelCode extends ByteCode {
    private String label;

    @Override
    public void init(ArrayList<String> arguments){
        label = arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm){
    }

    public String getLabel(){
        return this.label;
    }

    @Override
    public String printBC(){
        return ("LABEL " + label);
    }
}
