/* ************************************************
FalseBranchCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
- pop the top of the RTS
- if it is 0 (false), we branch (change pc)
  to corresponding label address
- if it is 1 (true), do nothing
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private String label;
    private int branchAddr;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (vm.popRunStack() == 0) {
            // -1 because want to display LABEL when dumping is turned on
            // otherwise, it will move on to the next pc after label without
            // displaying label
            vm.setPC(branchAddr-1);
        }
    }

    public String getLabel() {
        return this.label;
    }

    public void setBranchAddr(int addr) {
        this.branchAddr = addr;
    }

    @Override
    public String printBC(){
        return ("FALSEBRANCH " + label);
    }
}
