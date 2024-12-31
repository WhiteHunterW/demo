package com.example.biz.wordengineer;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.ChartMultiSeriesRenderData;
import com.deepoove.poi.data.Charts;
import com.deepoove.poi.data.Includes;
import com.deepoove.poi.data.NumberingFormat;
import com.deepoove.poi.data.Numberings;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.Texts;
import com.deepoove.poi.data.style.BorderStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * word 模版引擎示例
 * @author wenzeng
 * @date 2024/12/27
 */
public class XWPFTemplateTest {


    /**
     * 软件说明书模版
     * @throws IOException
     */
    public static void introduceTemplate() throws IOException {
        Map<String, Object> param = new HashMap<>(16);
        // 文本
        param.put("word", "模版引擎");
        param.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        param.put("header", "Deeply love what you love.");
        param.put("name", "Poi-tl");
        param.put("what", "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java.");
        // 带格式的文本
        param.put("author", Texts.of("Sayi-Poi").color("000000").create());
        // 超链接
        param.put("introduce", Texts.of("http://deepoove.com").link("http://deepoove.com").create());
        // 列表 设置列表编号格式
        param.put("feature",  Numberings.of(NumberingFormat.BULLET)
                        .addItem("Plug-in grammar")
                        .addItem("Supports word text, pictures, table...")
                        .addItem("Not just templates")
                .create());
        // 图片 设置图片大小
        param.put("portrait", Pictures.of("/Users/wenzeng/Desktop/图片1.png").size(50, 50).create());
        // 表格
        RowRenderData rowHeader = Rows.of("Word处理方案", "是否跨平台", "易用性").bgColor("ff9800").textColor("FFFFFF")
                .center().rowHeight(2.5f).create();
        RowRenderData row1 = Rows.of("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装").create();
        RowRenderData row2 = Rows.of("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装").create();
        RowRenderData row3 = Rows.of("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构").create();
        RowRenderData row4 = Rows.of("OpenOffice", "需要安装OpenOffice软件", "复杂，需要了解OpenOffice的API").create();
        RowRenderData row5 = Rows.of("Jacob、winlib", "Windows平台", "复杂，不推荐使用").create();
        param.put("solution_compare", Tables.create(rowHeader, row1, row2, row3, row4, row5));
        // 图表
        ChartMultiSeriesRenderData chart = Charts
                .ofMultiSeries("易用性", new String[] { "代码量", "维护量" })
                .addSeries("poi-tl", new Double[] { 15.0, 6.0 })
                .addSeries("freemark", new Double[] { 223.0, 119.0 })
                .create();
        param.put("chart", chart);
        XWPFTemplate template = XWPFTemplate.compile("/Users/wenzeng/Desktop/poi-tl.docx").render(param);
        template.writeAndClose(Files.newOutputStream(Paths.get("/Users/wenzeng/Desktop/软件说明.docx")));
    }


    /**
     * 付款单
     * 1.循环列表 && 自定义渲染策略
     * 2.对象构造参数：DetailTable
     */
    public static void paymentTemplate() throws IOException {
        Map<String, Object> param = new HashMap<>(16);
        param.put("NO", "202412271750");
        param.put("ID", "CUS2023120901");
        param.put("taitou", "北京三快科技测试有限公司");
        param.put("consignee", "北京涟友供应商测试公司");
        param.put("total", "总共：7200");
        param.put("subtotal", "800");
        param.put("tax", "100");
        param.put("transform", "120");
        param.put("other", "30");
        param.put("unpay", "400");

        // 表格
        RowRenderData header = Rows.of("日期", "订单编号", "销售代表", "离岸价", "发货方式", "条款", "税号").bgColor("F2F2F2").center()
                .textColor("7F7f7F").textFontFamily("Hei").textFontSize(9).create();
        RowRenderData row = Rows.of("2024-12-30","SNB893","测试代表","5000元","快递","附录1","T1134320").create();
        BorderStyle style = new BorderStyle();
        style.setColor("A6A6A6");
        style.setSize(4);
        style.setType(XWPFTable.XWPFBorderType.SINGLE);
        param.put("order", Tables.create(header, row));
        // 复杂表格 自定义渲染策略
        DetailTable detailTable = new DetailTable();
        // 货物信息
        RowRenderData goods = Rows.of("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600").center().create();
        detailTable.setGoods(Arrays.asList(goods, goods, goods));
        // 人工费
        RowRenderData labors = Rows.of("油漆工", "2", "200", "400").create();
        detailTable.setLabors(Arrays.asList(labors, labors, labors, labors));
        param.put("detail_table", detailTable);
        // 生成文件
        Configure configure = Configure.builder().bind("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("/Users/wenzeng/Downloads/payment.docx", configure).render(param);
        template.writeAndClose(Files.newOutputStream(Paths.get("/Users/wenzeng/Desktop/付款说明书.docx")));
    }

    /**
     * 区块对 && 循环
     * 第一部分内容在模版中填充了文字，其他两部分模版和代码都没设置文本内容
     * 在生成的文件中三部分的文本内容是一样的
     * 每一部分：文本+图片+图表的循环
     * 列表是不是也可以做？这里是用区块对做的
     */
    public static void graphTemplate() throws IOException {
        Map<String, Object> param = new HashMap<>(16);
        Map<String, Object> elephant = new HashMap<String, Object>(){
            private static final long serialVersionUID = 512340563611097478L;

            {
            put("name","大象");
            put("picture", Pictures.of("/Users/wenzeng/Desktop/wz/模版/3321735547515.jpg").size(200, 200).create());
            put("chart", Charts.ofMultiSeries("大象生存现状", new String[]{"2018年", "2019年", "2020年"})
                    .addSeries("成年象", new Integer[]{500, 600, 700})
                    .addSeries("幼象", new Integer[]{200, 300, 400 })
                    .addSeries("全部", new Integer[]{700, 900, 1100})
                    .create()
            );
        }};
        Map<String, Object> bird = new HashMap<String, Object>(){
            private static final long serialVersionUID = -8169743815349040435L;

            {
            put("name", "天鹅");
            put("picture", Pictures.of("/Users/wenzeng/Desktop/wz/模版/swan-5038729_1280.jpg").size(200, 200).create());
            put("chart", Charts.ofMultiSeries("天鹅生存现状", new String[]{"2018年", "2019年", "2020年"})
                    .addSeries("幼天鹅", new Integer[]{10,20,30})
                    .addSeries("成年天鹅", new Integer[]{20,40,60})
                    .addSeries("全部", new Integer[]{30,50,60})
                    .create()
            );
        }};
        Map<String, Object> giraffe = new HashMap<String, Object>(){
            private static final long serialVersionUID = -6166829098390840466L;

            {
            put("name", "长颈鹿");
            put("picture",Pictures.of("/Users/wenzeng/Desktop/wz/模版/长颈鹿.jpeg").size(200, 200).create());
            put("chart", Charts.ofMultiSeries("长颈鹿生存现状", new String[]{"2018年", "2019年", "2020年"})
                    .addSeries("成年鹿", new Integer[]{500, 600, 700})
                    .addSeries("幼年鹿", new Integer[]{200, 300, 400}).create()
            );
        }};
        param.put("animals", Arrays.asList(elephant, bird, giraffe));
        XWPFTemplate template = XWPFTemplate.compile("/Users/wenzeng/Downloads/animal.docx").render(param);
        template.writeAndClose(Files.newOutputStream(Paths.get("/Users/wenzeng/Desktop/动物图表.docx")));
    }

    /**
     * 简历模版 {{?experiences}}{{/experiences}}
     * 区块对&&循环
     */
    public static void resumeTemplate() throws IOException {
        Map<String, Object> param = new HashMap<>(16);
        // 第一段
        Map<String, Object> first = new HashMap<String, Object>(){{
            put("company", "测试公司1");
            put("department", "测试部门");
            put("job", "测试工程师");
            put("time", "2021.06-2022.10");
            put("responsibility", Numberings.of(NumberingFormat.BULLET)
                    .addItem("编写测试用例10000条，测试覆盖率达90%")
                    .addItem("搭建自动化测试框架，提升团队测试效率和准确性")
                    .addItem("组织用例评审和线上问题复盘会")
                    .addItem("线上问题及时响应")
                    .create());
        }};
        // 第二段
        Map<String, Object> second = new HashMap<String, Object>(){{
            put("company", "北京科技有限公司");
            put("department", "中台支撑部门");
            put("job", "前端开发工程师");
            put("time", "2022.11-2023.10");
            put("responsibility", Numberings.of(NumberingFormat.BULLET)
                    .addItem("搭建自动生成前端项目结构脚手架，并推广应用到公司技术部门")
                    .addItem("修复Android打包问题，支持线上一键打包代替传统本地打包方式，提升团队效率")
                    .addItem("完成多个复杂组建的设计编写")
                    .addItem("梳理项目业务逻辑沉淀出业务文档")
                    .create());
        }};
        param.put("experiences", Arrays.asList(first, second));
        XWPFTemplate template = XWPFTemplate.compile("/Users/wenzeng/Desktop/wz/模版/简历模版.docx").render(param);
        template.writeAndClose(Files.newOutputStream(Paths.get("/Users/wenzeng/Desktop/wz/模版/简历说明.docx")));
    }

    /**
     * 1.嵌套标签 {{+var}}：提供两个文件，用Include构造模版文件的关系
     * 2.使用对象构造参数，不用map
     */
    public static void resumeTemplateV2() throws IOException {
        Map<String, Object> param = new HashMap<>(2);
        List<WorkData> workData = new ArrayList<>(2);
        WorkData first = new WorkData("测试公司1", "测试部门","测试工程师","2021.06-2022.10",
                Numberings.of(NumberingFormat.LOWER_ROMAN)
                        .addItem("编写测试用例10000条，测试覆盖率达90%")
                        .addItem("搭建自动化测试框架，提升团队测试效率和准确性")
                        .addItem("组织用例评审和线上问题复盘会")
                        .addItem("线上问题及时响应")
                        .create()
                );
        WorkData second = new WorkData("北京科技有限公司", "中台支撑部门", "前端开发工程师","2022.11-2023.10",
                Numberings.of(NumberingFormat.LOWER_ROMAN)
                        .addItem("搭建自动生成前端项目结构脚手架，并推广应用到公司技术部门")
                        .addItem("修复Android打包问题，支持线上一键打包代替传统本地打包方式，提升团队效率")
                        .addItem("完成多个复杂组建的设计编写")
                        .addItem("梳理项目业务逻辑沉淀出业务文档")
                        .create()
                );
        workData.add(first);
        workData.add(second);
        param.put("experience", Includes.ofLocal("/Users/wenzeng/Desktop/wz/模版/嵌套子标签.docx").setRenderModel(workData).create());
        XWPFTemplate template = XWPFTemplate.compile("/Users/wenzeng/Desktop/wz/模版/嵌套标签.docx").render(param);
        template.writeAndClose(Files.newOutputStream(Paths.get("/Users/wenzeng/Desktop/wz/模版/嵌套标签说明.docx")));
    }


    public static void main(String[] args) throws IOException {
        //introduceTemplate();
        //paymentTemplate();
        //graphTemplate();
        //resumeTemplate();
        resumeTemplateV2();
    }
}
