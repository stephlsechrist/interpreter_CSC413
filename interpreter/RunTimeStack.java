package interpreter;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    // recursive iterator do be able to display with commas until frame is reached.
    public void dump() {
        String rawOutput = runTimeStack.toString();
        char[] outputArray = rawOutput.toCharArray();
        for (int i = 0; i < outputArray.length; i++) {
            if (framePointer.contains(i + 1)) {
                outputArray[i] = ']';
            }
            System.out.print(outputArray[i] + " ");
//            else if (framePointer.contains(i)) {
//                System.out.print("[");
//            }
//
//            else{
//                System.out.print(runTimeStack.get(i) + ",");
            }
            System.out.println();
            System.out.print(runTimeStack.toString());
//        }
//        if (!runTimeStack.isEmpty())
            System.out.println();
    }

    public int peek() {
        //        runTimeStackSize = runTimeStack.size();
        if (!runTimeStack.isEmpty()) {
            return (int) runTimeStack.get(runTimeStack.size() - 1);
        }

        else {
            // change print statement later
            System.out.println("The run time stack is empty");
            //            throw new EmptyStackException();
            return 0;
        }
        //        return 0;
    }

    public int pop() {
        int value = peek();
        // don't want to pop past frame
        if (!runTimeStack.isEmpty() && (runTimeStack.size() > framePointer.peek())) {
            runTimeStack.remove(runTimeStack.size() - 1);
        }
        else {
            System.out.println("Frame is empty");
            //            throw new EmptyStackException();
            return 0;
        }
        return value;
    }

    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }

    // when popping a frame, want to keep the value at the top of the RTS
    // and return it to calling method
    // destroy other values on RTS above said frame
    public void popFrame() {
        try {
            if (runTimeStack.size() > framePointer.peek()) {
                int valueToReturn = pop();
                while (runTimeStack.size() > framePointer.peek()) {
                    pop();
                }
                framePointer.pop();
                push((Integer) valueToReturn);
            }
        } catch (EmptyStackException error) {
            System.out.println("Frame could not be popped");
        }
    }

    public int store(int offset) {
        //        System.out.println("entered store in RTS");
        if (runTimeStack.size() > framePointer.peek()) {
            //            System.out.println("frame not empty so value to store exists on top of stack");
            int temp = pop();
            int offsetFromFrame = offset + (framePointer.peek());
            //            System.out.println("Offset is " + offset + " & frame pointer is at " + framePointer.peek());
            //            System.out.println("we are loading the value at index " + offsetFromFrame);
            // need to check again because altered RTS
            if (runTimeStack.size() > framePointer.peek()) {
                //                System.out.println("frame not empty so storing at offset");
                Integer temp1 = runTimeStack.remove(offsetFromFrame);
                //                System.out.println("removed item : " + temp1.toString());
                runTimeStack.add(offsetFromFrame, (Integer) temp);
                //                System.out.println("added item " + runTimeStack.get(offsetFromFrame).toString());
            }
            return temp;

        }
        else {
            //            throw new EmptyStackException();
            System.out.println("The stack is empty");
            return 0;
        }
    }

    public int load(int offset) {
        //        System.out.println("entered load in RTS");
        int offsetFromFrame = offset + (framePointer.peek());
        //        System.out.println("Offset is " + offset + " & frame pointer is at " + framePointer.peek());
        //        System.out.println("we are loading the value at index " + offsetFromFrame);
        if (!runTimeStack.isEmpty()) {
            //            System.out.println("RTS not empty, so performing load");
            int temp = (int) runTimeStack.get(offsetFromFrame);
            //            System.out.println("value we're loading to top of stack " + temp);
            push((Integer) temp);
            //            System.out.println("finished with load in RTS; back to VM");
            return temp;
        }
        else {
            //            throw new EmptyStackException();
            System.out.println("The stack is empty");
            return 0;
        }
    }

    public Integer push(Integer val) {
        if (runTimeStack.isEmpty()) {
            runTimeStack.add(0, val);
        }
        else {
            runTimeStack.add((runTimeStack.size()), val);
        }
        return val;
    }

    public int size() {
        return runTimeStack.size();
    }

    public int get(int pos) {
        return runTimeStack.get(pos);
    }

}
