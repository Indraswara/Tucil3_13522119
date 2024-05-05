#!/bin/bash

# Set maximum heap size for Java (4GB in this case)
java_options="-Xmx4G"

# Classpath (-cp) setting to include the 'bin' directory
classpath="bin"

# Main class to run
main_class="src.Main"

# Construct the full command to run
command="java $java_options -cp $classpath $main_class"

# Run the Java command
eval "$command"
