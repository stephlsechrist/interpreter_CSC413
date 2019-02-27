
package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */

    public Program loadCodes() throws IOException{
        String token = this.byteSource.readLine();
        this.tokenizer = new StringTokenizer(token, DELIMITERS, false);

//        while there are lines in file
        while(token != null){
//            while there are tokens in line, create instance of ByteCode class
//            and save arguments to ArrayList<String>
            while(this.tokenizer.hasMoreTokens()) {
                String currByteCode = CodeTable.getClassName(token);
                // see CodeTable section for more code?
                // do something
            }
            token = this.byteSource.readLine();
        }
        return null;
    }
}
