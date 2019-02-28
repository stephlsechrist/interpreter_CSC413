package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;

import java.util.ArrayList;

public class Program {

    private ArrayList<ByteCode> program;
    private ArrayList<Integer> labels;
    private int pc;

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
//        ArrayList<String> labels;
//        int pc;
        for (pc = 0; !(this.program.isEmpty()); pc++){
            if ((this.program.toString().compareToIgnoreCase("label")) == 0){
                labels.add(pc);
            }
        }
    }

    public void addCode(ByteCode newCode){
        this.program.add(newCode);
    }


}
