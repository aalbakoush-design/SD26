#!/bin/bash
# ====================================================
# OpenJML Verification Runner for SD26 Encoder
# ====================================================
# Prerequisites:
# 1. Download OpenJML from: https://github.com/OpenJML/OpenJML/releases
#    (Download the latest openjml.zip or openjml.jar)
# 2. Extract openjml.jar to: /opt/openjml/openjml.jar
# ====================================================

OPENJML_JAR="/opt/openjml/openjml.jar"
SOURCE_FILE="src/main/java/com/sd26/encoder/service/CodecService.java"
CLASSPATH="target/classes:$HOME/.m2/repository/commons-codec/commons-codec/1.17.1/commons-codec-1.17.1.jar"

echo "============================================="
echo "Running OpenJML Static Checking (ESC Mode)"
echo "============================================="

if [ ! -f "$OPENJML_JAR" ]; then
    echo "[ERROR] OpenJML not found at $OPENJML_JAR"
    echo ""
    echo "Please download OpenJML from:"
    echo "https://github.com/OpenJML/OpenJML/releases"
    echo ""
    echo "Extract openjml.jar to /opt/openjml/openjml.jar"
    exit 1
fi

java -jar "$OPENJML_JAR" -esc -progress -spec-prec -classpath "$CLASSPATH" "$SOURCE_FILE"

echo ""
echo "============================================="
echo "Verification completed. Check output above."
echo "============================================="
