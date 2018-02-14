package com.hb.major.service.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hb.major.model.bbs.BbsDao;
import com.hb.major.model.entity.BbsVo;

@Service
public class BbsServiceImpl implements BbsService {
	@Autowired
	BbsDao bbsDao;
	
	@Override
	public void bbsListAll(Model model, int currentpage) {
		try {

			int posttotalnum = bbsDao.bbsSelectAll().size(); // 총 게시글 수
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

			model.addAttribute("list", bbsDao.bbsSelectPage(currentpage));
			model.addAttribute("startpage", startpage);
			model.addAttribute("endpage", endpage);
			model.addAttribute("currentpage", currentpage);
			model.addAttribute("totalpage", totalpage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bbsAddOne(BbsVo bean) {
		try {
			bbsDao.bbsInsertOne(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bbsDetailOne(int no, Model model) {
		try {
			bbsDao.bbsSelectOne(no);
			model.addAttribute("bean", bbsDao.bbsSelectOne(no));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bbsUpdateOne(BbsVo bean) {
		try {
			bbsDao.bbsUpdateOne(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bbsDeleteOne(int no) {
		try {
			
			bbsDao.bbsDeleteOne(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bbsSearch(Model model, String keyword) {
		try {
			model.addAttribute("list", bbsDao.bbsSelectWhere(keyword));
			System.out.println(bbsDao.bbsSelectWhere(keyword));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bbsStatus(Model model, String status) {
		try {
			model.addAttribute("list", bbsDao.bbsSelectStatus(status));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
