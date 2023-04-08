package org.xt;

import org.junit.Assert;
import org.junit.Test;
import org.xt.util.HttpUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author : xutao
 * @date : 2022-10-11 21:33
 */
public class FileTest {
    private static final String IMAGE_PATH = "G:/images/";

    /**
     * 测试携带请求头
     */
    @Test
    public void testDownloadImageWithHeaders() {
        String baiduLogoUrl = "http://pic.58pic.com/58pic/15/68/59/71X58PICNjx_1024.jpg";
        File localFile = new File(IMAGE_PATH + "scenery.jpg");
        Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//		headers.put("Accept-Encoding", "gzip, deflate");
//		headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
//		headers.put("Cache-Control", "max-age=0");
//		headers.put("Connection", "keep-alive");
//		headers.put("Cookie", "referer=%22https%3A%5C%2F%5C%2Fwww.58pic.com%5C%2Ftupian%5C%2FBMP.html%22; qt_visitor_id=%228d800369135f0ea407efc30c35b855c0%22; qt_uid=0; qt_type=0; user-browser=%22baidu%22; history_search=%22%7B%5C%22BMP_%5C%22%3A%5C%22%5C%5C%5C%2Ftupian%5C%5C%5C%2FBMP.html%5C%22%7D%22; awake=0; qiantudata2018jssdkcross=%7B%22distinct_id%22%3A%2216956b4d4b859f-0f2dc330b57f7c-3a3a5c0e-2073600-16956b4d4b92bd%22%2C%22props%22%3A%7B%22latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22latest_referrer%22%3A%22https%3A%2F%2Fwww.baidu.com%2Flink%22%2C%22latest_referrer_host%22%3A%22www.baidu.com%22%2C%22latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D; qt_utime=1551937913; FIRSTVISITED=1551937885.528; showAd:8d800369135f0ea407efc30c35b855c0=%22w6SIEgLKiJOIC5HVD3fKoJHKodaWmZy8mtm4zJbLytqWn5vMyZmWyZm4yJG4nwmWiIWIywr5zxj3AxnLCL2Pzci9iJeIlcj3DxjUiJOImsiSiNnOB6DFDgLTzxmIoJmSiMXHC6rFC5HVD423Aw4LiJOXntuXotm6ote3Fv3%3D%22");
//		headers.put("Host", "pic.58pic.com");
//		headers.put("If-Modified-Since", "Tue, 22 Jul 2014 15:40:16 GMT");
//		headers.put("If-None-Match", "ae01753237194fa9a5badf2d90fd20d6");
        headers.put("Referer", "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=0&spn=0&di=107250&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1986179278%2C1118313821&os=1011800134%2C3605107432&simid=3440756675%2C361207036&adpicid=0&lpn=0&ln=1765&fr=&fmq=1552355383195_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F68%2F59%2F71X58PICNjx_1024.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bcbrtv_z%26e3Bv54AzdH3Ffitstwg2p7AzdH3F8cmbcl08_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
//		headers.put("Upgrade-Insecure-Requests", "1");
//		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        Assert.assertTrue(HttpUtils.downloadImageWithHeaders(baiduLogoUrl, ".jpg", localFile, headers));
    }
}
