build:
	javac -d . *.java

run: build
	java com.java.hello.world.HelloWorldSwing
	
clean:
	find . -type f -name "*.class" -exec rm {} '+'
