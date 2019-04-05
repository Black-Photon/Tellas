import jni.Utilities;

import java.nio.file.FileSystems;

public class Main {
    public static void main(String[] args) {
        System.load(
                    FileSystems.getDefault()
                            .getPath("../build/libOpenGLProject.so")  // Dynamic link
                            .normalize().toAbsolutePath().toString());
        
        Utilities u = new Utilities();

        System.out.println(u.getValue());

        u.run();
        
    }
}