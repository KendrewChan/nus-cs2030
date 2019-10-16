import java.io.*;

class Main {
    public static void main(String args[]) {
        try {
            BufferedReader scroll = new BufferedReader(new FileReader(args[0]));

            Farm farm = new Farm();
            
            while (true) {
                try {
                    String line = scroll.readLine();
                     
                    if (line == null) {
                        break;
                    } else {
                        farm.runInstruction(line);
                    }
                } catch (IOException e) {
                    break;
                } catch (IllegalInstructionException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {

        }
    }
}
