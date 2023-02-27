cd ./javaagent/src && javac com/nsu/aop/**/*.java


jar cmf ./META-INF/MANIFEST.MF ../artifact/agent.jar com/nsu/aop/*.class

cd ../../test/src && javac com/nsu/test/*.java

cd ../../test/src && java -javaagent:./../../javaagent/artifact/agent.jar com.nsu.test.Main