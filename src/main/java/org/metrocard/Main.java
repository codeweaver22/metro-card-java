package org.metrocard;

import org.metrocard.service.MetroCardProcessor;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide input file path");
            return;
        }

        String filePath = args[0];

        MetroCardProcessor processor = new MetroCardProcessor();
        processor.process(filePath);
    }
}