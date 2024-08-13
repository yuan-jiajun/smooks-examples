package org.smooks.examples.test;

import org.smooks.examples.test.SmooksTransform;

public class Test {
    public static void main(String[] args) throws Exception {
        SmooksTransform smooksTransform = new SmooksTransform();
        String result = smooksTransform.runSmooksTransform();
        System.out.println(result);
    }
}
