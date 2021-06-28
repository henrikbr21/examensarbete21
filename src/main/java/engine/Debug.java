package engine;

public abstract class Debug {

    public static boolean findDuplicate(Board board){


        for(int i = 0; i < 64; i++){
            int copiesFound = 0;
            long pos = AttackSets.getPosition(i);

            if((board.WP & pos) != 0){
                copiesFound++;
            }
            if((board.WN & pos) != 0 ){
                copiesFound++;
            }
            if((board.WB & pos) != 0 ){
                copiesFound++;
            }
            if((board.WR & pos) != 0 ){
                copiesFound++;
            }
            if((board.WK & pos) != 0 ){
                copiesFound++;
            }
            if((board.WQ & pos) != 0){
                copiesFound++;
            }
            if((board.BP & pos) != 0){
                copiesFound++;
            }
            if((board.BN & pos) != 0){
                copiesFound++;
            }
            if((board.BB & pos) != 0){
                copiesFound++;
            }
            if((board.BR & pos) != 0 ){
                copiesFound++;
            }
            if((board.BK & pos) != 0 ){
                copiesFound++;
            }
            if((board.BQ & pos) != 0){
                copiesFound++;
            }


            if(copiesFound > 1){
                System.out.println("ERROR SEVERAL PIECES ON ONE SQUARE!!!!!");
                return true;
            }

        }
        return false;

    }

}
