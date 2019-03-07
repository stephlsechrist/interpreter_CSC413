/* ************************************************
CallCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
- get the current address we're executing (pc) and save
  to VM's returnAddrs stack so we know where to return
- set the pc to the corresponding label address that was
  initialized when this BC was instantiated
- for dumping, we want to display the name of the function
  label we're jumping to, as well as the arguments being passed
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode {
    private String label;
    private int branchAddr;
    private int currAddr;
    private String argumentsPassed;
    private String formattedLabel;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        currAddr = vm.getPC();
        // -1 because want to display LABEL when dumping is turned on
        // otherwise, it will move on to the next pc after label without
        // displaying label
        vm.setPC(branchAddr - 1);
        vm.pushReturnAddrs(currAddr);
        argumentsPassed = vm.peekFrameRunStack();
    }

    public String getLabel() {
        return this.label;
    }

    public void setBranchAddr(int addr) {
        this.branchAddr = addr;
    }

    public int getBranchAddr() {
        return this.branchAddr;
    }

    // had to format dump output
    // removed brackets and added parentheses around arguments being passed
    @Override
    public String printBC() {
        formattedLabel = label;
        try {
            int openBrackets = label.indexOf("<");
            int closeBrackets = label.indexOf(">");
            if (openBrackets > -1 && closeBrackets > -1) {
                formattedLabel = formattedLabel.substring(0, openBrackets);
                formattedLabel = formattedLabel.substring(0, closeBrackets-1);
            }
        } catch (Exception e) {}
        return ("CALL " + formattedLabel + " " + formattedLabel + "(" + argumentsPassed + ")");
    }
}
