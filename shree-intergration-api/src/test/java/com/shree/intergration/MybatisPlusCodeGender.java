package com.shree.intergration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class MybatisPlusCodeGender {

    /**
     * @param args
     * @Title: main
     * @Description: 生成
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        /*************************************************************
         下面的路径十分关键，只向src/java目录就可以了
         ***************************************************************/
//        gc.setOutputDir("D://");//输出文件路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/shree-intergration-api/src/main/java");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(false);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("shree");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setEntityName("%s");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setIdType(IdType.ID_WORKER);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("shree");
        dsc.setPassword("shry7062955");
        dsc.setUrl("jdbc:mysql://rm-wz92o8n73a1825z5gjo.mysql.rds.aliyuncs.com/shree_mysql");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setSkipView(true);
        strategy.setRestControllerStyle(true);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // lombok
        strategy.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 字段常量
        strategy.setEntityColumnConstant(true);
        // strategy.setTablePrefix("t_wj_seats_");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        //strategy.setExclude("t_wj_base"); // 排除的表
        strategy.setEntityTableFieldAnnotationEnable(false);// 生成字段注解
        //strategy.setSuperEntityClass("wj.myshirotest.entity.BaseEntity"); // 实体父类
        //strategy.setSuperEntityColumns("id");//公共字段
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        /*************************************************************
         下面的包配置就是生成代码的位置
         ***************************************************************/
        pc.setParent("com.shree.intergration.model");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 这个是必要的,否则程序会报空指针异常 参见
        // com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.getObjectMap 的结尾
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        mpg.setCfg(cfg);
        // 执行生成
        mpg.execute();
    }

}