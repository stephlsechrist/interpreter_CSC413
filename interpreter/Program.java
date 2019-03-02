package interpreter;

import interpreter.bytecode.*;
//import interpreter.bytecode.LabelCode;

//import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Program {

    private ArrayList<ByteCode> program;
    private int pc;
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
        System.out.println("Entered resolveAddr");
        for (int i = 0; i < program.size(); i++){
            System.out.println(program.get(i) + " " + program.size());
        }
        System.out.println("");

        for (int i = 0; i < program.size(); i++){
//            int posToClip = program.get(i).toString().indexOf("@");
//            String bc = program.get(i).toString().substring(21,posToClip);
            if (program.get(i).getClass().toString().contains("interpreter.bytecode.LabelCode")) {
                System.out.println(program.get(i).getClass());
                LabelCode currBC;// = new LabelCode();
                String currLabel;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (LabelCode) program.get(i);
                currLabel = currBC.getLabel();
                System.out.println((i+1) + ": " + currLabel);
                System.out.println(i);
                // load HashMap
                labels.put(currLabel, (i+1));
                System.out.println("Added to HashMap: " + labels.get(currLabel) + "\n");
            }
        }

        for (int j = 0; j < program.size(); j++){
            String currLabel;
            int branchAddr;

            if (program.get(j).toString().contains("FalseBranch")){
                System.out.print((j+1) + " FalseBranch ");
                FalseBranchCode currBC;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (FalseBranchCode) program.get(j);
                currLabel = currBC.getLabel();
                System.out.print(currLabel + " ");
                branchAddr = (int) labels.get(currLabel);
                currBC.setBranchAddr(branchAddr);
                System.out.println(branchAddr + " " + currBC.getBranchAddr());
            }

            else if (program.get(j).toString().contains("Goto")){
                System.out.print((j+1) + " Goto ");
                GotoCode currBC;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (GotoCode) program.get(j);
                currLabel = currBC.getLabel();
                System.out.print(currLabel + " ");
                branchAddr = (int) labels.get(currLabel);
                currBC.setBranchAddr(branchAddr);
                System.out.println(branchAddr + " " + currBC.getBranchAddr());
            }

            else if (program.get(j).toString().contains("Call")){
                System.out.print((j+1) + " Call ");
                CallCode currBC;
                // cast abstract ByteCode to more specific LabelCode
                currBC = (CallCode) program.get(j);
                currLabel = currBC.getLabel();
                System.out.print(currLabel + " ");
                branchAddr = (int) labels.get(currLabel);
                currBC.setBranchAddr(branchAddr);
                System.out.println(branchAddr + " " + currBC.getBranchAddr());
            }
        }

        System.out.println("resolveAddr: finished");
    }

    public void addCode(ByteCode newCode) {
        this.program.add(newCode);
    }


}
