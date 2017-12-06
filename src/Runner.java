import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        long time = System.currentTimeMillis();
        try{
            Scanner file = new Scanner(new File("missingnums.in"));
            while(file.hasNext()){
                String missingNumberString = file.nextLine();
                MissingNumberFinder missingNumberFinder = new MissingNumberFinder(missingNumberString, 0, 249);
                ArrayList<Integer> missingDigits = missingNumberFinder.getMissingDigits(missingNumberString);
                String missingDigitsString = "";
                for (int j : missingDigits){
                    missingDigitsString += Integer.toString(j);
                }
                PermutationEngine engine = new PermutationEngine(missingDigitsString);
                missingNumberFinder.setPossibleMissingNumbers(engine.getPermutations());
                int missingNumber = missingNumberFinder.findThatShizzzzzz();
                System.out.println("THE MISSING NUMBER IS: " + missingNumber);
            }
        }catch(Exception e){
            
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
}
