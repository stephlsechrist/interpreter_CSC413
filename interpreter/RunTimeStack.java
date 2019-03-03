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
    }

    public int peek() {
        //        runTimeStackSize = runTimeStack.size();
        if (!runTimeStack.isEmpty()) {
            return (int) runTimeStack.get(runTimeStack.size() - 1);
        }

        else {
            // change print statement later
            // System.out.println("The run time stack is empty");
            throw new EmptyStackException();
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
            throw new EmptyStackException();
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
        if (runTimeStack.size() > framePointer.peek()) {
            int temp = pop();
            int offsetFromFrame = offset + (framePointer.peek());
            // need to check again because altered RTS
            if (runTimeStack.size() > framePointer.peek()) {
                runTimeStack.remove(offsetFromFrame);
                runTimeStack.add(offsetFromFrame, (Integer) temp);
            }
            return temp;

        }
        else {
            throw new EmptyStackException();
        }
    }

    public int load(int offset) {
        int offsetFromFrame = offset + (framePointer.peek());
        if (!runTimeStack.isEmpty()) {
            int temp = (int) runTimeStack.get(offsetFromFrame);
            push((Integer) temp);
            return temp;
        }
        else {
            throw new EmptyStackException();
        }
    }

    public Integer push(Integer val) {
        if (runTimeStack.isEmpty()) {
            runTimeStack.add(0, val);
        }
        else {
            runTimeStack.add((runTimeStack.size() - 1), val);
        }
        return val;
    }
}
