@echo off

set argCount=0
for %%x in (%*) do (
   set /A argCount+=1
)
if not %argCount% == 1 (
echo "Error: Roman numerals expects a single (digit) argument."
exit /B
)

set JAR_FILE=target\roman-numerals-0.1.0-SNAPSHOT-standalone.jar
echo %JAR_FILE%
if exist %JAR_FILE% (
    java -jar %JAR_FILE% %1
) else (
    lein uberjar
	java -jar %JAR_FILE% %1
)

