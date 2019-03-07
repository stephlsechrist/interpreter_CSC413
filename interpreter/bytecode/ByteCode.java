/* ************************************************
ByteCode.java

Created by: Stephanie Sechrist
Last Edited: March 6, 2019

Abstract parent class of all byte codes.
All byte codes have
+execute(VirtualMachine vm): void
+init(ArrayList<String> arguments): void
+printBC(): String
************************************************* */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public abstract class ByteCode {

    public abstract void execute(VirtualMachine vm);

    public abstract void init(ArrayList<String> arguments);

    public String printBC(){
        return "";
    }
}
