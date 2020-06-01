package com.example.dataimport.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
public class UploadUtil {
    private static Session session;
    private static ChannelSftp channelSftp;

    private static final String HOST = "120.76.152.182";
    private static final Integer PORT = 22;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Cy123456666";
    /**
     * 获取sftp连接
     */
    public static ChannelSftp getSFTPClient() {
        //开始时间  用于计时
        long startTime = System.currentTimeMillis();
        JSch jsch = new JSch();// 创建JSch对象
        Channel channel = null;
        try {
            //根据用户名，主机ip，端口获取一个Session对象
            session = jsch.getSession(USERNAME, HOST,PORT);
            session.setPassword(PASSWORD); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            //session.setTimeout(timeout); // 设置timeout时间
            session.connect(); // 通过Session建立链接
            channel = session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
            long endTime = System.currentTimeMillis();
            log.info("连接sftp成功耗时" + (endTime - startTime) + "毫秒");
            return (ChannelSftp) channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Description //判断目录是否存在
     * @Param [directory, sftp]
     * @return boolean
     **/
    public static boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            log.info("目录"+directory+"已存在");
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                log.info("目录"+directory+"不存在");
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }
    /**
     * @Description //创建目录
     * @Param [createpath, sftp]
     * @return void
     **/
    public static void createDir(String createpath, ChannelSftp sftp) {
        try {
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString(),sftp)) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    log.info("创建目录"+filePath.toString()+"成功");
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                    log.info("进入目录"+filePath.toString());
                }
            }
            sftp.cd(createpath);
        } catch (SftpException e) {
//            throw new SystemException("创建路径错误：" + createpath);
        }
    }
    /**
     * @Description //关闭链接资源
     * @Param []
     * @return void
     **/
    public static void close() {
        if (channelSftp != null && channelSftp.isConnected()) {
            channelSftp.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        log.info("关闭连接资源");
    }
    /**
     * @Description //上传文件
     * @Param [host:上传主机IP, port:端口22, userName:登录名, password:密码
     *   remoteFile:上传路径,in:输入流格式文件, pathName:文件名称]
     * @return boolean
     **/
    public static boolean uploadFile(String remoteFile, InputStream in, String pathName){
        try{
            //建立连接
            if (channelSftp == null || !channelSftp.isConnected()) {
                channelSftp=getSFTPClient();
            }
            //判断文件夹是否存在，不存在则创建
            if (isDirExist(remoteFile,channelSftp)) {
                channelSftp.put(in, remoteFile + "/" +pathName);
                log.info("文件上传成功,文件路径"+remoteFile + "/" +pathName);
                return true;
            } else {
                //创建文件夹在上传文件
                createDir(remoteFile ,channelSftp);
                channelSftp.put(in, remoteFile + "/" +pathName);
                log.info("文件上传成功,文件路径"+remoteFile + "/" +pathName);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
        return false;

    }

    /**
     * 文件夹上传
     * @param host sftp地址
     * @param port sftp端口
     * @param userName 登录名
     * @param password 密码
     * @param file 上传的文件夹
     * @param remoteFile sftp服务器文件存放路径
     * @return true 成功,false 失败
     */
   /* public static boolean uploadDstFile(String host, int port, String userName, String password,
                                        MultipartFile[] file, String remoteFile){
        boolean b = false;
        //获取连接
        channelSftp = getConn(userName,password,host,port);
        if (isConnected()) {
            b = uploadFiles(file,remoteFile);//循环迭代文件夹
            close();//关闭连接
        }
        return b;
    }*/

    /**
     * 循环读取文件夹里面的文件上传，不支持文件夹里面嵌套文件夹上传
     * @param file  文件
     * @param remoteFile 服务器路径
     * @return
     */
    private static  boolean uploadFiles(MultipartFile[] file, String remoteFile) {
        try {
            for (int i = 0; i < file.length; i++) {
                InputStream in=null;
                in = file[i].getInputStream();
                if(i==0){//获取设置文件夹名称
                    remoteFile=remoteFile+"/"+file[i].getOriginalFilename().substring(0,file[i].getOriginalFilename().lastIndexOf("/"));
                }
                //获取设置文件名称
                String originalFilename = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("/"));
                //判断文件夹是否存在，存在则直接上传
                if (isDirExist(remoteFile, channelSftp)) {
                    channelSftp.put(in, remoteFile+"/"+originalFilename);
                    log.info("文件上传成功,文件路径" + remoteFile+"/"+originalFilename);
                } else {
                    //创建文件夹在上传文件
                    createDir(remoteFile, channelSftp);
                    channelSftp.put(in,  remoteFile+"/"+originalFilename);
                    log.info("文件上传成功,文件路径"+ remoteFile+"/"+originalFilename);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        close();
        return true;
    }

    /**
          * 获取文件大小
          * 
          * @param size
          * @return
          */
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
        // 因为如果以MB为单位的话，要保留最后1位小数，
        // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

}

