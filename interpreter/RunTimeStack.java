/* *************************************************
runTimeStack.java

Modified by: Stephanie Sechrist
Last Edited: March 6, 2019

All functions added except constructor
+dump(): void
+peek(): int
+pop(): int
+newFrameAt(int offset): void
+popFrame(): void
+peekFrame(): String
+store(int offset): int
+load(int offset): int
+push(Integer val): Integer

- runTimeStack controlled by VM. not accessible by
  bytecodes
- this is where I prevent the BC from doing illegal
  operations to the RTS
************************************************* */

package interpreter;

import java.util.*;
import java.lang.String;

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

    // stuck on this for a few hours and down to the wire
    // i was trying divide the stack using substring at values that
    // occur in my frame pointer, but the formatting was not happening for me
    // also, i couldn't figure out a way to do the above and have a dynamic amount
    // of substrings.
    // collaborated with Thomas Sechrist
    // ended up using his algorithm
    public void dump() {
        ArrayList<Integer> clonedStack;
        clonedStack = (ArrayList) runTimeStack.clone();
        ArrayList holdArray[] = new ArrayList[framePointer.size()];


        for (int i = framePointer.size(); i > 0; i--) {
            holdArray[i - 1] = new ArrayList<>();
            int top = framePointer.get(i - 1);
            int size = clonedStack.size();

            for (int j = top; j < size; j++) {
                holdArray[i - 1].add(clonedStack.remove(top));
            }
        }

        for (int i = 0; i < framePointer.size(); i++) {
            System.out.print(holdArray[i].toString());
        }
        System.out.println();
    }

    // peek returns the value at the top of the stack
    // only allowed if RTS is not empty
    // if empty, return 0
    public int peek() {
        if (!runTimeStack.isEmpty()) {
            return (int) runTimeStack.get(runTimeStack.size() - 1);
        }

        else {
            return 0;
        }
    }

    // pop removes the item at the top of the stack and returns it
    // only allowed if RTS is not empty and the frame at the top
    //    of the stack is not empty. not allowed to pop past frame boundaries
    // otherwise, return 0
    public int pop() {
        int value = peek();
        // don't want to pop past frame
        if (!runTimeStack.isEmpty() && (runTimeStack.size() > framePointer.peek())) {
            runTimeStack.remove(runTimeStack.size() - 1);
        }
        else {
            return 0;
        }
        return value;
    }

    // inserts new frame offset from top of stack
    // always allowed if called
    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }

    // when popping a frame, want to keep the value at the top of the RTS
    //    and return it to calling method
    // destroy other values on RTS above said frame, remove the frame,
    //    and push the kept value back on the stack
    public void popFrame() {
        try {
            if (runTimeStack.size() > framePointer.peek()) {
                int valueToReturn = pop();
                while (runTimeStack.size() > framePointer.peek()) {
                    pop();
                }
                framePointer.pop();
                push(valueToReturn);
            }
        } catch (EmptyStackException error) {
        }
    }

    // peekFrame is used mainly in order to display the CALL byte code correctly
    //   when dump is on. want to show the arguments being passed into a function
    //   when CALL is executed. in other words, the values in the top frame on RTS
    // we are returning a formatted String of the values, including commas
    // I iterate through RTS starting from the most recent frame pointer and add
    //   the values to the string we are returning until we reach end of RTS
    public String peekFrame() {
        String valuesInFrame = "";
        try {
            for (int i = framePointer.peek(); i <= (runTimeStack.size() - 1); i++) {
                valuesInFrame = (valuesInFrame + runTimeStack.get(i));
                if (i != (runTimeStack.size()-1)) {
                    valuesInFrame = valuesInFrame + ",";
                }
            }
        } catch (Exception e) {}
        return valuesInFrame;
    }

    // store pops the top value off the RTS and saves it into offset n
    //   from the start of the frame; returns value stored
    // only works if the RTS is not empty or the top frame is not empty
    //   otherwise, return 0
    public int store(int offset) {
        if (runTimeStack.size() > framePointer.peek()) {
            int temp = pop();
            int offsetFromFrame = offset + (framePointer.peek());
            // need to check again because altered RTS
            if (runTimeStack.size() > framePointer.peek()) {
                Integer temp1 = runTimeStack.remove(offsetFromFrame);
                runTimeStack.add(offsetFromFrame, (Integer) temp);
            }
            return temp;
        }
        else {
            return 0;
        }
    }

    // load saves the value that is n offset from start of the frame
    //   and pushes it onto the RTS; returns value loaded
    // only works if the RTS is not empty and the offset being requested
    //   is not greater than the number of items in the top frame
    public int load(int offset) {
        int offsetFromFrame = offset + (framePointer.peek());
        int frameSize = (runTimeStack.size() - framePointer.peek());
        if (!runTimeStack.isEmpty() && (offset < frameSize)) {
            int temp = (int) runTimeStack.get(offsetFromFrame);
            push((Integer) temp);
            return temp;
        }
        else {
            return 0;
        }
    }

    // push pushes a given Integer onto the stack and returns what is pushed
    public Integer push(Integer val) {
        if (runTimeStack.isEmpty()) {
            runTimeStack.add(0, val);
        }
        else {
            runTimeStack.add((runTimeStack.size()), val);
        }
        return val;
    }
    //    used for debugging purposes
    //    public int size() {
    //        return runTimeStack.size();
    //    }
    //
    //    public int get(int pos) {
    //        return runTimeStack.get(pos);
    //    }

}
