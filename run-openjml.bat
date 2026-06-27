@echo off
REM ====================================================
REM OpenJML Verification Runner for SD26 Encoder
REM ====================================================
REM Prerequisites:
REM 1. Download OpenJML from: https://github.com/OpenJML/OpenJML/releases
REM    (Download the latest openjml.zip or openjml.jar)
REM 2. Extract openjml.jar to: C:\openjml\openjml.jar
REM ====================================================

set OPENJML_JAR=C:\openjml\openjml.jar
set SOURCE_FILE=src\main\java\com\sd26\encoder\service\CodecService.java
set CLASSPATH=target\classes;%USERPROFILE%\.m2\repository\commons-codec\commons-codec\1.17.1\commons-codec-1.17.1.jar

echo =============================================
echo Running OpenJML Static Checking (ESC Mode)
echo =============================================

if not exist "%OPENJML_JAR%" (
    echo [ERROR] OpenJML not found at %OPENJML_JAR%
    echo.
    echo Please download OpenJML from:
    echo https://github.com/OpenJML/OpenJML/releases
    echo.
    echo Extract openjml.jar to C:\openjml\openjml.jar
    pause
    exit /b 1
)

java -jar "%OPENJML_JAR%" -esc -progress -spec-prec -classpath "%CLASSPATH%" "%SOURCE_FILE%"

echo.
echo =============================================
echo Verification completed. Check output above.
echo =============================================
pause
