package interpreter;

import interpreter.bytecode.ByteCode;
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
        for (int i = 0; i < program.size(); i++){
            System.out.println(program.get(i) + " " + program.size());
        }
        System.out.println("resolveAddr: finished");

        for (int i = 0; i < program.size(); i++){
            int posToClip = program.get(i).toString().indexOf("@");
            String bc = program.get(i).toString().substring(21,posToClip);
            System.out.println(bc);
        }
    }

    public void addCode(ByteCode newCode) {
        this.program.add(newCode);
    }


}
