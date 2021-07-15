package engine;
import java.util.*;

public class UCI extends Thread {

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        while(true){
            String input = scan.nextLine();

            if(input.equals("uci")){
                System.out.println("id name Klas 0.1");
                System.out.println("id author Henrik");
            }else if(input.equals("isready")){
                System.out.println("readyok");
            }else if(input.equals("ucinewgame")){

            }else if(input.startsWith("position")){

            }else if(input.equals("go")){

            }

        }
    }
}
