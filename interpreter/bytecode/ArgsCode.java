package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private int frameInsert;

    @Override
    public void init(ArrayList<String> arguments) {
        try {
            frameInsert = Integer.parseInt(arguments.get(0));
        } catch (Exception error) {
            System.out.println("Fail in ArgsCode init");
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameRunStack(frameInsert);
    }
}
