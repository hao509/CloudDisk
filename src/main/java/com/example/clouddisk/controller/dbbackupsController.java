package com.example.clouddisk.controller;

import com.example.clouddisk.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:faryhao
 * @create: 2023-05-04 21:41
 * @Description: 数据库备份
 */
@Slf4j
@RestController
@RequestMapping("/db")
public class dbbackupsController {
   // @RequestMapping(value = "/databasebackup",method = {RequestMethod.POST,RequestMethod.GET})


    @GetMapping("/database-backup")
    public void databaseBackup(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = "D:\\clouddisk1\\CloudDisk\\src\\main\\resources\\templates";
        String dbName = "clouddisk111";
        String fileName = dbName + new java.util.Date().getTime() + ".sql";
        String fullPath = filePath + File.separator + fileName;

        try {
            Process process = Runtime.getRuntime().exec(
                    "cmd  /c  mysqldump -u root -p666999 " + dbName + " > "
                            + fullPath);
            process.waitFor();
            // Check if the file exists and is readable
            File file = new File(fullPath);

            if (!file.exists() || !file.canRead()) {
                System.out.println("File not found or not readable: " + fullPath);
                response.setHeader("X-Backup-Status", "failed");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "备份失败");
                return;
            }

            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(file);
                log.info(String.valueOf(fis));

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

                // Adding custom HTTP header X-Backup-Status, representing success
                response.setHeader("X-Backup-Status", "success");

                os = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                }
            } finally {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            // Adding custom HTTP header X-Backup-Status, representing failure
            response.setHeader("X-Backup-Status", "failed");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "备份失败");
        }
    }
/**
*@Description:
*@Param: 获取备份文件
*@return: 
*@Author: faryhao
*@date: 2023/5/4
*/
    @GetMapping("/directory-contents")
    public ResponseEntity<List<Map<String, Object>>> getDirectoryContents(@RequestParam("path") String path) {
        File directory = new File(path);
        if (!directory.isDirectory()) {
            return ResponseEntity.badRequest().body(null);
        }

        File[] files = directory.listFiles();
        List<Map<String, Object>> contents = new ArrayList<>();

        for (File file : files) {
            Map<String, Object> fileInfo = new HashMap<>();
            fileInfo.put("name", file.getName());
            fileInfo.put("path", file.getPath());
            fileInfo.put("isDirectory", file.isDirectory());
            fileInfo.put("size", file.length());
            contents.add(fileInfo);
        }

        return ResponseEntity.ok(contents);
    }
}
