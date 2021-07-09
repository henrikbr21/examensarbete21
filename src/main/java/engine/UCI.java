package engine;
import java.util.*;

public class UCI {

    public static void com(){
        while(true){
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if(input.equals("uci")){
                System.out.print("id name Klas 0.1\n");
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
