package org.xt.client;

import org.xt.util.FileUtils;

import java.util.Properties;

/**
 * 描述：
 *
 * @author : xutao
 * @date : 2022-10-11 22:45
 */
public class App {
    public static void main(String[] args) {
        Properties properties = FileUtils.load("application.properties");
        System.out.println(properties.getProperty("pictures.directory"));
    }
}
