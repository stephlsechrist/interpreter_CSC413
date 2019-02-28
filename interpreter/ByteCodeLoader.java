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
        ArrayList<ByteCode> program = new ArrayList<>();
        Program programCode = new Program();
        // try catch block to catch the many errors being encountered during java reflection
        try {
            String currLine = this.byteSource.readLine();
            String token;
            this.tokenizer = new StringTokenizer(currLine, DELIMITERS, false);
            ArrayList<String> arguments = new ArrayList<>();

//        while there are lines in file
            while (currLine != null) {
//            while there are tokens in line, create instance of ByteCode class
//            and save arguments to ArrayList<String>
                token = this.tokenizer.toString();
                String currByteCode = CodeTable.getClassName(token);
                Class c = Class.forName("interpreter.bytecode." + currByteCode);
                ByteCode bc = (ByteCode) c.getDeclaredConstructor().newInstance();
                while (this.tokenizer.hasMoreTokens()) {
                    arguments.add(this.tokenizer.nextToken());
//                    program.add(bc.init(arguments));
                    programCode.addCode(bc.init(arguments));
                }
            }
        } catch (Exception error) {
            System.out.println("Cannot load current bytecode.");
            return null;
        }

        // want to return programCode with resolved address
        // might need to move this line of code; not sure yet.
        programCode.resolveAddrs();
        return programCode;
    }
}
