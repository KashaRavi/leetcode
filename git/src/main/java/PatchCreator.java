package com.git;

import java.io.File;

/**
 * @author rkasha
 */


// /c/Users/rkasha/Desktop/create_diff.sh
public class PatchCreator {

    public static void main(String args[]) {

        try {
            String git = "C:\\Program Files (x86)\\GitTest\\bin\\git.exe";
            String patch_create_arg1 = "format-patch";
            String patch_create_arg2 = "master";
            String patch_create_arg3 = "--stdout";
            ProcessBuilder pb = new ProcessBuilder(git,patch_create_arg1,patch_create_arg2,patch_create_arg3);
            File dir = new File("D:\\borathon\\webserver");
            File log = new File("D:\\test.patch");
            pb.redirectOutput(log);
            pb.directory(dir);
            Process p = pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
