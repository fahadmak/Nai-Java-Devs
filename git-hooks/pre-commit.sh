echo "Running static analysis..."

## Run checkstyle to check the code quality.
./gradlew check --daemon

# Store the last exit code in a variable.
checkResult=$?

# Perform checks
if [ $checkResult -ne 0 ]
then
    echo "Code violations were found, fix them to proceed with the commit"
    exit 1
fi

# You can commit
exit 0
