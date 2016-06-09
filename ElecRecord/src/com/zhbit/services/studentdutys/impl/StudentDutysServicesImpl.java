package com.zhbit.services.studentdutys.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zhbit.dao.subjectcontest.SubjectContestDao;
import com.zhbit.dao.polstatus.PolstatusDao;
import com.zhbit.dao.studentdutys.StudentDutysDao;
import com.zhbit.dao.stuentstatus.StuStatusDao;
import com.zhbit.dao.system.UserDao;
import com.zhbit.entity.StudentDutys;
import com.zhbit.entity.Subjectcontest;
import com.zhbit.entity.User;
import com.zhbit.services.BaseServicesImpl;
import com.zhbit.services.studentdutys.StudentDutysServices;
import com.zhbit.services.subjectcontest.SubjectContestServices;
import com.zhbit.services.system.UserServices;
import com.zhbit.util.PageUtils;
import com.zhbit.util.QueryUtils;


/**
 * 项目名称：ElecRecord 
 * 类名称： StudentDutysServicesImpl 
 * 类描述： TODO
 * 创建人：朱嘉鑫
 * 创建时间：2016年6月7日 下午7:23:22 
 * 修改人：朱嘉鑫
 * 修改时间：2016年6月7日 下午7:23:22 
 * 修改备注： 
 * @version 
 */
@Service(value=StudentDutysServices.SERVER_NAME)
public class StudentDutysServicesImpl extends BaseServicesImpl<StudentDutys> 
	implements StudentDutysServices {
	
	StudentDutysDao studentDutysDao;
	@Resource(name=StudentDutysDao.DAO_NAME)
	 public void setSubjectContestDao(StudentDutysDao studentDutysDao) {
	    	super.setBaseDao(studentDutysDao);
			this.studentDutysDao = studentDutysDao;
			
		}
//	@Override
//	public void save(StudentDuty stydent) {
//		subjectContestDao.add(studentContest);
//	}


	

	@Override
	public PageUtils queryList(StudentDutys studentDutys, int pageNO, int pageSize) {
		String[] fields=null;
		String[] params=null;
		String proterty="createTime";		
		if(studentDutys!=null){ //判定politicalstatus不为空时
			////多个查询条件组合
			if(!StringUtils.isEmpty(studentDutys.getGrade())){ 
				//查询语句组合
				fields=new String[]{"grade=?","college=?","className like ?","duty=?"};
				params=new String[]{studentDutys.getGrade(),studentDutys.getCollege(),"%"+studentDutys.getClassName()+"%",studentDutys.getDuty()};
			}else if(!StringUtils.isEmpty(studentDutys.getCollege())){
				fields=new String[]{"college=?","grade=?","className like ?","duty=?"};
				params=new String[]{studentDutys.getCollege(),studentDutys.getGrade(),"%"+studentDutys.getClassName()+"%",studentDutys.getDuty()};
			}else if(!StringUtils.isEmpty(studentDutys.getClassName())){
				fields=new String[]{"className like ?","college=?","grade=?","duty=?"};
				params=new String[]{"%"+studentDutys.getClassName()+"%",studentDutys.getCollege(),studentDutys.getGrade(),studentDutys.getDuty()};
			}else  if(!StringUtils.isEmpty(studentDutys.getDuty())){
				fields=new String[]{"duty=?","college=?","grade=?","className like ?"};
				params=new String[]{studentDutys.getDuty(),studentDutys.getCollege(),studentDutys.getGrade(),"%"+studentDutys.getClassName()+"%"};
			}
		}

		return getPageUtils(fields, params, null, QueryUtils.ORDER_BY_ASC, pageNO, pageSize);
	}
	@Override
	public List<StudentDutys> queryListDownload(Serializable... id) {
		List<StudentDutys> list=studentDutysDao.queryListDownload(id);
		return list;
	}
	@Override
	public List<StudentDutys> queryAllList() {
		return studentDutysDao.queryAllList();
	}


	

	
}