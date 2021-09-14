package march;

import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> PlayerPositions=new ArrayList<>();
    static ArrayList<Integer> CpuPositions=new ArrayList<>();

    public static void main(String[] args){
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
       Scanner sc =new Scanner(System.in);
        while (true) {
//            Scanner sc =new Scanner(System.in);
            System.out.println("Enter your placement(1-9) : ");
            int Playerpos = sc.nextInt();

            while (PlayerPositions.contains(Playerpos)|| CpuPositions.contains(PlayerPositions)){
                System.out.println("Position Taken! Enter correct Position ");
                Playerpos=sc.nextInt();

            }

            placePiece(gameBoard, Playerpos, "player");
            String result =checkWinner();
            if (result.length()>0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (PlayerPositions.contains(cpuPos)|| CpuPositions.contains(cpuPos)){
//                System.out.println("Position Taken! Enter correct Position ");
                cpuPos = rand.nextInt(9) + 1;

            }
            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);//its static so we can call it like these
            result =checkWinner();
            if (result.length()>0) {
                System.out.println(result);
                break;
            }
        }
    }
    public static void printGameBoard(char[][] gameBoard){
        for (char[] row:gameBoard){
            for (char c:row){
                System.out.print(c);
            }
            System.out.println();
        }

    }
    public static void placePiece(char[][] gameBoard,int pos, String user){
        char symbol=' ';
        if (user.equals("player")){
            symbol='X';
            PlayerPositions.add(pos);
        }
        else if (user.equals("cpu")){
            symbol='O';
            CpuPositions.add(pos);
        }
        switch (pos){
            case 1:
                gameBoard[0][0]= symbol;
                break;
            case 2:
                gameBoard[0][2]=symbol;
                break;
            case 3:
                gameBoard[0][4]=symbol;
                break;
            case 4:
                gameBoard[2][0]=symbol;
                break;
            case 5:
                gameBoard[2][2]=symbol;
                break;
            case 6:
                gameBoard[2][4]=symbol;
                break;
            case 7:
                gameBoard[4][0]=symbol;
                break;
            case 8:
                gameBoard[4][2]=symbol;
                break;
            case 9:
                gameBoard[4][4]= symbol ;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + pos);
        }

    }
    public static String checkWinner(){
        List topRow= Arrays.asList(1,2,3);
        List MidRow= Arrays.asList(4,5,6);
        List BottomRow= Arrays.asList(7,8,9);
        List topCol= Arrays.asList(1,4,7);
        List MidCol= Arrays.asList(2,5,8);
        List BottomCol= Arrays.asList(3,6,9);
        List Cross1= Arrays.asList(1,5,9);
        List Cross2= Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(MidRow);
        winning.add(BottomRow);
        winning.add(topCol);
        winning.add(MidCol);
        winning.add(BottomCol);
        winning.add(Cross2);
        winning.add(Cross1);
        for (List l: winning){
            if (PlayerPositions.containsAll(l)){
                return "Congratulations You WON!!";
            }else if (CpuPositions.containsAll(l)){
                return "Better Luck Next Time :)";
            }else if (PlayerPositions.size()+CpuPositions.size()==9){
                return "LOL";
            }
        }





        return "";
    }
}
