javadoc -d docs src/main/java/nsu/fit/vladimir/Main.java
javac -d out src/main/java/nsu/fit/vladimir/Main.java
jar cvf out/app.jar -C out .
java -cp out nsu.fit.vladimir.Main