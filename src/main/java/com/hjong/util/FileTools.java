package com.hjong.util;

import com.hjong.entity.File;
import com.hjong.entity.FileException;
import com.hjong.entity.PaidFiles;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.Objects;


@Component
public class FileTools{

    /**
     * 存储文件到系统
     */
    public File storeFile(MultipartFile file, String path) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();

        try {
            if(fileName.contains("..")) {
                throw new FileException("文件路径出错...." + fileName);
            }
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            File newFile = new File();
            newFile.setFileName(fileName);
            newFile.setFileSize(this.transforSize(file.getSize()));
            newFile.setFileType(this.transforType(fileName));
            newFile.setFilePath(path + "/" + fileName);
            return newFile;
        } catch (IOException ex) {
            throw new FileException("存储文件失败 ...." + fileName + "请重试");
        }
    }

    public PaidFiles createFile(MultipartFile file, String path) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();
        try {
            if(fileName.contains("..")) {
                throw new FileException("文件路径出错...." + fileName);
            }
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            PaidFiles newFile = new PaidFiles();
            newFile.setFileName(fileName);
            newFile.setFileSize(this.transforSize(file.getSize()));
            newFile.setFileType(this.transforType(fileName));
            newFile.setFilePath(path + "/" + fileName);

            return newFile;
        } catch (IOException ex) {
            throw new FileException("存储文件失败 ...." + fileName + "请重试");
        }
    }

    /**
     * 加载文件
     **/
    public Resource loadFileAsResource(String file_path) {
        try {
            Path filePath = Paths.get(file_path).toAbsolutePath().normalize();
            String fileName = filePath.getFileName().toString();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("文件不存在 " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("文件找不到 ...." + ex);
        }
    }

    /**
     * 根据文件后缀判断文件类型
     */
    public String transforType(String fileName) {
        if(fileName == null){
            fileName = "emptyFileName";
            return fileName;
        }else {
            //获取文件后缀名并转化为小写，用于后续比较
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            //创建图片类型数组
            String[] img = { "bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                    "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf" };
            for (String s : img) {
                if (s.equals(fileType)) {
                    return "image";
                }
            }
            //创建文档类型数组
            String[] document = { "txt", "doc", "docx", "xls","xlsx", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};
            for (String s : document) {
                if (s.equals(fileType)) {
                    return "documents";
                }
            }
            // 创建视频类型数组
            String[] video = { "mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm" };
            for (String s : video) {
                if (s.equals(fileType)) {
                    return "video";
                }
            }
            // 创建音频类型数组
            String[] music = { "mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf" };
            for (String s : music) {
                if (s.equals(fileType)) {
                    return "audio";
                }
            }
        }
        return "other";
    }

    /**
     * 根据文件字节大小，转换成相应的B,KB,MB,GB
     * 文件的字节大小
     * @return 转换单位后的文件大小
     */
    public String transforSize(long size) {
        //获取到的size bites，表示字节大小
        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
        int MB = 1024 * 1024;//定义MB的计算常量
        int KB = 1024;//定义KB的计算常量
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B";
        }
        return resultSize;
    }
    /**
     * 读取文本文件，返回一个字符串
     * @param 文件路径
     * @return 文本内容
     */
//    public static String readText(String fileName) {
//        StringBuffer stringList = new StringBuffer();
//        System.out.println(fileName);
//        try {
//            File file = new File(fileName);
//            if (!file.exists()) {
//                System.out.println("该文件不存在！");
//                return stringList.toString();
//            }
//            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
//            BufferedReader in = new BufferedReader(isr);
//            String str;
//            while ((str = in.readLine()) != null) {
//                //str = str.replace(" ", "&nbsp");
//                stringList.append(str+"\n");//为了在前端正常显示，加上换行和空格转换符号,如果需要在后端显示，则去掉，注意：前端如果用innerText
//                //显示的话，则不需要在后端加
//            }
//            in.close();
//        } catch (IOException e) {
//            System.out.println("文件为空，读取出错");
//        }
//        return stringList.toString();
//    }

    /**
     * 从文件中按行读取，返回List<String>
     * @param 文件路径
     * @return 文本内容List<String>
     */
//    public static List<String> readStringList(String fileName){
//        List<String> stringList = new ArrayList<String>();
//        try {
//            File file = new File(fileName);
//            if (!file.exists()) {
//                System.out.println("该文件不存在！");
//                return stringList;
//            }
//            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
//            BufferedReader in = new BufferedReader(isr);
//            String str;
//            while ((str = in.readLine()) != null) {
//                stringList.add(str);
//            }
//            in.close();
//        } catch (IOException e) {
//            System.out.println("文件为空，读取出错");
//        }
//        return stringList;
//    }

    /**
     * 判断哪些是可以预览，也就是读取的文本文件
     * @param 文件路径
     * @return 是否可以读取
     */
//    public static boolean canPreView(String fileName) {
//        //获取文件后缀名并转化为小写，用于后续比较
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
//        // 创建可以预览文本类型数组
//        String canPreviewArray[] = { "txt", "java", "xml", "py","lrc","sql","html","jsp"};
//        for (int i = 0; i < canPreviewArray.length; i++) {
//            if (canPreviewArray[i].equals(fileType)) {
//                return true;
//            }
//        }
//        return false;
//    }

}
