package kr.green.sga.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.sga.dao.BackQnaDAO;
import kr.green.sga.dao.BackQnaReplyDAO;
import kr.green.sga.vo.BackQnaReplyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("qnaReplyService")
public class BackQnaReplyServiceImpl implements BackQnaReplyService{
	
	@Autowired
	private BackQnaReplyDAO backQnaReplyDAO;
	
	@Autowired
	private BackQnaDAO BackQnaDAO;

	@Override
	public void insertReply(BackQnaReplyVO backQnaReplyVO) {
		log.info("insert 호출 : " + backQnaReplyVO);
		if(backQnaReplyVO!=null) {
			try {
				//db에 저장
				backQnaReplyDAO.insert(backQnaReplyVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateReply(BackQnaReplyVO backQnaReplyVO) {
		log.info("update 호출 : " + backQnaReplyVO);
		if(backQnaReplyVO!=null) {
			try {
				//문의 답변 수정 
				backQnaReplyDAO.update(backQnaReplyVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("null")
	@Override
	public void deleteReply(BackQnaReplyVO backQnaReplyVO) {
		log.info("delete 호출 : " + backQnaReplyVO);
		if(backQnaReplyVO!=null) {
			BackQnaReplyVO vo = null;
				try {
					backQnaReplyDAO.delete(vo.getBack_Qna_Idx());
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
