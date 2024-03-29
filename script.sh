#find agent source files
cd ./javaagent/src && find ./ -type f -name "*.java" > ./sources.txt

#compile javaagent
javac -cp ../artifact/javassist-3.29.2-GA.jar:../artifact/aspectjweaver-1.9.19.jar:../artifact/guava-31.1-jre.jar -d ../out/ @./sources.txt

#find agent compiled files
cd ../out && find ./ -type f -name "*.class" > ./csources.txt

#make agent jar
jar cmf ./../src/META-INF/MANIFEST.MF ../artifact/agent.jar @./csources.txt


# ----------------------#

#find app source files
cd ../../test/src/ && find ./ -type f -name "*.java" > ./sources.txt

#compile app
javac -cp ../../javaagent/artifact/agent.jar -d ../out  @./sources.txt

#find app compiled files
cd ../out && find ./ -type f -name "*.class" > ./csources.txt

# echo "
# ████─█─█─█──█────████─████─████────█───█─███─███─█──█──────██─████─█─█─████────████─████─███─█──█─███
# █──█─█─█─██─█────█──█─█──█─█──█────█───█──█───█──█──█───────█─█──█─█─█─█──█────█──█─█────█───██─█──█─
# ████─█─█─█─██────████─████─████────█─█─█──█───█──████───────█─████─█─█─████────████─█─██─███─█─██──█─
# █─█──█─█─█──█────█──█─█────█───────█████──█───█──█──█────█──█─█──█─███─█──█────█──█─█──█─█───█──█──█─
# █─█──███─█──█────█──█─█────█────────█─█──███──█──█──█────████─█──█──█──█──█────█──█─████─███─█──█──█─
# "

#run test with javaagent jar
java -javaagent:../../javaagent/artifact/agent.jar=package:com.nsu.test com.nsu.test.Main
