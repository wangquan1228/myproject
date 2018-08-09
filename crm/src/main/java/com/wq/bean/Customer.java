package com.wq.bean;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	 
        /**
         * java.lang.String
         */
        private Long cust_id;
        private String cust_name;
        //private String cust_source;
        //private String cust_industry;
        //private String cust_level;
        private String cust_linkman;
        private String cust_phone;
        private String cust_moblie;
        
        
        //引用管理的数据字典对象
        private BaseDict cust_source;//客户来源
        private BaseDict cust_industry;//客户行业
        private BaseDict cust_level;//客户级别
        
      //表达客户与拜访记录的一对多、
        private Set<SaleVisit> saleVisits = new HashSet<SaleVisit>();
        
        
        public Set<SaleVisit> getSaleVisits() {
            return saleVisits;
        }
        public void setSaleVisits(Set<SaleVisit> saleVisits) {
            this.saleVisits = saleVisits;
        }
        
        public BaseDict getCust_source() {
            return cust_source;
        }
        public void setCust_source(BaseDict cust_source) {
            this.cust_source = cust_source;
        }
        public BaseDict getCust_industry() {
            return cust_industry;
        }
        public void setCust_industry(BaseDict cust_industry) {
            this.cust_industry = cust_industry;
        }
        public BaseDict getCust_level() {
            return cust_level;
        }
        public void setCust_level(BaseDict cust_level) {
            this.cust_level = cust_level;
        }
        public Long getCust_id() {
            return cust_id;
        }
        public void setCust_id(Long cust_id) {
            this.cust_id = cust_id;
        }
        public String getCust_name() {
            return cust_name;
        }
        public void setCust_name(String cust_name) {
            this.cust_name = cust_name;
        }
        
        public String getCust_linkman() {
            return cust_linkman;
        }
        public void setCust_linkman(String cust_linkman) {
            this.cust_linkman = cust_linkman;
        }
        public String getCust_phone() {
            return cust_phone;
        }
        public void setCust_phone(String cust_phone) {
            this.cust_phone = cust_phone;
        }
        public String getCust_moblie() {
            return cust_moblie;
        }
        public void setCust_moblie(String cust_moblie) {
            this.cust_moblie = cust_moblie;
        }
        @Override
        public String toString() {
            return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_linkman=" + cust_linkman + ", cust_phone=" + cust_phone + ", cust_moblie=" + cust_moblie + ", cust_source="
                    + cust_source + ", cust_industry=" + cust_industry + ", cust_level=" + cust_level + "]";
        }
         

}
