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
include CMakeFiles/inc.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/inc.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/inc.dir/flags.make

CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.o: CMakeFiles/inc.dir/flags.make
CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.o: ../java/jni_GLWrapper.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.o -c /home/joseph/Documents/Programming/Graphics/OpenGLProject/java/jni_GLWrapper.cpp

CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/joseph/Documents/Programming/Graphics/OpenGLProject/java/jni_GLWrapper.cpp > CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.i

CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/joseph/Documents/Programming/Graphics/OpenGLProject/java/jni_GLWrapper.cpp -o CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.s

# Object files for target inc
inc_OBJECTS = \
"CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.o"

# External object files for target inc
inc_EXTERNAL_OBJECTS =

libinc.so: CMakeFiles/inc.dir/java/jni_GLWrapper.cpp.o
libinc.so: CMakeFiles/inc.dir/build.make
libinc.so: CMakeFiles/inc.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX shared library libinc.so"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/inc.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/inc.dir/build: libinc.so

.PHONY : CMakeFiles/inc.dir/build

CMakeFiles/inc.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/inc.dir/cmake_clean.cmake
.PHONY : CMakeFiles/inc.dir/clean

CMakeFiles/inc.dir/depend:
	cd /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/joseph/Documents/Programming/Graphics/OpenGLProject /home/joseph/Documents/Programming/Graphics/OpenGLProject /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug /home/joseph/Documents/Programming/Graphics/OpenGLProject/cmake-build-debug/CMakeFiles/inc.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/inc.dir/depend

