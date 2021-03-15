package util.fileopration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * 图片处理工具类
 *
 * @author liukang_lc on 2020/3/25
 */
public class ImageUtil {

    private static Logger logger= LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 二进制转bufferImage
     * @param imageByte
     * @return
     */
    public static BufferedImage byteImage2BufferImage(byte[] imageByte){
        BufferedImage image=null;
        if(imageByte.length==0){
            logger.warn("二进制流图片为空");
        }
        try {
            ByteArrayInputStream inputStream=new ByteArrayInputStream(imageByte);
            image= ImageIO.read(inputStream);
            return image;
        } catch (IOException e) {
            logger.error("byte[]转bufferedImage失败",e);
            return null;
        }
    }

    /**
     * bufferImage转base64
     * @param image
     * @return
     */
    public static String image2Base64(BufferedImage image){
        Base64.Encoder encoder = Base64.getEncoder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            logger.error("图片转base64失败",e);
            return "";
        }
        return new String(encoder.encode((baos.toByteArray())));
    }

    /**
     * 二进制转base64
     * @param imageByte
     * @return
     */
    public static String byte2Base64(byte[] imageByte){
        if(imageByte.length==0){
            return "";
        }
        BufferedImage image=byteImage2BufferImage(imageByte);
        if(image==null){
            return "";
        }
        return "data:image/png;base64,"+image2Base64(image);
    }

    /**
     * 二进制转base64，返回的数据不带base64标识
     * @param imageByte
     * @return
     */
    public static String byte2Base64WithoutSign(byte[] imageByte){
        if(imageByte.length==0){
            return "";
        }
        BufferedImage image=byteImage2BufferImage(imageByte);
        if(image==null){
            return "";
        }
        return image2Base64(image);
    }
}
