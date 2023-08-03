package org.example;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.example.constant.WebUrl;
import org.example.dto.WebApiPic;
import org.example.util.FileUtil;
import org.example.util.HttpUtils;
import org.example.util.JacksonUtil;
import org.example.util.OkHttpUtils;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 描述：图像测试
 *
 * @author xutao
 * @Date 2023-03-09 22:35:33
 */
@Slf4j
public class ImageTest {

    @Test
    public void readBytes() throws MalformedURLException {
        String path = "C:\\Users\\Dell\\Pictures\\bg\\1.jpg";
        byte[] bytes = cn.hutool.core.io.FileUtil.readBytes(path);

        FileUtil.saveImage("C:\\Users\\Dell\\Pictures\\bg\\2.png", bytes);

        System.out.println(bytes.length);
    }

    @Test
    public void downloadImage() {
        String path = "http://www.65luchu.com/data/attachment/forum/202303/09/093752pwqswchxz5wqh9sj.jpg";
        HttpUtils.downloadImage(path, "png", new File("C:\\Users\\Dell\\Pictures\\bg\\3.jpg"), null);
    }

    @Test
    public void t2() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("sort", "pc");
        params.put("type", "json");
        params.put("num", "100");

        Response response = OkHttpUtils.get(WebUrl.PICTURE_PC, params);
        int code = response.code();
        log.info("code is: {}", code);

        ResponseBody body = response.body();
        WebApiPic webApiPic = JacksonUtil.fromJSON(body.string(), WebApiPic.class);
        List<String> pic = webApiPic.getPic();
        for (int i = 0; i < pic.size(); i++) {
            HttpUtils.downloadImage(pic.get(i), "png", FileUtils.getFile("G:\\pics\\r_" + i + ".png"), null);
        }

    }

    @Test
    public void t1() throws Exception {
        String picDir = "G:\\pics\\";
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1501; i < 1600; i++) {
            try {
                ResponseEntity<byte[]> responseEntity =
                    restTemplate.exchange(WebUrl.RANDOM_PICTURE, HttpMethod.GET, null, byte[].class);
                if (responseEntity.getStatusCodeValue() == 200) {
                    byte[] body = responseEntity.getBody();
                    String imagePath = picDir + i + ".jpg";
                    HttpUtils.saveImage(imagePath, body);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                log.error("download image exception, err msg: {}", e.getMessage());
            }
        }
    }
}
