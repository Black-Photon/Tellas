# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.14

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/joseph/Documents/Programming/Graphics/OpenGLProject

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/OpenGLProject.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/OpenGLProject.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/OpenGLProject.dir/flags.make

CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.o: CMakeFiles/OpenGLProject.dir/flags.make
CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.o: ../out/production/OpenGLProject/jni_GLWrapper.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.o -c /home/joseph/Documents/Programming/Graphics/OpenGLProject/out/production/OpenGLProject/jni_GLWrapper.cpp

CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/joseph/Documents/Programming/Graphics/OpenGLProject/out/production/OpenGLProject/jni_GLWrapper.cpp > CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.i

CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/joseph/Documents/Programming/Graphics/OpenGLProject/out/production/OpenGLProject/jni_GLWrapper.cpp -o CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.s

CMakeFiles/OpenGLProject.dir/src/glad.c.o: CMakeFiles/OpenGLProject.dir/flags.make
CMakeFiles/OpenGLProject.dir/src/glad.c.o: ../src/glad.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/OpenGLProject.dir/src/glad.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/OpenGLProject.dir/src/glad.c.o   -c /home/joseph/Documents/Programming/Graphics/OpenGLProject/src/glad.c

CMakeFiles/OpenGLProject.dir/src/glad.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/OpenGLProject.dir/src/glad.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/joseph/Documents/Programming/Graphics/OpenGLProject/src/glad.c > CMakeFiles/OpenGLProject.dir/src/glad.c.i

CMakeFiles/OpenGLProject.dir/src/glad.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/OpenGLProject.dir/src/glad.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/joseph/Documents/Programming/Graphics/OpenGLProject/src/glad.c -o CMakeFiles/OpenGLProject.dir/src/glad.c.s

CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.o: CMakeFiles/OpenGLProject.dir/flags.make
CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.o: ../classes/Shader.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.o -c /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Shader.cpp

CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Shader.cpp > CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.i

CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Shader.cpp -o CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.s

CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.o: CMakeFiles/OpenGLProject.dir/flags.make
CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.o: ../classes/Shape.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.o -c /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Shape.cpp

CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Shape.cpp > CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.i

CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Shape.cpp -o CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.s

CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.o: CMakeFiles/OpenGLProject.dir/flags.make
CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.o: ../classes/Cube.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.o -c /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Cube.cpp

CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Cube.cpp > CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.i

CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Cube.cpp -o CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.s

CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.o: CMakeFiles/OpenGLProject.dir/flags.make
CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.o: ../classes/Camera.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.o -c /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Camera.cpp

CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Camera.cpp > CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.i

CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/joseph/Documents/Programming/Graphics/OpenGLProject/classes/Camera.cpp -o CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.s

# Object files for target OpenGLProject
OpenGLProject_OBJECTS = \
"CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.o" \
"CMakeFiles/OpenGLProject.dir/src/glad.c.o" \
"CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.o" \
"CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.o" \
"CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.o" \
"CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.o"

# External object files for target OpenGLProject
OpenGLProject_EXTERNAL_OBJECTS =

libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/out/production/OpenGLProject/jni_GLWrapper.cpp.o
libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/src/glad.c.o
libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/classes/Shader.cpp.o
libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/classes/Shape.cpp.o
libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/classes/Cube.cpp.o
libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/classes/Camera.cpp.o
libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/build.make
libOpenGLProject.so: include/glfw-3.2.1/src/libglfw3.a
libOpenGLProject.so: /usr/lib/librt.so
libOpenGLProject.so: /usr/lib/libm.so
libOpenGLProject.so: /usr/lib/libX11.so
libOpenGLProject.so: /usr/lib/libXrandr.so
libOpenGLProject.so: /usr/lib/libXinerama.so
libOpenGLProject.so: /usr/lib/libXxf86vm.so
libOpenGLProject.so: /usr/lib/libXcursor.so
libOpenGLProject.so: CMakeFiles/OpenGLProject.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Linking CXX shared library libOpenGLProject.so"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/OpenGLProject.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/OpenGLProject.dir/build: libOpenGLProject.so

.PHONY : CMakeFiles/OpenGLProject.dir/build

CMakeFiles/OpenGLProject.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/OpenGLProject.dir/cmake_clean.cmake
.PHONY : CMakeFiles/OpenGLProject.dir/clean

CMakeFiles/OpenGLProject.dir/depend:
	cd /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/joseph/Documents/Programming/Graphics/OpenGLProject /home/joseph/Documents/Programming/Graphics/OpenGLProject /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles/OpenGLProject.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/OpenGLProject.dir/depend

