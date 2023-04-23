package com.example.clouddisk.utils;

import java.io.File;

/**
 * @author:faryhao
 * @create: 2023-04-11 20:27
 * @Description: 控制文件上传时同名文件不覆盖
 */
public class FileNoOverwrite {
    /**
     * 获取文件夹中相同文件名的文件个数
     *
     * @param filePath
     * @param fileName
     * @return
     */
    private static int getFileMax(String filePath, String fileName) {
        File file = new File(filePath);
        File[] files;
        int number = 0;
        if (file.isDirectory()) {
            files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isFile()
                        && (f.getName().substring(0, f.getName().lastIndexOf("."))
                        .contains(fileName.substring(0, fileName.lastIndexOf(".")))
                        && f.getName().substring(f.getName().lastIndexOf("."))
                        .equals(fileName.substring(fileName.lastIndexOf("."))))) {
                    number = number + 1;
                }
            }
        }
        return number;
    }

    /**
     * 校验文件名称相同，若相同后缀(+n)
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return 新文件名称
     */
    public static String checkFileName(String filePath, String fileName) {
        int fileMax = getFileMax(filePath, fileName);
        //重复的文件名
        StringBuilder newFileName = new StringBuilder();
        if (fileMax > 0) {
            newFileName.append(fileName.substring(0, fileName.lastIndexOf("."))).
                    append("(" + fileMax + ")").append(fileName.substring(fileName.lastIndexOf(".")));
        } else {
            newFileName.append(fileName);
        }
        return newFileName.toString();
    }

}
