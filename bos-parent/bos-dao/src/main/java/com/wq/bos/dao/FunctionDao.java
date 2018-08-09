package com.wq.bos.dao;

import java.util.List;

import com.wq.bos.dao.base.BaseDao;
import com.wq.bos.domain.Function;

/** 
*
* @author : wangquan
* @date ：2018年8月6日 上午10:40:17
* 
*/
public interface FunctionDao extends BaseDao<Function> {
    /**
     * @param id
     * @return
     */
    List<Function> findFunctionListByUserId(String id);

}
