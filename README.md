# Generating Roman Numerals: Notes

## The approach
Read the wikipedia article about roman numerals. I then searched to see what sort of algorithms were suggested for conversion from arabic numbers to Roman Numerals.
All the results I read seemed to converge on this approach:  
https://www.rapidtables.com/convert/number/how-number-to-roman-numerals.html  
So I chose to implement that one.

I worked on the core of the algorithm using the REPL and unit tests to drive the development. Once that was working, I added a main method to allow it to be called from the command line.
While experimenting at the command line I thought of some edge cases (for example -1) and it was at that point I refactored out my edge case handling.
I then added Linux and Windows batch script files to make it easier to run.

### Implementation
Based on this sorted vector (array) of arabic numbers and keywordised versions of their Roman Numerals:
```clojure
[[1000 :M]
  [900 :CM]
  [500 :D]
  [400 :CD]
  [100 :C]
  [90 :XC]
  [50 :L]
  [40 :XL]
  [10 :X]
  [9 :IX]
  [5 :V]
  [4 :IV]
  [1 :I]]]
```
find the numeral that is less than or equal to the number to be converted (implemented as ```get-lowest-roman-for-number```, using ```reduce```).

In ```generate``` there is a loop which uses ```get-lowest-roman-for-number```, subtracts the number that found a match from the number being converted and loops round until the remainder hits zero. Each match is ```conj```'d into a vector so 8 would be ```[:V :I :I :I]```
That vector is then turned into a string by ```roman-keys->string```

```-main``` (which is invoked from the command line just handles printing and conversion of the command line args (which arrive in the code as strings) and then calls ```generate-response```.  
That allows ```generate-response``` to handle boundary and edge cases (including formatting responses) and delegate the actual conversion to ```generate```.


## Running the code

### Via Leiningen ('lein')
  
```lein run <arg>```  
i.e.   
```lein run 8```

### From a JAR file
To run from a jar, first build a jar:
Run ```lein uberjar``` from the root of the project.

Then either:  
__Use the BASH script:__  
```./roman-numerals 8```

__or the Windows Batch script:___  
```roman-numberals.bat 8```
> Both the scripts should check for the existence of the JAR file and invoke ```lein uberjar``` if it does not exist.

__or invoke Java directly:__         
```java -jar target\roman-numerals-0.1.0-SNAPSHOT-standalone.jar <arg>```  
i.e.  
```java -jar target\roman-numerals-0.1.0-SNAPSHOT-standalone.jar 66```

## Running the tests
You can run all the tests with
```lein test``` from the root of the project.


