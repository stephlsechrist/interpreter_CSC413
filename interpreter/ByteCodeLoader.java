package interpreter;

import interpreter.bytecode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = " ";

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     * Tokenize string to break it into parts.
     * Grab THE correct class name for the given ByteCode from CodeTable
     * Create an instance of the ByteCode class name returned from code table.
     * Parse any additional arguments for the given ByteCode and send them to
     * the newly created ByteCode instance via the init function.
     */

    // source for help using BufferedReader & StringTokenizer:
    // https://stackoverflow.com/questions/21972561/stringtokenizer-not-working-with-bufferedreader
    public Program loadCodes() {
        Program programCode = new Program();
        // try catch block to catch the many errors being encountered during java reflection
        try {
            ArrayList<String> arguments = new ArrayList<>();
            int j = 0;

//        while there are lines in file
            while (byteSource.ready()) {
//            while there are tokens in line, create instance of ByteCode class
//            and save arguments to ArrayList<String>
                tokenizer = new StringTokenizer(this.byteSource.readLine(), DELIMITERS, false);
                String currByteCode = CodeTable.getClassName(tokenizer.nextToken());
                Class c = Class.forName("interpreter.bytecode." + currByteCode);
                ByteCode bc = (ByteCode) c.getDeclaredConstructor().newInstance();
//                System.out.println("Created instance of BC from " + currByteCode);
                int i = 0;
                while (tokenizer.hasMoreTokens()) {
                    arguments.add(tokenizer.nextToken());
//                    System.out.println("arg" + i + ": " + arguments.get(i));
//                    program.add(bc.init(arguments));
//                    System.out.println(bc + " " + arguments.get(i));
                    i++;
                }
                bc.init(arguments);
                programCode.addCode(bc);
//                System.out.println("BC just added: " + programCode.getCode(j) + " " + (j+1));
                arguments.clear();
                j++;
//                currLine = this.byteSource.readLine();
            }
        } catch (Exception error) {
            System.out.println("Cannot load current bytecode.");
            return null;
        }

//        System.out.println();
        // want to return programCode with resolved address
        // might need to move this line of code; not sure yet.
//        System.out.println(programCode.getCode(1));
        programCode.resolveAddrs();
//        System.out.println("BCL: addresses resolved and about to return programCode\n");
        return programCode;
    }
}
