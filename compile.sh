#!/bin/bash

if [ ! -d "bin" ]; then
    mkdir bin
fi

find src -name '*.java' | while read -r file; do
    javac -d bin "$file"
done

if [ $? -ne 0 ]; then
    echo "Compilation failed."
else
    echo "Compilation successful."
fi

read -p "Press Enter to exit..."
