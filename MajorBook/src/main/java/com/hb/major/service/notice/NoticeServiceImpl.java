package com.hb.major.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hb.major.model.entity.NoticeVo;
import com.hb.major.model.notice.NoticeDao;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDao noticeDao;

	@Override
	public void noticeListAll(Model model, int currentpage) {
		try {


			int posttotalnum = noticeDao.noticeSelectAll().size(); // 총 게시글 수
			int totalpage;
			int startpage;
			if (posttotalnum % 10 == 0) {
				totalpage = posttotalnum / 10; // 한페이지에 게시글 10개씩
			} else {
				totalpage = posttotalnum / 10 + 1;
			}

			int tempstartpage = currentpage / 5; // 페이지는 5페이지씩
			
			if(currentpage%5==0) {
				 startpage = (tempstartpage -1)* 5+1;
			}else {
				startpage = tempstartpage * 5 + 1;
			}
			
		
			int endpage;

			if (totalpage - startpage < 4) {
				endpage = totalpage;
			} else {
				endpage = startpage + 4;
			}
			model.addAttribute("list", noticeDao.noticeSelectPage(currentpage));
			model.addAttribute("startpage", startpage);
			model.addAttribute("endpage", endpage);
			model.addAttribute("currentpage", currentpage);
			model.addAttribute("totalpage", totalpage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void noticeAddOne(NoticeVo bean) {
		try {
			noticeDao.noticeInsertOne(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void noticeDetailOne(int no, Model model) {
		try {
			noticeDao.noticeSelectOne(no);
			model.addAttribute("bean", noticeDao.noticeSelectOne(no));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void noticeUpdateOne(NoticeVo bean) {
		try {
		
			noticeDao.noticeUpdateOne(bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void noticeDeleteOne(int no) {
		try {
			noticeDao.noticeDeleteOne(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void noticeSearch(Model model, String keyword) {
		try {
			model.addAttribute("list", noticeDao.noticeSelectWhere(keyword));
			System.out.println(noticeDao.noticeSelectWhere(keyword));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
