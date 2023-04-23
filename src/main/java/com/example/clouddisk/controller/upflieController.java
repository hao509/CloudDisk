package com.example.clouddisk.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.clouddisk.common.Result;
import com.example.clouddisk.dto.DownDto;
import com.example.clouddisk.entity.Doc;
import com.example.clouddisk.entity.Docshare;
import com.example.clouddisk.entity.User;

import com.example.clouddisk.service.DocService;
import com.example.clouddisk.service.DocfakeService;
import com.example.clouddisk.service.DocshareService;
import com.example.clouddisk.service.UserService;
import com.example.clouddisk.utils.FileNoOverwrite;
import com.example.clouddisk.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static java.lang.System.in;
import static org.apache.tomcat.util.http.fileupload.util.Streams.checkFileName;

/**
 * @author:faryhao
 * @create: 2023-04-06 15:33
 * @Description: file上传
 */
@Slf4j
@RestController
@RequestMapping("/updata")
public class upflieController {


    @Autowired
    UserService userService;
    @Autowired
    DocService docService;

    @Autowired
    DocfakeService docfakeService;

    @Autowired
    DocshareService docshareService;
//根目录
    private static final String BASE_DIR = "D:\\上传\\";

/**
*@Description:
*@Param: 文件 用户id
*@return: 上传成功失败信息
*@Author: faryhao
*@date: 2023/4/10
*/
    @PostMapping("/userupdata")
    public Result updata(@RequestParam MultipartFile file, HttpServletRequest request) {
        Doc doc = new Doc();

        String fileName = file.getOriginalFilename();
        String authorization = request.getHeader("token");
        log.info(authorization);
        String userDir = BASE_DIR + authorization;
        String NewFileName = FileNoOverwrite.checkFileName(userDir, fileName);
        // 新建一个文件路径
        File uploadFile = new File(userDir, NewFileName);
        // 当父级目录不存在时，自动创建
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }
        // 存储文件到电脑磁盘
        doc.setDocfakename(NewFileName);
        doc.setDocowner(authorization);
        doc.setDocsize(String.valueOf(file.getSize()));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", authorization);
        User user = userService.getOne(wrapper);
        String userspace = user.getUserspace();
        String sumSize = docService.getSumSize(authorization);
        log.info(user.toString());
        log.info(String.valueOf(sumSize));
        String getdocsize = doc.getDocsize();
        log.info(getdocsize);
       if((getdocsize+sumSize).compareTo(userspace)<=0) {
           try {
               file.transferTo(uploadFile);
               boolean i = docService.save(doc);
               log.info(String.valueOf(i));
               return Result.ok("上传成功");

           } catch (IOException e) {
               e.printStackTrace();
               return Result.error("请重新选择上传文件");
           }

       }else{
           return Result.error("用户空间不足，请联系管理员");
       }
    }
/**
*@Description:
*@Param: 文件/文件名
*@return: 下载链接
*@Author: faryhao
*@date: 2023/4/10
*/
@PostMapping("/download")
public Result download(@RequestBody DownDto downDto, HttpServletResponse response) throws IOException {
   // String QQ = token;
    Integer docid = downDto.getDocid();
    String token = downDto.getToken();
    if(docid!=null) {
         // Integer  i = Integer.valueOf(docid);
           QueryWrapper<Doc> wrapper = new QueryWrapper<>();
           wrapper.eq("docid", docid);
           Doc result = docService.getOne(wrapper);
        if(result!=null){
            String filename = result.getDocfakename();
            log.info(String.valueOf(result)+"doc");
            String owner = result.getDocowner();
            String fileDir =  BASE_DIR+owner;
            File file = new File(fileDir+"\\"+filename);
            log.info(String.valueOf(file));
            if(file.exists()){
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStream inputStream = new BufferedInputStream(fileInputStream);
                response.reset();

// 根据文件扩展名设置MIME类型
                String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
                switch (extension) {
                    case "png":
                        response.setContentType("image/png");
                        break;
                    case "jpeg":
                    case "jpg":
                        response.setContentType("image/jpeg");
                        break;
                    case "docx":
                        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                        break;
                    // 如果需要支持其他文件类型，可在此处添加其他case
                    default:
                        response.setContentType("application/octet-stream");
                }

                //response.setCharacterEncoding("UTF-8");
                response.setContentLength((int) file.length());

                response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8"));

                ServletOutputStream os = response.getOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }

                fileInputStream.close();
                inputStream.close();
                os.flush();
                os.close();
                return Result.ok(filename);
            }else {
                return Result.error("shibai");
            }



        }else{
            return Result.error("文件不存在");

        }




    }
    return Result.error("提取错误");
}



    /**
    *@Description:
    *@Param: 页数，一页多少个，文件拥有者
    *@return: 分页
    *@Author: faryhao
    *@date: 2023/4/10
    */
    @GetMapping("/filelist")

    public Result filelist(Integer page, Integer pagesize,String name){
        //fenye gouzhao
        try {
            Page<Doc> listInfo = new Page<>(page,pagesize);
            LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
            //tiaojian
            queryWrapper.like(name!=null,Doc::getDocowner,name);
            //baixutiaojian
            queryWrapper.orderByDesc(Doc::getDocsize);
            docService.page(listInfo,queryWrapper);
            return Result.ok(listInfo);

        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询失败");
        }

    }

    /**
    *@Description:
    *@Param: docid
    *@return: Result
    *@Author: faryhao
    *@date: 2023/4/11
    */
    @GetMapping("/filedelete")
    public Result filedelete(@RequestParam Integer docid){
        try {
            String owner = SessionUtil.getsession();
            String userDir = BASE_DIR + owner;
            QueryWrapper<Doc> wrapper = new QueryWrapper<>();
            wrapper.eq("docid", docid);
            Doc doc = docService.getOne(wrapper);
            log.info(String.valueOf(doc)+666);
            String filename = doc.getDocfakename();
            if (owner.equals(doc.getDocowner())){
                log.info(userDir+filename);
                File file = new File(userDir+"\\"+filename);
                file.delete();
                docService.removeById(docid);
                return Result.ok("SHANCHUCHENGG");

            }else{
                return Result.error("删除失败");
            }
        } catch (Exception e){
            e.printStackTrace();
            return Result.error("删除失败");
        }
    }
    /**
    *@Description:
    *@Param: docid token
    *@return:加密后的url 分享动作
    *@Author: faryhao
    *@date: 2023/4/13
    */
    @PostMapping("/getshareurl")
    public Result getshareurl(@RequestParam Integer docid,String token){
        Docshare docshare = new Docshare();
        QueryWrapper<Doc> wrapper = new QueryWrapper<>();
        wrapper.eq("docid", docid);
        Doc doc = docService.getOne(wrapper);
        if(doc==null){
            return Result.ok("没有这个文件");
        }else{
            docshare.setDocid(doc.getDocid());
            docshare.setUserid(doc.getDocowner());
            docshare.setDocplace("\\"+doc.getDocfakename());
            docshareService.save(docshare);
            log.info(String.valueOf(docshare));
            String URL = docshare.getShareid()+"\\"+docshare.getDocplace();
            //加密url
            String encodedString = Base64.getEncoder().encodeToString(URL.getBytes());
            log.info(encodedString);
//            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//            String decodedString = new String(decodedBytes);
           // log.info(decodedString);
            return Result.ok(encodedString);
        }
    }
    @GetMapping("/sharelist")
    public Result sharelists(Integer page, Integer pagesize,String name){
        try {
            Page<Docshare> listInfo = new Page<>(page,pagesize);
            LambdaQueryWrapper<Docshare> queryWrapper = new LambdaQueryWrapper<>();
            //tiaojian
            queryWrapper.like(name!=null,Docshare::getUserid,name);
            //baixutiaojian
            queryWrapper.orderByDesc(Docshare::getShareid);
            docshareService.page(listInfo,queryWrapper);
            return Result.ok(listInfo);

        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询失败");
        }
    }
    /**
    *@Description:
    *@Param: shareid token
    *@return: 管理分享
    *@Author: faryhao
    *@date: 2023/4/13
    */
    @PostMapping("/shareurl")
    public Result shareurl(@RequestParam Integer shareid,String token) {

        QueryWrapper<Docshare> wrapper = new QueryWrapper<>();
        wrapper.eq("shareid", shareid);
        wrapper.eq("userid",token);
        Docshare docshare = docshareService.getOne(wrapper);
        if (docshare != null) {
            String Url = docshare.getShareid()+"\\"+docshare.getDocplace();
            log.info(Url);
            String encodedString = Base64.getEncoder().encodeToString(Url.getBytes());
            return Result.ok(encodedString);

        }else {
            return Result.error("参数错误");
        }
    }
    @PostMapping(value = "/getsharefile")
    public Result getsharefile(@RequestBody String str ,HttpServletResponse response) throws IOException {
       if(str!=null) {
           log.info(str);
           byte[] decodedBytes = Base64.getDecoder().decode(str);
           String decodedString = new String(decodedBytes);
           log.info(decodedString);

           int index = decodedString.indexOf("\\"); // 获取第一个反斜杠的位置
           String result = decodedString.substring(0, index); // 获取第一个反斜杠之前的子字符串
           log.info(result);
           QueryWrapper<Docshare> wrapper = new QueryWrapper<>();
           wrapper.eq("shareid", result);
           Docshare docshare = docshareService.getOne(wrapper);
           if(docshare!=null){
              int fileid =  docshare.getDocid();
              QueryWrapper<Doc> wrapper1 = new QueryWrapper<>();
              wrapper1.eq("docid",fileid);
              Doc doc = docService.getOne(wrapper1);
              String filename = doc.getDocfakename();
              log.info(String.valueOf(doc)+"doc");
              String owner = doc.getDocowner();
              String fileDir =  BASE_DIR+owner;
              File file = new File(fileDir+"\\"+filename);
               log.info(String.valueOf(file));
               if(file.exists()){
                   FileInputStream fileInputStream = new FileInputStream(file);
                   InputStream inputStream = new BufferedInputStream(fileInputStream);
                   response.reset();
                   response.setContentType("application/octet-stream");
                   response.setCharacterEncoding("UTF-8");
                   response.setContentLength((int) file.length());

                   byte[] buffer = new byte[inputStream.available()];

                   inputStream.read(buffer);
                   inputStream.close();
                   response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8"));
                   ServletOutputStream os = response.getOutputStream();
                   os.write(buffer);
                   fileInputStream.close();
                   os.flush();
                   os.close();
                   return Result.ok(filename);
               }else {
                   return Result.error("shibai");
               }



           }else{
               return Result.error("文件不存在");

           }




       }
       return Result.error("提取错误");
    }



}
