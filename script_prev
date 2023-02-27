#find agent source files
find ./javaagent/src/ -type f -name "*.java" > ./javaagent/sources.txt

#compile javaagent
javac -d ./javaagent/out/ @./javaagent/sources.txt

#find agent compiled files
find ./javaagent/out/ -type f -name "*.class" > ./javaagent/csources.txt

#make agent jar
jar cmf ./javaagent/src/META-INF/MANIFEST.MF ./javaagent/artifact/agent.jar @./javaagent/csources.txt

# ----------------------#

#find app source files
find ./test/src/ -type f -name "*.java" > ./test/sources.txt

#compile app
javac -d ./test/out -cp ./javaagent/agent/agent.jar @./test/sources.txt

#find app compiled files
find ./test/out/ -type f -name "*.class" > ./test/csources.txt

#run test with javaagent jar
java -cp ./javaagent/artifact/agent.jar -javaagent:./javaagent/artifact/agent.jar @./test/csources.txt