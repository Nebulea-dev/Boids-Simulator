all: runBoids



compileTestInvader:
	javac -d bin -classpath lib/gui.jar src/TestInvader.java

runTestInvader: compileTestInvader
	java -classpath bin:lib/gui.jar TestInvader



compileBoids:
	javac -d bin -classpath lib/gui.jar -sourcepath src/ src/test/boids/TestBoidsSimulator.java

runBoids: compileBoids
	java -classpath bin:lib/gui.jar test.boids.TestBoidsSimulator



compileBalls:
	javac -d bin -classpath lib/gui.jar -sourcepath src/ src/test/balls/TestBallsSimulator.java

runBalls: compileBalls
	java -classpath bin:lib/gui.jar test.balls.TestBallsSimulator



compileGameOfLife:
	javac -d bin -classpath lib/gui.jar -sourcepath src/ src/test/gameBoards/gameoflife/TestGameOfLifeSimulator.java

runGameOfLife: compileGameOfLife
	java -classpath bin:lib/gui.jar test.gameBoards.gameoflife.TestGameOfLifeSimulator



compileGameOfImmigration:
	javac -d bin -classpath lib/gui.jar -sourcepath src/ src/test/gameBoards/gameOfImmigration/TestGameOfImmigrationSimulator.java

runGameOfImmigration: compileGameOfImmigration
	java -classpath bin:lib/gui.jar test.gameBoards.gameOfImmigration.TestGameOfImmigrationSimulator



compileSchelling:
	javac -d bin -classpath lib/gui.jar -sourcepath src/ src/test/gameBoards/schelling/TestSchellingSimulator.java

runSchelling: compileSchelling
	java -classpath bin:lib/gui.jar test.gameBoards.schelling.TestSchellingSimulator




clean:
	rm -rf bin/

