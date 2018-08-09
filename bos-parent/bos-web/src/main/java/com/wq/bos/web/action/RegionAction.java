package com.wq.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wq.bos.domain.Region;
import com.wq.bos.domain.Staff;
import com.wq.bos.service.RegionService;
import com.wq.bos.utils.PageBean;
import com.wq.bos.utils.PinYin4jUtils;
import com.wq.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 区域设置
 * 
 * @author wangquan
 * @date 2018年7月24日
 *
 */
@Controller("regionAction")
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    private static final long serialVersionUID = 1L;
    @Autowired
    private RegionService regionService;

    // 属性驱动
    // 属性驱动获取前台参数
    private int page;// 多少页

    private int rows;// 数据条数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private File regionFile;

    public File getRegionFile() {
        return regionFile;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }
    //查询条件
    public String term;
     
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 分页查询
     */
    public String query() throws Exception {
        PageBean pageBean = new PageBean();
        // 设置一下客户端传过来page rows
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        // 创建一个离线对象

        DetachedCriteria mDetachedCriteria = DetachedCriteria.forClass(Region.class);
        // 设置进pageBean
        pageBean.setDetachedCriteria(mDetachedCriteria);

        // 调用service层进行业务逻辑处理
        regionService.pageQuery(pageBean);

        // json-lib
        // JSonObject 将一个单一的对象转换为json
        // JSonArray 将数组或者集合对象转换为json

        // 去掉json中我们不需要的数据
        JsonConfig mJsonConfig = new JsonConfig();
        mJsonConfig.setExcludes(new String[] { "currentPage", "detachedCriteria", "pageSize","subareas" });

        // 转成json字符串
        String json = JSONObject.fromObject(pageBean, mJsonConfig).toString();
        // 发送json数据
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    // 区域导入
    public String importXls() throws Exception {
        // 创建数组,将对象添加到list 的集合中,这样的写的好处1 便于以后修改 2 可以使用list的所有的属性方法
        List<Region> regions = new ArrayList<Region>();
        // 使用poi文件解析
        // 将文件加载到内存中(只能接受文件流)
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        // 根据指定的名称加载sheet对象
        HSSFSheet sheet = workbook.getSheet("sheet1");
        Region region = null;
        // 遍历sheet的数据
        // 遍历对象最好使用加强for
        for (Row row : sheet) {
            // 获得行号
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                // 跳出=过当前
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            // 将获取的数据封装成对象
            region = new Region(id, province, city, district, postcode, null, null, null);
            // 获取城市简码以及城市编码
            // length()-1的做法是去掉一个字符串后面的最后一个字符,如:北京市,去掉市
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            // 字符拼接
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            // 城市简码 BJCY
            String shorCode = StringUtils.join(headByString);
            // 城市编码 shijiazhuang
            // 如果不填跌第二个参数 默认空格
            String cityCode = PinYin4jUtils.hanziToPinyin(city, "");
            region.setShortcode(shorCode);
            region.setCitycode(cityCode);
            regions.add(region);
        }
         
        //批量保存
        regionService.save(regions);
        return "list";
    }
    //分区管理,模糊查询
    public  String listAjax() {
        List<Region> list=null;
        System.out.println("前台参数"+term );
        if(StringUtils.isNotBlank(term)) {
            list=regionService .findAll(term);
        }
        list=regionService.findAll();
       // System.out.println(list);
        this.javaToJson(list, new String[] {
                "currentPage","detachedCriteria","pageSize","subareas"
            });
        
        return NONE;
    }
    //添加信息
    public String  save() throws Exception{
        regionService.save(model);
         return "list";
    }
}
