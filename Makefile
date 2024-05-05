# Example de makefile pour compiler le squelette de code distribué
# Vous pouvez compléter ce makefile, mais étant donnée la taille du projet, 
# il est FORTEMENT recommandé d'utiliser un IDE!

# Organisation:
#  1) Les sources (*.java) se trouvent dans le répertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) sont générés dans le répertoire bin
#     La hiérarchie des sources (par package) est conservée.
#
#  3) Une librairie gui.jar est distribuée pour l'interface grapique. 
#     Elle se trouve dans le sous-répertoire lib.
#
# Compilation:
#  Options de javac:
#   -d : répertoire dans lequel sont générés les .class compilés
#   -sourcepath : répertoire dans lequel sont cherchés les .java
#   -classpath : répertoire dans lequel sont cherchées les classes compilées (.class et .jar)

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

