# 1. 前期准备工作
## 1.1 安装 JDK17
`jdk-17_windows-x64_bin.zip`
## 1.2 手动下载 Spring v6.0.6 版本对应的 gradle 版本 gradle-7.6-all.zip
## 1.3 修改 Spring 源码编译配置
（1）spring-framework-6.0.6\gradle\wrapper\gradle-wrapper.properties
    修改 distributionUrl 的值为本地下载好的 gradle-7.6-all.zip
（2）修改 spring-framework-6.0.6\build.gradle、spring-framework-6.0.6\setting.gradle、spring-framework-6.0.6\settings.gradle
    在 repositories 增加以下仓库代理地址
    maven {
            allowInsecureProtocol = true
            url 'http://maven.aliyun.com/nexus/content/groups/public'
        }
    maven { url 'https://maven.aliyun.com/repository/google' }
    maven { url 'https://maven.aliyun.com/repository/jcenter' }
    maven { url 'https://maven.aliyun.com/nexus/content/groups/public/'}
    maven { url 'https://maven.aliyun.com/nexus/content/repositories/jcenter'}
    maven { url 'https://dl.google.com/dl/android/maven2/' }


# 2. 编译
gradlew :spring-oxm:compileTestJava
