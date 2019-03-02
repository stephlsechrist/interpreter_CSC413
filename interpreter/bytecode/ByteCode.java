package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode {
//    public ByteCode(){
//        String code;
////        String className = CodeTable.get(code);
////        Class c = Class.forName("interpreter.bytecode." + classname);
//    }
            //constructor

//    public ByteCode getCode(int index){
//        return null;
//    }


    public abstract void execute(VirtualMachine vm);

    public abstract void init(ArrayList<String> arguments);
}
