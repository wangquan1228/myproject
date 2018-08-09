package com.wq.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wq.bos.domain.Region;
import com.wq.bos.domain.Subarea;
import com.wq.bos.service.SubareaService;
import com.wq.bos.utils.FileUtils;
import com.wq.bos.utils.PinYin4jUtils;
import com.wq.bos.web.action.base.BaseAction;
import com.wq.crm.utils.CustomerService;

@Controller("subareaAction")
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    // 注入
    @Autowired
    private SubareaService subareaService;
    @Autowired
    private  CustomerService customerService;
    //导入文件
    private File subareaFile;
    
    public File getSubareaFile() {
        return subareaFile;
    }

    public void setSubareaFile(File subareaFile) {
        this.subareaFile = subareaFile;
    }
    //查询条件
    private String term;
    

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    // 分页查询
    public String pageQuery() {
        // 动态查询,取出来DetachedCriteria
        DetachedCriteria dc = pageBean.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)) {
            // 添加过滤条件,根据地址关键字模糊查询
            dc.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        Region region = model.getRegion();
        if (region != null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            // 添加连接
            dc.createAlias("region", "r");
            // select * from subarea s join region r on s.id = r .id where ....
            if (StringUtils.isNotBlank(province)) {
                dc.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(city)) {
                dc.add(Restrictions.like("r.city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                dc.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
        subareaService.pageQuery(pageBean);
        this.javaToJson(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "decidedzone", "subareas" });
        return NONE;
    }

    // 添加信息
    public String add() throws Exception {
        System.out.println(model);
        subareaService.save(model);
        return "list";
    }
    //导出
    public String exportXls() throws IOException {
        //第一处查询数据库,返回所有分区数据
        List<Subarea> list=subareaService.findAll();
        //第二步 使用poi 将数据写到execel里面
        //首先在内存中创建一个文件
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建一个标签页
        HSSFSheet sheet=workbook.createSheet("分区数据");
        //创建标题行
        HSSFRow headRow=sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");
        for(Subarea subarea:list) {
            HSSFRow dataROw=sheet.createRow(sheet.getLastRowNum()+1);
            dataROw.createCell(0).setCellValue(subarea.getId());
            dataROw.createCell(1).setCellValue(subarea.getStartnum());
            dataROw.createCell(2).setCellValue(subarea.getEndnum());
            dataROw.createCell(3).setCellValue(subarea.getPosition());
            dataROw.createCell(4).setCellValue(subarea.getRegion().getName());
             
        }
        String  fileName="分区数据.xls";
        String contenType = ServletActionContext.getServletContext().getMimeType(fileName);
        //获取输出流
        ServletOutputStream outputStream=ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(contenType);
        
        //获取浏览器的agent 
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        //防止出现中文乱码
        fileName = FileUtils.encodeDownloadFilename(fileName, agent);
        ServletActionContext.getResponse().setHeader("content-disposition", "atachment;filename="+fileName);
        workbook.write(outputStream);
         return  NONE;
    }
    /**
     * 管理分区
     * @return
     */
    public  String listAjax() {
      //查询到所有未关联到定区的分区 ,返回数据
            List<Subarea> list = subareaService.findListNotAssociation();
            //System.out.println("测试: "+list);
            this.javaToJson(list, new String [] {"decidedzone","region"});
            return NONE;
        }
    
}
