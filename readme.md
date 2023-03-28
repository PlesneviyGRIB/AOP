# AOP tool
AOP tool for Java. It is designed to implement aspect-oriented programming in Java without using AspectJ or Spring.
AOP tool is based on mutating class methods using Javassist. Mutation is fully automated, it is only necessary to pass the name of the package in which the classes are located.
The JAR that needs to be passed to javaagent, as well as an example of an application using the AOP tool, can be found in the ```finalVersion``` branch. 

##### Supported pointcuts designators:
* execution
* call
* within
* cflow

##### Supported advice types:
* Before
* Around 
* After
* Finally
* AfterThrowing

### Usage example
##### Basic:
* ```Around``` advice
    ```java
    @Around("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static Object loggingAdvice10(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        System.out.println("AROUND 2");

        proceedingJoinPoint.getExecutedObject();

        Object[] args = proceedingJoinPoint.getArgs();

        args[0] = (Integer)args[0] + 10;

        return proceedingJoinPoint.invoke(args);
    }
    ```
* ```Before```, ```After```, ```Finally``` advice
  ```java
    @Before("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static void loggingAdvice1(){
        System.out.println("Before execution");
    }
  ```
##### Cflow and method reference examples:
***You need to annotate classes with ```@PointcutPool``` or ```@Aspect``` annotations.***
###### CFlow example
  ```java
@PointcutPool
class Aspects{
    @Cflow("execution(* *(..))")
    public void method(){}
}

@Aspect  
class MyClass{
    @After(Aspects.method)
    public static void loggingAdvice1(){
        System.out.println("Before execution");
    }  
}
  ```

###### Method reference
  ```java
@PointcutPool
class Aspects{
    @PointCut("execution(* *(..))")
    public void method(){}
}

@Aspect  
class MyClass{
    @After(Aspects.method)
    public static void loggingAdvice1(){
        System.out.println("Before execution");
    }  
}
  ```
    

Also, you can download example application from ```finalVersion``` branch

#### Project setup
1. Download JAR
2. Run your app with the JAR passed to javaagent. Also, you must  specify your ```package``` as an option for javaagent.
   ```bash
   java -javaagent:agent.jar=package:<your_package> <your_main_class>
   ```

#### Start development
1. Create a directory and clone project.
   ```bash
   git remote add origin https://github.com/PlesneviyGRIB/AOP.git
   git clone origin
   ```
2. Create your own branch
   ```bash
    git checkout -b <branch_name>
   ```
3. Add or change files
4. Upload changes
   ```bash
    git add <files_changed>
    git commit -m "<commit message about changes>"
    git push origin <branch_name>
   ```
5. Open pull request on ```main``` branch.