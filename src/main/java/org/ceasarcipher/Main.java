package org.ceasarcipher;

import org.ceasarcipher.cli.Executor;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {

        } else {
            new Executor().showMenu();
        }
    }
}
