build:
	javac -d . *.java

run: build
	java com.game.of.life.GameOfLife
	
clean:
	find . -type f -name "*.class" -exec rm {} '+'
