/* ************************************************
ArgsCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Requests virtual machine to:
Inserts a frame at n down from top of RTS
************************************************* */
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

    @Override
    public String printBC(){
        return ("ARGS " + frameInsert);
    }
}
