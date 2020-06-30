package com.shree.intergration.common.base;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScaner {

    public static List<Class<?>> scanPackage(String packageName) {
        List<Class<?>> returnClassList = new ArrayList<>();
        // 获取当前包下以及子包下所以的类
        List<Class<?>> allClass = getClassesByPackageName(packageName);
        if (allClass != null) {
            returnClassList.addAll(allClass);
        }
        return returnClassList;
    }

    public static List<Class<?>> getClassesByPackageName(String packageName) {
        return getClassesByPackageName(packageName, true);
    }

    /**
     * 从指定 package 中获取所有的 Class, 并指定是否搜索 jar 文件
     *
     * @param packageName 被搜索的包名称
     * @param searchJar   是否搜索 jar 文件
     * @return
     */
    public static List<Class<?>> getClassesByPackageName(String packageName, boolean searchJar) {
        //第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //是否循环迭代
        boolean recursive = true;
        //获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        List<URL> urls = new ArrayList<URL>();
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
//                String protocol = url.getProtocol();
//                // 如果是 JBoss / wildfly 奇葩的协议.. 则转换为对应的地址
//                if ("vfs".equals(protocol)) {
//                    // 获取 JBoss 的 WebRoot 的路径
//                    String jBossWebRoot = AppContext.getInstance().getWebRoot();
//                    logger.debug("ClassFinder.getClasses JBOSS WebRoot: " + jBossWebRoot);
//                    int p = url.toString().indexOf("/WEB-INF");
//                    String realPath = url.toString().substring(p);
//                    if (url.toString().indexOf(".jar") > -1) {
//                        realPath = realPath.replaceAll(".jar", ".jar!");
//                        url = new URL("jar:file:" + jBossWebRoot + realPath);
//                    } else {
//                        url = new URL("file:" + jBossWebRoot + realPath);
//                    }
//                }
                urls.add(url);
            }
            for (URL url : urls) {
                //得到协议的名称
                String protocol = url.getProtocol();
                //如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    //以文件的方式扫描整个包下的文件 并添加到集合中
                    findClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol) || ("zip".equals(protocol) && url.toString().contains(".jar"))) {
                    if (searchJar) {
                        //如果是jar包文件
                        //定义一个JarFile
                        JarFile jar;
                        try {
                            //获取jar
                            if (("zip".equals(protocol)) && url.toString().contains(".jar")) {
                                int end = url.toString().indexOf(".jar");
                                String urlText = url.toString().substring(0, end + 4);
                                urlText = urlText.replaceAll("zip:", "");
                                jar = new JarFile(urlText);
                            } else {
                                jar = ((JarURLConnection) url.openConnection()).getJarFile();
                            }
                            //从此jar包 得到一个枚举类
                            Enumeration<JarEntry> entries = jar.entries();
                            //同样的进行循环迭代
                            findClassesByJarEntries(entries, packageName, classes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }


    public static void findClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {
        //获取此包的目录 建立一个File
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] files = dir.listFiles(new FileFilter() {
            //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        //循环所有文件
        for (File file : files) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                findClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void findClassesByJarEntries(Enumeration<JarEntry> entries, String packageName, List<Class<?>> classes) {
        while (entries.hasMoreElements()) {
            //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            //如果是以/开头的
            if (name.charAt(0) == '/') {
                //获取后面的字符串
                name = name.substring(1);
            }
            String packagePath = packageName.replace('.', '/');
            //如果前半部分和定义的包名相同, 并且是 .class 结尾
            if (name.startsWith(packagePath) && name.endsWith(".class")) {
                //去掉后面的".class" 获取真正的类名, 并将 / 转为 . 构建正确的类名
                String className = name.substring(0, name.length() - 6).replace('/', '.');
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
