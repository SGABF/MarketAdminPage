/**
 * 
 */
package kr.sga.gkmarket.notice.service;


import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.notice.dao.NoticeDAO;
import kr.sga.gkmarket.notice.dao.NoticeFileDAO;
import kr.sga.gkmarket.notice.vo.BackNoticeFileVO;
import kr.sga.gkmarket.notice.vo.BackNoticeVO;


/**
 * @author HanKyul
 *
 */
@Service("noticeSevice")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private NoticeFileDAO noticeFileDAO;
	
	@Override
	public void insertNotice(BackNoticeVO backNoticeVO) {
		if(backNoticeVO!=null) {
			noticeDAO.insertNotice(backNoticeVO);
		}
	}

	@Override
	public void updateNotice(BackNoticeVO backNoticeVO) {
		BackNoticeVO dbvo = null;
		if(backNoticeVO!=null) {
			dbvo = noticeDAO.selectIdx(backNoticeVO.getBack_Notice_Idx());
			noticeDAO.updateNotice(backNoticeVO);
		}
		dbvo = noticeDAO.selectIdx(backNoticeVO.getBack_Notice_Idx());
	}
	
	@Override
	public void deleteNotice(int backNoticeVO) {
			noticeDAO.deleteNotice(backNoticeVO);
	}

	@Override
	public List<BackNoticeVO> getNotice() {
	
		List<BackNoticeVO> list = noticeDAO.getNotice();
		
		return list;
	}
	
	@Override
	public int selectSeq() {

		return noticeDAO.selectSeq();
		
	}

	@Override
	public BackNoticeVO selectIdx(int idx) {
		
		
		return noticeDAO.selectIdx(idx);
	}

		
		
	}


