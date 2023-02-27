#find agent source files
cd ./javaagent/src && find ./ -type f -name "*.java" > ./sources.txt

echo "1"

#compile javaagent
javac -d ../../javaagent/out/ @./sources.txt

cd ../..

echo "2"
#find agent compiled files
cd ./javaagent/out && find ./ -type f -name "*.class" > ../src/csources.txt

cd ../..

echo "3"

#make agent jar
cd ./javaagent/out && jar cmf ./../src/META-INF/MANIFEST.MF ../artifact/agent.jar @../src/csources.txt

cd ../..

echo "4"
# ----------------------#

#find app source files
cd ./test/src/ && find ./ -type f -name "*.java" > ./sources.txt

#compile app
javac -d ../out  @./sources.txt

echo "5"

#find app compiled files
cd ../out && find ./ -type f -name "*.class" > ../src/csources.txt

echo "6"

#run test with javaagent jar
ls
java  -javaagent:../../javaagent/artifact/agent.jar com.nsu.test.Main

echo "7"
