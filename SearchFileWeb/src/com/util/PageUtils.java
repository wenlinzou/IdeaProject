package com.util;

import java.util.ArrayList;
import java.util.List;

import com.bean.Page;

public class PageUtils<T> {

	//传入返回的类型
	public List<T> queryListPage(List<T> inputList,Page page){
		List<T> backList = new ArrayList<T>();

		int startIndex = 0;
		int endIndex = 0;
		
		int currentPage = page.getCurrentPage();
		int tempGoPage = currentPage; 
		
		int pageSize = page.getPageSize();
//		int totalCount = page.getTotalCount();
		int totalCount = inputList==null?0:inputList.size();
		page.setTotalCount(totalCount);
		
		int totalPage = totalCount / pageSize + 1;
		page.setTotalPage(totalPage);
		currentPage = currentPage <= 1?0:currentPage-1;
		startIndex = currentPage * pageSize;
System.out.println("pagesize:"+pageSize+" totalcount:"+totalCount);		
		//大于总页数 显示最后一页
		if(tempGoPage >= totalPage){
			//显示剩余的记录
			// 总条数 - 每页显示条数 * (前往页码数-1) = 剩余
			startIndex = (totalPage-1)*pageSize;
			endIndex = totalCount;
System.out.println("剩余条数 "+endIndex);			
		}else{
			endIndex = startIndex + pageSize;
		}
			
System.out.println("size:"+inputList.size());
System.out.println("currnet:"+tempGoPage+" start "+startIndex +" end "+endIndex);
		for (int i = startIndex; i < endIndex; i++) {
			backList.add(inputList.get(i));
		}
		return backList;
	}
	
	

}
