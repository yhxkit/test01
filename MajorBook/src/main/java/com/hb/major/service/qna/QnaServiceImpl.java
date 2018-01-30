package com.hb.major.service.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hb.major.model.entity.QnaVo;
import com.hb.major.model.qna.QnaDao;

@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	QnaDao qnaDao;

	@Override
	public void qnaListAll(Model model, int currentpage) {
		try {

			int posttotalnum = qnaDao.qnaSelectAll().size(); // 총 게시글 수
			int totalpage;

			if (posttotalnum % 10 == 0) {
				totalpage = posttotalnum / 10; // 한페이지에 게시글 10개씩
			} else {
				totalpage = posttotalnum / 10 + 1;
			}

			int tempstartpage = currentpage / 5; // 페이지는 5페이지씩
			int startpage = tempstartpage * 5 + 1;
			int endpage;

			if (totalpage - startpage < 4) {
				endpage = totalpage;
			} else {
				endpage = startpage + 4;
			}

			model.addAttribute("list", qnaDao.qnaSelectPage(currentpage));
			model.addAttribute("startpage", startpage);
			model.addAttribute("endpage", endpage);
			model.addAttribute("currentpage", currentpage);
			model.addAttribute("totalpage", totalpage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void qnaAddOne(QnaVo bean) {
		try {
			qnaDao.qnaInsertOne(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void qnaDetailOne(int no, Model model) {
		try {
			qnaDao.qnaSelectOne(no);
			model.addAttribute("bean", qnaDao.qnaSelectOne(no));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void qnaUpdateOne(QnaVo bean) {
		try {
			qnaDao.qnaUpdateOne(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void qnaDeleteOne(int no) {
		try {
			qnaDao.qnaDeleteOne(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void qnaSearch(Model model, String keyword) {
		try {
			model.addAttribute("list", qnaDao.qnaSelectWhere(keyword));
			System.out.println(qnaDao.qnaSelectWhere(keyword));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void qnaMyListAll(Model model, String id) {
		try {
			System.out.println(qnaDao.qnaSelectWhereMylist(id));
			
			model.addAttribute("mylist", qnaDao.qnaSelectWhereMylist(id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
