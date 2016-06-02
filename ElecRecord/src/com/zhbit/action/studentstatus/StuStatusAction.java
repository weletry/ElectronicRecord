package com.zhbit.action.studentstatus;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.org.apache.xerces.internal.util.Status;
import com.zhbit.action.BaseAndExcelAction;
import com.zhbit.entity.StuStatus;
import com.zhbit.entity.SystemDll;
import com.zhbit.services.studentstatus.StuStatusServices;
import com.zhbit.services.system.SystemDllServices;
import com.zhbit.util.DecodeUtils;
import com.zhbit.util.PageUtils;
import com.zhbit.util.QueryUtils;

/** 
 * 项目名称：ElecRecord
 * 类名称：StuStatusAction
 * 类描述： 用于学籍异动信息处理的Action类
 * 创建人：罗建鑫
 * 创建时间：2016年5月26日 上午10:43:11
 * 修改人：罗建鑫
 * 修改时间：2016年5月26日 上午10:43:11
 * 修改备注： 
 * @version 
 */ 
@Controller("stuStatusAction")
@Scope(value="prototype")
public class StuStatusAction extends BaseAndExcelAction {

	/**
	 * 添加默认的UID
	 */
	private static final long serialVersionUID = 1L;
	StuStatus stuStatus;
	@Resource(name=StuStatusServices.SERVICE_NAME)
	StuStatusServices stuStatusServices;
	@Resource(name=SystemDllServices.SERVICE_NAME)
	SystemDllServices systeDllServices;
	@Override
	public String importExcel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exportExcel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String listUI() {
		// TODO Auto-generated method stub
		//查询学年信息并推送到前台进行显示
		String[] fields={"keyword=?"};
		String[] params1={"学年"};
		List<SystemDll> years=systeDllServices.findObjectByFields(fields,params1);
		request.setAttribute("years", years);
		
		//对传来的查询条件进行编码
		if(stuStatus!=null){
			try {
				stuStatus.setStuName(DecodeUtils.decodeUTF(stuStatus.getStuName()));
				stuStatus.setAcademicYear(DecodeUtils.decodeUTF(stuStatus.getAcademicYear()));
				stuStatus.setStudentNo(DecodeUtils.decodeUTF(stuStatus.getStudentNo()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("编码时出错");
		}
		}
		//将传过来的参数进行回显
		request.setAttribute("queryCon",stuStatus);
		
		pageUtils=stuStatusServices.queryList(stuStatus, getPageNO(), getPageSize());
		
		return "listUI";
	}

	/**
		 * 方法描述:此方法用于跳转到添加学籍异动信息界面
		 * @param
		 * @param 
		 */
	@Override
	public String addUI() {
		// TODO Auto-generated method stub
		//保存查询条件
			request.setAttribute("queryCon", stuStatus);
			
		//到数据字典查找类别
			String[] fields={"keyword=?"};
			String[] params1={"学院名称"};
			String[] params2={"学年"};
			String[] params3={"专业"};
		//查找学院类别	
			List<SystemDll> colleges=systeDllServices.findObjectByFields(fields, params1);
		//查找学年
			List<SystemDll> years=systeDllServices.findObjectByFields(fields, params2);
		//查找专业
			List<SystemDll> majors=systeDllServices.findObjectByFields(fields, params3);
			
		//将查询到的信息推送到前台显示
			request.setAttribute("colleges", colleges);	
			request.setAttribute("years", years);
			request.setAttribute("majors", majors);
				
		return "addUI";
	}

	@Override
	public String add() {
		//设定创建时间为当前时间
		Timestamp createtime = new Timestamp(System.currentTimeMillis());
		stuStatus.setCreateTime(createtime);
		
		//利用save方法将新添加的学籍异动信息添加到数据库中
		stuStatusServices.save(stuStatus);
		
		//保存成功后将Stustatus中的属性设定为查询条件
		stuStatus.setAcademicYear(request.getParameter("query_academicYear"));
		stuStatus.setStudentNo(request.getParameter("query_studentNo"));
		stuStatus.setStuName(request.getParameter("query_stuName"));
				
		//这里不命名为queryCon，因为Struts中XML文件不支持无Get，Set方法的EL表达式
		request.setAttribute("stuStatus",stuStatus);
		
		return "add";
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
		 * 方法描述:此方法用于跳转到学籍异动信息修改界面
		 * @param
		 * @param 
		 */
	@Override
	public String editorUI() {
		// TODO Auto-generated method stub
		//保存查询条件
		request.setAttribute("queryCon", stuStatus);
		
		//通过传过来的参数值获取对应的学籍信息
		stuStatus=stuStatusServices.findObjectById(stuStatus.getId());
		
		//到数据字典查找类别
		String[] fields={"keyword=?"};
		String[] params1={"学院名称"};
		String[] params2={"学年"};
		String[] params3={"专业"};
		//查找学院类别	
		List<SystemDll> colleges=systeDllServices.findObjectByFields(fields, params1);
		//查找学年
		List<SystemDll> years=systeDllServices.findObjectByFields(fields, params2);
		//查找专业
		List<SystemDll> majors=systeDllServices.findObjectByFields(fields, params3);
		
		//将查询到的信息推送到前台显示
		request.setAttribute("colleges", colleges);	
		request.setAttribute("years", years);
		request.setAttribute("majors", majors);
		
		//将查询得到的学籍信息推送到前台显示
		request.setAttribute("stuStatus", stuStatus);
		
		return "editorUI";
	}

	
	@Override
	public String editor() {
		// TODO Auto-generated method stub
		
		//使用update方法更新学籍信息
		stuStatusServices.update(stuStatus);
		
		//保存成功后将Stustatus中的属性设定为查询条件
	
		stuStatus.setAcademicYear(request.getParameter("query_academicYear"));
		stuStatus.setStudentNo(request.getParameter("query_studentNo"));
		stuStatus.setStuName(request.getParameter("query_stuName"));
	
		//这里不命名为queryCon，因为Struts中XML文件不支持无Get，Set方法的EL表达式
		request.setAttribute("stuStatus",stuStatus);
		
		return "editor";
	}

	@Override
	public String deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//------------------------------------getter&setter-----------------------------------
	public StuStatus getStuStatus() {
		return stuStatus;
	}

	public void setStuStatus(StuStatus stuStatus) {
		this.stuStatus = stuStatus;
	}
	

	
}
