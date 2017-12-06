import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MissingNumberFinder {

    private ArrayList<String> missingNumberStrings;
    private ArrayList<Integer> numbersInString, possibleMissingNumbers;
    private int lowestNumber, highestNumber;

    public ArrayList<Integer> getPossibleMissingNumbers() {
        return possibleMissingNumbers;
    }


    public int findThatShizzzzzz(){
        int majorCoutner = 0;
        while (majorCoutner < 50){
            majorCoutner++;
//            System.out.println("Numbers in string: " + Arrays.toString(numbersInString.toArray()));
//            System.out.println("Missing number strings: " + Arrays.toString(missingNumberStrings.toArray()));
//            System.out.println("Possible missing numbers: " + Arrays.toString(possibleMissingNumbers.toArray()));
//            System.out.println();

            // Loop from biggest number to smallest
            for (int i = numbersInString.size()-1; i >= 0; i--){
                String numberAsString = numbersInString.get(i).toString();
                // If number is in possible missing numbers, don't consider removing
                boolean skip = false;
                for (int j = 0; j < possibleMissingNumbers.size(); j++){
                    if (numberAsString.equals(possibleMissingNumbers.get(j).toString())){
                        skip = true;
                    }
                }
                if (skip){
                    continue;
                }

                // If you find the number in the strings, add one to counter
                int stringIndex = -1;
                int counter = 0;
                for (int j = 0; j < missingNumberStrings.size(); j++){
                    for (int k = 0; k < missingNumberStrings.get(j).length() + 1 - numberAsString
                            .length();
                         k++){
                        if (numberAsString.equals(missingNumberStrings.get(j).substring(k, k +
                                numberAsString.length()))){
                            stringIndex = j;
                            counter++;
                        }
                    }
                }

                // Only one instance of number found
                if (stringIndex != -1 && counter == 1){
                    String splitString = missingNumberStrings.get(stringIndex);
                    String leftStr = splitString.substring(0, splitString.indexOf(numberAsString));
                    String rightStr = splitString.substring(splitString.indexOf(numberAsString) +
                            numberAsString.length());

                    if (leftStr.length() != 0) missingNumberStrings.add(leftStr);
                    if (rightStr.length() != 0) missingNumberStrings.add(rightStr);
                    missingNumberStrings.remove(splitString);
                    numbersInString.remove(numbersInString.indexOf(Integer.parseInt
                            (numberAsString)));
                }
            }

            boolean found = false;

            // Loop through possible numbers.
            for (int j = 0; j < possibleMissingNumbers.size(); j++){
                int number = possibleMissingNumbers.get(j);
                // If that number is found, flag it as found.
                for (int i = 0; i < missingNumberStrings.size(); i++){
                    number = possibleMissingNumbers.get(j);
                    if (missingNumberStrings.get(i).contains(Integer.toString(number))){
                        found = true;
                    }
                }
                // If that number was never found in any missing number strings, it's the number.
                if (!found){
                    return number;
                }
            }

        }
        return possibleMissingNumbers.get(0);
    }

    public ArrayList<Integer> getMissingDigits(String missingNumberString){

        // Calculate highest digit used in number sequence.
        int highestDigitUsed = 9;
        if (highestNumber < 10){
            highestDigitUsed = highestNumber;
        }
        // Build a hashmap containing all of the digits used
        HashMap<String, Integer> numberAppearances = new HashMap<String, Integer>();
        for (int i = 0; i <= highestDigitUsed; i++){
            String num = Integer.toString(i);
            numberAppearances.put(num, 0);
        }

        // Build a string of every possible number ordered
        String allNumsOrdered = "";
        for (int i = lowestNumber; i <= highestNumber; i++){
            numbersInString.add(i);
            allNumsOrdered += Integer.toString(i);
        }

        // Set the values to the expected number of appearances by that number
        for( int i=0; i < allNumsOrdered.length(); i++ ) {
            String key = Character.toString(allNumsOrdered.charAt(i));
            numberAppearances.put(key, numberAppearances.get(key) + 1);
        }

        // Every time you find a digit, subtract it from the expected appearances
        // After looping through the entire string, you will know your missing digits
        for( int i=0; i < missingNumberString.length(); i++ ) {
            String key = Character.toString(missingNumberString.charAt(i));
            numberAppearances.put(key, numberAppearances.get(key) - 1);
        }

        // Build ArrayList of digits with values over 1.
        ArrayList<Integer> missingDigits = new ArrayList<Integer>();
        for (String key : numberAppearances.keySet()) {
            for (int i = 0; i < numberAppearances.get(key); i++){
                missingDigits.add(Integer.parseInt(key));
            }
        }
        return missingDigits;
    }

    public void setPossibleMissingNumbers(ArrayList<Integer> arrayList){
        ArrayList<Integer> validNumbers = new ArrayList<Integer>();
        for (int i : arrayList){
            if (i <= highestNumber && i >= lowestNumber){
                validNumbers.add(i);
            }
        }
        possibleMissingNumbers = validNumbers;
    }

    public MissingNumberFinder(String missingNumberString, int low, int high){
        this.missingNumberStrings = new ArrayList<>();
        this.missingNumberStrings.add(missingNumberString);
        this.lowestNumber = low;
        this.highestNumber = high;
        numbersInString = new ArrayList<Integer>();
    }

}
