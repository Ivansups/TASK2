package ru.vsu.cs.course1;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.SwingUtils;

import java.io.PrintStream;
import java.util.Locale;


public class Program {

    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean mainProgr;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args[0].equals("-f")) {
                params.inputFile = args[1];
                if (args.length > 2)
                    params.outputFile = args[2];
            }
            params.mainProgr = true;
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }


    public static void winMain() throws Exception {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  -f <input-file> (<output-file>)// enter your file name where is numbers");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            winMain();
        } else {
            int[] arr2 = ArrayUtils.readIntArrayFromFile(params.inputFile);
            if (arr2 == null) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            }
            if (params.mainProgr) {
                MyLinkedList list = MyLinkedList.makeLinkedList(arr2);
                MyLinkedList.bubbleSort(list);
                arr2 = MyLinkedList.makeArray(list);
            }

            PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
            out.println(ArrayUtils.toString(arr2));
            out.close();
        }
    }
}
