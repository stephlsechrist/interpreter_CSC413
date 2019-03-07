/* *********************************************************
Program.java

Modified by: Stephanie Sechrist
Last Edited: March 6, 2019

Added methods:
#getCode(int pc): ByteCode
+getSize(): int   // not needed
+resolveAddrs()
+addCode(ByteCode newCode)

- Program object is controlled by VirtualMachine.java
- contains ArrayList of byte codes(program) that holds all
  of the ByteCode instances initialized in ByteCodeLoader.java
- resolveAddrs takes the labels of any branches in program
  (CALL, FALSEBRANCH, GOTO) and finds the labels to branch to
  - uses a HashMap to load the labels with corresponding address
  - look for label in HashMap and find corresponding address and
    save this data to the instance of the branch
- addCode called by ByteCodeLoader to add an instance of a BC
  to program
********************************************************* */

package interpreter;

import interpreter.bytecode.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;
    private HashMap<String, Integer> labels = new HashMap<String, Integer>();

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public int getSize() {
        return this.program.size();
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param // program Program object that holds a list of ByteCodes
     */
    public void resolveAddrs() {
        // need ArrayList so we can use init to recreate a ByteCode with proper address?

        for (int i = 0; i < program.size(); i++){
            if (program.get(i).getClass().toString().contains("interpreter.bytecode.LabelCode")) {
                LabelCode currBC;// = new LabelCode();
                String currLabel;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (LabelCode) program.get(i);
                currLabel = currBC.getLabel();
                // load HashMap
                labels.put(currLabel, (i));
            }
        }

        for (int j = 0; j < program.size(); j++){
            String currLabel;
            int branchAddr;

            if (program.get(j).toString().contains("FalseBranch")){
                FalseBranchCode currBC;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (FalseBranchCode) program.get(j);
                currLabel = currBC.getLabel();
                branchAddr = (int) labels.get(currLabel);
                currBC.setBranchAddr(branchAddr);
            }

            else if (program.get(j).toString().contains("Goto")){
                GotoCode currBC;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (GotoCode) program.get(j);
                currLabel = currBC.getLabel();
                branchAddr = (int) labels.get(currLabel);
                currBC.setBranchAddr(branchAddr);
            }

            else if (program.get(j).toString().contains("Call")){
                CallCode currBC;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (CallCode) program.get(j);
                currLabel = currBC.getLabel();
                branchAddr = (int) labels.get(currLabel);
                currBC.setBranchAddr(branchAddr);
            }
        }
    }

    public void addCode(ByteCode newCode) {
        this.program.add(newCode);
    }


}
