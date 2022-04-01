package kr.sga.gkmarket.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.qna.dao.BackQnaReplyDAO;
import kr.sga.gkmarket.qna.vo.BackQnaReplyVO;

@Service("backQnaReplyService")
public class BackQnaReplyServiceImpl implements BackQnaReplyService {

	@Autowired
	public BackQnaReplyDAO backQnaReplyDAO;

	@Override
	public void insert(BackQnaReplyVO backQnaReplyVO) {
		if (backQnaReplyVO != null) {
			
			backQnaReplyDAO.insert(backQnaReplyVO);
			System.out.println("backQnaRe   " + backQnaReplyVO + "\n");
			backQnaReplyDAO.selectContentIdx(backQnaReplyVO);
		}
	}

	@Override
	public void update(BackQnaReplyVO backQnaReplyVO) {
		if (backQnaReplyVO != null) {
			backQnaReplyDAO.update(backQnaReplyVO);
		}
	}

	@Override
	public void delete(int idx) {
		System.out.println("delete  " +"\n");
			backQnaReplyDAO.delete(idx);
	}

	@Override
	public int commentCount(int ref) {
		int count = backQnaReplyDAO.commentCount(ref);
		return count;
	}

	@Override
	public BackQnaReplyVO selectComment(int idx) {
		BackQnaReplyVO backQnaReplyVO = null;
		backQnaReplyVO = backQnaReplyDAO.selectComment(idx);
		
		return backQnaReplyVO;
	}
}
