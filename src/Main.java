import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    private ArrayList<String> missingNumberStrings;
    private ArrayList<Integer> numbersInString, possibleMissingNumbers;
    private int lowestNumber, highestNumber;

    public ArrayList<Integer> getPossibleMissingNumbers() {
        return possibleMissingNumbers;
    }


    public int findThatShizzzzzz(){
        int majorCoutner = 0;
        while (majorCoutner < 5){
            majorCoutner++;
            System.out.println("Numbers in string: " + Arrays.toString(numbersInString.toArray()));
            System.out.println("Missing number strings: " + Arrays.toString(missingNumberStrings.toArray()));
            System.out.println("Possible missing numbers: " + Arrays.toString(possibleMissingNumbers.toArray()));
            System.out.println();

            // Loop from biggest number to smallest
            for (int i = numbersInString.size()-1; i > 0; i--){
                String numberAsString = numbersInString.get(i).toString();

                // If number is in possible missing numbers, don't consider removing
                boolean skip = false;
                for (int j = 0; j < possibleMissingNumbers.size(); j++){
                    if (numberAsString.equals(possibleMissingNumbers.get(j).toString())){
                        skip = true;
                    }
                }
                if (skip){
                    System.out.println("WE SKIPPPPIN");
                    continue;
                }

                // If you find the number in the strings, add one to counter
                int counter = 0;
                int stringIndex = -1;
                for (int j = 0; j < missingNumberStrings.size(); j++){
                    for (int k = 0; k < missingNumberStrings.get(j).length() - numberAsString.length();
                         k++){
                        if (numberAsString.equals(missingNumberStrings.get(j).substring(k, k +
                                numberAsString.length()))){
                            stringIndex = j;
                            counter++;
                            System.out.println("Counter is going up to : " + counter + " for " + numberAsString);
                        }
                    }
                }

                // Only one instance of number found
                if (counter == 1){
                    System.out.println("WE IN HERRRRRRRRRRRRRRRRRRRRRRRRRRREEEEEEEEEEEEE");
                    String leftStr;
                    String rightStr;
                    String splitString = missingNumberStrings.get(stringIndex);
                    leftStr = splitString.substring(0, splitString.indexOf(numberAsString));
                    rightStr = splitString.substring(splitString.indexOf(numberAsString) + numberAsString.length());
                    missingNumberStrings.add(leftStr);
                    missingNumberStrings.add(rightStr);
                    missingNumberStrings.remove(splitString);
                    numbersInString.remove(Integer.parseInt(numberAsString));
                }
            }
        }
        return possibleMissingNumbers.get(0);
    }

    private ArrayList<Integer> getMissingDigits(String missingNumberString){

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
        for (int i : arrayList){
            if (i > highestNumber || i < lowestNumber){
                arrayList.remove(i);
            }
        }
        possibleMissingNumbers = arrayList;
    }

    public Main(String missingNumberString, int low, int high){
        this.missingNumberStrings = new ArrayList<>();
        this.missingNumberStrings.add(missingNumberString);
        this.lowestNumber = low;
        this.highestNumber = high;
        numbersInString = new ArrayList<Integer>();
    }

    public static void main(String[] args){
        String input1 =
                "12918716012290166911152252375520717688136151587172021423814015893230126219245125111212831016420166724923611034914318154649257179193183117511123678101382031456820914256461592919762981317626212411301162271021811922811313315208182172120222011814432139392041621358962431067130153338692522115711918036246177141991701823111043196108242174217137127156021520621879146344873222693514716312415619116523114819418824016178652242082471982261211712107577667024408519321124821680892342291263223123105475420232951972337235161213150195109167104132100271752147459581731284441185424523984205521122441491554114721691865013853131072219016894134228199200184189";
        String input2 =
                "12315328174148224263315516515619678203842431571913420815914757101131126104235758513315020221983823915220716817122348671305816282041781057024010949164125372232421921162002332309918319513810699351020227110297216324712419824453108188129871192381861581816182774054426012019317514117021479226160245134742543122502209480173209632411222514219021217211661001831361801111461318745565293423271222689013216662233131722481113971391022117946205248392132175547149237231236141841431151811761899518214016911185199145222167620659194620110714469486524921522911241648811712761228367318524601771371352273015121915191679215451121321976711421889442101281031611";
        String input3 =
                "2915513314715224093581852381630188217205314018220422423015413642184194241411873421912367191944165249143150171114103811756679160246371672482454723910274591001621161261719142115203221103623348181827564777214911561069960180281931181272186321208320210820221117201169234222120107140702361301721078399719224115773832161522716557358824265612292522610987111742156233126131112321789511956819851624414638891011961215046811316492692231778612290761193295962492257220023123771441991322716618954455613417912821023190158431374176122122133014811620720917015317322288584124145168981381311414953181121511251392432202062355524718619710415980105135144211129";
        String input4 =
                "2196205179249135106811812265116316445243248244251715010515422970897212917323218544292614315661781021412216014687127170237108124169351011536219010993172144115211110246573139125191974194225136203802362141481231841335861521861871491372001201002213421315517811485142141118166157111158111832352344812122116721957202233117175269218138131245141321741888221224632472308409221012611214539361473722375921722791176591962074024133235518220199177113162542012157974161247103341932315610424083189129014019420916823871843182993077283316324211611910228664964204192198272067150191175224208982161586222165128574215197180381301074613688823951620695536021976";

        //int missingNumber1 = 37;
        //int missingNumber2 = 96;
        //int missingNumber3 = 183;
        //int missingNumber4 = 159;
        String temp = "822161427929623111541026253101972013282121751824";
        //missing number 21

        // Get missing digits
        Main mainObj = new Main(temp, 0, 29);
        ArrayList<Integer> missingDigits = mainObj.getMissingDigits(temp);

        // Get the digits in a string
        String tempStr = "";
        for (int i : missingDigits){
            tempStr += Integer.toString(i);
        }

        // Get every possible combination of the number
        PermutationEngine engine = new PermutationEngine(tempStr);
        mainObj.setPossibleMissingNumbers(engine.getPermutations());
        int missingNumber = mainObj.findThatShizzzzzz();

//        String[] missingNumberStrings = {input1, input2, input3, input4};
//        for (String tempStr : missingNumberStrings){
//            Main obj = new Main(tempStr, 0, 249);
//            for (String str : missingNumberStrings){
//                ArrayList<Integer> arr = obj.getMissingDigits(str);
//                for(int i : arr){
//                    System.out.print(i);
//                }
//                System.out.print("\n");
//            }
//        }
    }
}
