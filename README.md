# Tellas
Using concepts of OpenGL learned from OpenGLProject, I plan to create a Minecraft clone to improve my skills. This will need to be similar in basic respects, but can be somewhat different in its choices beyond that - Eg. I plan to include specific support for Portal-Like structures.

## Development
The development process consists of phases:

Phase 1: Pure C++ to create basic OpenGL (Completed)

Phase 2: Shift the workload onto a Java/Scala program which makes use of abstracted C++ (In Progress)

Phase 3: Use this to create a basic world that can be recognised as Minecraft

Phase 4: Using the structure now created, define new blocks to diversify the world

Phase 5: Return to C++ to improve on Graphical Aspects learning new specific features to improve the world and add lighting

Phase 6: Add support for inventories, items and GUI's in general

Phase 7: Extend to allow more types of blocks including models

Phase 8: Create an API to allow double-rendering for things like portals and mirrors

So far, work beyond Phase 8 has not been considered.

## Language Choice
It was decided to use such an interface between languages primarily to learn both OpenGL and Scala, but additionally because the speed gained by C++ and abstraction from Scala makes them a useful pair of languages to work together. By abstracting the common C++ tasks, you can retain the speed and flexibility of OpenGL in C++ while getting access to complex Data Structures in the Scala.

## Running the program
If you are attempting to run yourself, you will need to change the location in CMakeLists.txt to whereever you clone it. Other than that it should run fine out of the box, so long as you have Java, Scala and CMake installed. It is recommended to use IntelliJ to build as it is currently the only method of building the Java/Scala side of the project to my knowledge without editing paths.

The process for running should be:
1) IF any native definitions have changed:
   - Run jni.sh to update the .h file
2) IF any Java/Scala has been changed
   - Build the Java/Scala. You may need to specify the output folder as PROJECT/out in IntelliJ
3) Run build.sh to build the C++ and run the project

Unfortunately, as the project is run from the Scala side, it is impossible (to my knowledge) to run debugging on the C++ side. The Java side might be debugged by closing the project as soon as build.sh runs, and rerunning in a debugger. Otherwise, both cout and println are printed to the console, so debugging in this manner is always possible.
