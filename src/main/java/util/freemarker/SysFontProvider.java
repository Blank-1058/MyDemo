package util.freemarker;

import com.itextpdf.html2pdf.LogMessageConstant;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.util.StreamUtil;
import com.itextpdf.layout.font.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * 字体加载类(用于itext由html转pdf时使用)
 *
 * @author liukang_lc on 2021/3/13
 */
public class SysFontProvider extends DefaultFontProvider {

    private static final Logger logger = LoggerFactory.getLogger(SysFontProvider.class);

    /**
     * 自定义字体的路径（放到resource目录下）
     */
    private static final String CUSTOM_FONT_PATH = "/freemarker/font/";

    /**
     * 要加载的字体
     */
    private static final String[] CUSTOM_FONTS_NAMES = new String[]{
            "STSONG.TTF"
    };

    public SysFontProvider() {
        // 加载默认的字体
        super();
        // 加载自定义的字体
        addAllCustomFonts();
    }

    /**
     * 加载所有的自定义字体
     */
    private void addAllCustomFonts(){
        Range rangeToLoad = addCalligraphFonts();
        for (String fontName : CUSTOM_FONTS_NAMES) {
            // 从工程resource目录下加载字体
            try (InputStream stream = SysFontProvider.class.getResourceAsStream(CUSTOM_FONT_PATH + fontName)) {
            // 从系统文件目录下加载字体
//            try (InputStream stream = new FileInputStream(CUSTOM_FONT_PATH + fontName)) {
                byte[] fontProgramBytes = StreamUtil.inputStreamToArray(stream);
                addFont(fontProgramBytes, null, rangeToLoad);
            } catch (Exception e) {
                logger.error(LogMessageConstant.ERROR_LOADING_FONT,e);
            }
        }
    }
}
