package com.taoists.sys.service;

import java.io.Serializable;
import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.entity.Dept;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
public interface DeptService extends BaseDao<Dept> {
	
	List<Dept> findDepts(Dept dept, boolean parentIsNull);
	
	List<Dept> findDeptsByParent(Serializable parentId);

}
