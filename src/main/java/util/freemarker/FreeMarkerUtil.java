package util.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * word文档生成类
 *
 * @author liukang_lc on 2021/3/10
 */
public class FreeMarkerUtil {

    private static Logger logger = LoggerFactory.getLogger(FreeMarkerUtil.class);

    /**
     * 生成word文档
     * @param templatePath 文档模板所在的文件夹
     * @param templateFileName 文档模板的名称
     * @param dataMap 模板中需要替换的数据
     * @param targetFilePath 目标文件路径
     * @param targetFileName 目标文件名
     * @return File
     * @throws IOException
     * @throws TemplateException
     */
    public static File generateWord(String templatePath, String templateFileName, Map<String,Object> dataMap,String targetFilePath,String targetFileName) throws IOException, TemplateException {
        byte[] wordBytes = createWord(templatePath, templateFileName, dataMap);
        File filePath = new File(targetFilePath);
        if(filePath.isDirectory() && !filePath.exists()){
            filePath.mkdirs();
        }
        File file = new File(targetFilePath+File.separator+targetFileName);
        FileOutputStream fOut = new FileOutputStream(file);
        fOut.write(wordBytes);
        fOut.close();
        return file;
    }

    /**
     * 生成word文档
     * @param templatePath 文档模板所在的文件夹
     * @param templateFileName 文档模板的名称
     * @param dataMap 模板中需要替换的数据
     * @return 二进制数组
     * @throws IOException
     * @throws TemplateException
     */
    public static byte[] generateWord(String templatePath, String templateFileName, Map<String,Object> dataMap) throws IOException, TemplateException {
        byte[] wordBytes = createWord(templatePath, templateFileName, dataMap);
        return wordBytes;
    }

    /**
     * 生成word文档
     * @param templatePath 文档模板所在的文件夹
     * @param templateFileName 文档模板的名称
     * @param dataMap 模板中需要替换的数据
     * @return 二进制数组
     */
    private static byte[] createWord(String templatePath, String templateFileName, Map<String,Object> dataMap) throws IOException, TemplateException {
        Configuration configuration = getConfiguration(templatePath);
        Template template = configuration.getTemplate(templateFileName);

        for(Map.Entry<String,Object> entry:dataMap.entrySet()){
            String value = entry.getValue().toString();
            // 处理转义字符
            value = transformForDoc(value);
            entry.setValue(value);
        }

        StringWriter stringWriter = new StringWriter();
        Writer out = new BufferedWriter(stringWriter);
        try {
            template.process(dataMap,out);
        } catch (TemplateException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }finally {
            out.close();
            stringWriter.close();
        }
        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 生成pdf文档
     * @param templatePath 文档模板所在的文件夹
     * @param templateFileName 文档模板的名称
     * @param dataMap 模板中需要替换的数据
     * @param targetFilePath 目标文件路径
     * @param targetFileName 目标文件名
     * @return File
     * @throws IOException
     * @throws TemplateException
     */
    public static File generatePdf(String templatePath, String templateFileName, Map<String,Object> dataMap,String targetFilePath,String targetFileName) throws IOException, TemplateException {
        byte[] pdfBytes = createPdf(templatePath, templateFileName, dataMap);
        File filePath = new File(targetFilePath);
        if(filePath.isDirectory() && !filePath.exists()){
            filePath.mkdirs();
        }
        File file = new File(targetFilePath+File.separator+targetFileName);
        FileOutputStream fOut = new FileOutputStream(file);
        fOut.write(pdfBytes);
        fOut.close();
        return file;
    }

    /**
     * 生成pdf文档
     * @param templatePath 文档模板所在的文件夹
     * @param templateFileName 文档模板的名称
     * @param dataMap 模板中需要替换的数据
     * @return 二进制数组
     * @throws IOException
     * @throws TemplateException
     */
    public static byte[] generatePdf(String templatePath, String templateFileName, Map<String,Object> dataMap) throws IOException, TemplateException {
        byte[] pdfBytes = createPdf(templatePath, templateFileName, dataMap);
        return pdfBytes;
    }

    /**
     * 生成pdf文档
     * @param templatePath 文档模板所在的文件夹
     * @param templateFileName 文档模板的名称
     * @param dataMap 模板中需要替换的数据
     * @return 二进制数组
     */
    private static byte[] createPdf(String templatePath, String templateFileName, Map<String,Object> dataMap) throws IOException, TemplateException {
        Configuration configuration = getConfiguration(templatePath);
        Template template = configuration.getTemplate(templateFileName);

        StringWriter stringWriter = new StringWriter();
        Writer out = new BufferedWriter(stringWriter);
        try {
            template.process(dataMap,out);
        } catch (TemplateException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }finally {
            out.close();
            stringWriter.close();
        }
        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }


    /**
     * 生成配置
     * @param templatePath
     * @return
     * @throws IOException
     */
    private static Configuration getConfiguration(String templatePath) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        // 模板文件在resource目录下
        configuration.setClassForTemplateLoading(FreeMarkerUtil.class,templatePath);
        // 模板文件在文件系统的目录下
//        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }

    /**
     * 处理转义字符(用于word生成)
     */
    private static String transformForDoc(String str) {
        if (str.contains("<") || str.contains(">") || str.contains("&")) {
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
        }
        // 处理换行符
        if(str.contains("\n")){
            str = str.replaceAll("\n","<w:br/>");
        }
        return str;
    }

}
