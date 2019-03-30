package cn.qiuzhizhushou.wechat.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class PathUtil
{
    public static String pathRoot;

    static {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) {
                path = new File("");
            }
            PathUtil.pathRoot = path.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileExist(String filePath)
    {
        File path = new File(filePath);
        return path.exists();
    }
}
