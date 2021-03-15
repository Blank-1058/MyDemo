package util.freemarker;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import freemarker.template.TemplateException;
import util.fileopration.ImageUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * freeMarker主函数
 *
 * @author liukang_lc on 2021/3/15
 */
public class FreeMarkerTest {

    public static void main(String[] args) {

        try {
            /******************** 生成word **********************/
            byte[] wordBytes = buildWord();
            // 根据业务需要，可以保存到本地或者将byte数组以流的方式返回
            String wordFilePath = System.getProperty("user.dir")+File.separator+"docTest.doc";
            File docFile=new File(wordFilePath);
            FileOutputStream docFileOutputStream=new FileOutputStream(docFile);
            docFileOutputStream.write(wordBytes);
            docFileOutputStream.flush();
            docFileOutputStream.close();
            System.out.println("生成word完毕，保存路径："+wordFilePath);

            /*************** 生成pdf ******************/
            byte[] pdfBytes = buildPdf();
            // 根据业务需要，可以保存到本地或者将byte数组以流的方式返回
            String pdfFilePath = System.getProperty("user.dir")+File.separator+"pdfTest.pdf";

            ConverterProperties properties=new ConverterProperties();
            // 加载字体
            SysFontProvider fontProvider=new SysFontProvider();
            properties.setFontProvider(fontProvider);
            // 将html转pdf并写入到流
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(new ByteArrayInputStream(pdfBytes),outputStream,properties);

            // 将流写入到文件
            File pdfFile=new File(pdfFilePath);
            FileOutputStream pdfFileOutputStream=new FileOutputStream(pdfFile);
            pdfFileOutputStream.write(outputStream.toByteArray());
            pdfFileOutputStream.flush();
            pdfFileOutputStream.close();
            outputStream.close();
            System.out.println("生成pdf完毕，保存路径："+pdfFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    /**
     * 报名表模板文件-word
     */
    private final static String WORD_TEMPLATE_NAME = "doc_template.ftl";
    /**
     * 生成word
     * @return
     */
    private static byte[] buildWord() throws IOException, TemplateException {
        // 1、获取数据
        Map<String,Object> dataMap=getDataMap();

        // 2、使用工具类生成word文档
        byte[] bytes = FreeMarkerUtil.generateWord("/freemarker/template/", WORD_TEMPLATE_NAME,dataMap);

        return bytes;
    }

    /**
     * 报名表模板文件-pdf
     */
    private final static String PDF_TEMPLATE_NAME = "pdf_template.ftl";

    /**
     * 生成pdf
     * @return
     */
    private static byte[] buildPdf() throws IOException, TemplateException {
        // 1、获取数据
        Map<String,Object> dataMap=getDataMap();
        // 因为模板是html，如果数据中有base64编码的图片的话，需要对图片加上base64的标识
        String imageBase64 = (String) dataMap.get("photo");
        imageBase64="data:image/png;base64,"+imageBase64;
        dataMap.put("photo",imageBase64);

        // 2、使用工具类生成pdf文档
        byte[] bytes = FreeMarkerUtil.generatePdf("/freemarker/template/", PDF_TEMPLATE_NAME,dataMap);

        return bytes;
    }

    /**
     * 准备数据
     * @return
     * @throws IOException
     */
    private static Map<String,Object> getDataMap() throws IOException {
        Map<String,Object> dataMap = new HashMap<>(16);
        dataMap.put("name","空白");
        dataMap.put("gender","男");
        dataMap.put("age","18");
        dataMap.put("birthday","2008.01");
        dataMap.put("nation","汉");
        dataMap.put("local","籍贯");
        dataMap.put("political","群众");
        dataMap.put("health","健康");
        dataMap.put("marriage","未婚");
        dataMap.put("position","能吃");
        dataMap.put("record","1、高中生活\n2、大学生活\n3、硕士生活\n4、工作生活");

        // 文档中需要图片的话要将图片转成base64编码
        InputStream inputStream= FreeMarkerTest.class.getResourceAsStream("/freemarker/template/test.jpg");

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int n=0;
        while(-1 !=(n=inputStream.read(buffer))){
            byteArrayOutputStream.write(buffer,0,n);
        }
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String base64 = ImageUtil.byte2Base64WithoutSign(imageBytes);
        dataMap.put("photo",base64);

        return dataMap;
    }
}
