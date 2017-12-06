# Josh-Challenge

My friend Joshua challenged me to take a string of numbers in the range [0, 250) that are scrambled and find the missing digit.

An example simplifies this:
input string => "185492076" in the range [0, 10).
Missing digit = 3

# If you are an employer 
I don't recommend looking at this code as a representation for me because this was definitely hacked together and just me having some fun.
I was not careful in how I did things and may have not used the proper conventions and all that good stuff. 
I just wanted to whip something together to tell my friend I did it haha.

# To all
Fair warning, this is not complete, so if anyone is looking to get their hands dirty and learn, be my guest.

All of the logic is in the findThatShizzzzzz() function under MissingNumberFinder.java so make your changes there if you want to do this.
You can assume that:
```
private ArrayList<String> missingNumberStrings;
private ArrayList<Integer> numbersInString, possibleMissingNumbers;
private int lowestNumber, highestNumber;
```
are all properly filled in by the time you call findThatShizzzzzz();

# Explanations of what these are #
missingNumberStrings is a list of substrings of the big inputted string. This starts as a list containing only the big inputted string.
How this works is that if I know with 100% certainty that I have found a number then I remove that from the string and split it into 2 more strings.
(Example: ".....10924910....." where '249' only appears once across all of the missingNumberStrings, I will get the strings ".....109" and "10.....", add those to the missingNumberStrings list, and remove the entry containing these two strings joined by '249'.
I understand this can be a bit abstract, but it must be understood to work on the code. You can contact me with questions.

numbersInString is a list of all numbers that can be in the missingNumberStrings.
In this case, it's simply a list from [0, 249), but when a number is found, I remove it from this list as well.

possibleMissingNumbers is a list of all of the possible missing numbers.
This works by first finding which digits are missing in the string (see getMissingDigits function) and then using the PermutationEngine object to get every possible unique permutation of those digits.
(i.e {1, 1, 4} when passed into the Permutation engine as "114" will return {"114", "141", "411"}.)
Then, because our range is [0, 250), the setPossibleMissingNumbers function will (based off of example above) remove 411, because it's not a number that is in the string on purpose.
I say on purpose because if the number 4 and 11 end up next to each other, 411 technically appears, but it shouldn't be considered.

lowestNumber and highestNumber simply defines our range. 
This is set in the constructor.
I currently have it pass in 0 for the lowestNumber and 249 for the highestNumber because 250 is not included in our examples.

Hopefully given this ReadMe and some documentation throughout the code, you can hack away, see how it works and maybe make something of it. 

# Again, this is not complete! Change this and hack away at it and most importantly, have fun!

A little side note - stay out of the PermutationEngine class. It's a tool used in this program, and shouldn't require any further use.
If you want to use it as a utility do:

```
PermutationEngine engine = new PermutationEngine("Some String Here");
ArrayList<String> arr = engine.getPermutations();
```

You shouldn't have to modify it really, but by all means, do whatever you want!
