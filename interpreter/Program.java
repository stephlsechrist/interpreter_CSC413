package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Program {

    private ArrayList<ByteCode> program;
    private ArrayList<Integer> labels;
    private int pc;
    private StringTokenizer tokenizer;

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
        ArrayList<String> labels = new ArrayList<>();
//        int pc;
        for (int i = 0; !(this.program.isEmpty()); i++) {
//            ByteCode bc = this.program.get(i);
            String currBC = this.program.get(i).toString();
            int startOfLabel = (currBC.indexOf(" ")) + 1;
            String isolatedBC = currBC.substring(0, (startOfLabel - 1));
            String label = currBC.substring(startOfLabel, getSize() - 1);

            if (((this.program.get(i).toString().compareToIgnoreCase("falsebranch")) == 0) ||
                    ((this.program.get(i).toString().compareToIgnoreCase("goto")) == 0) ||
                    ((this.program.get(i).toString().compareToIgnoreCase("call")) == 0)) {

                for (pc = 0; !(this.program.get(pc).toString().contains(label)); pc++) {
                    if (i == (getSize() - 1)) {
                        System.out.println("Label not found: " + label);
                    }
                }
                // remove BC with unresolved address
                this.program.remove(i);
                try {
                    String currByteCode = CodeTable.getClassName(isolatedBC);
                    Class c = Class.forName("interpreter.bytecode." + currByteCode);
                    ByteCode newBC = (ByteCode) c.getDeclaredConstructor().newInstance();
                    labels.add(Integer.toString(pc));
                    // replace BC with resolved address
                    this.program.add(i, newBC.init(labels));
                } catch (Exception e) {
                    System.out.println("in resolve address, couldn't make instance of BC.");
                }
            }
        }
    }

    public void addCode(ByteCode newCode) {
        this.program.add(newCode);
    }


}
