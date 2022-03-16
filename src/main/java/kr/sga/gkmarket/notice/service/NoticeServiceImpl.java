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
import kr.sga.gkmarket.notice.vo.BackNoticeVO;


/**
 * @author HanKyul
 *
 */
@Service("noticeSevice")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public void insertNotice(BackNoticeVO backNoticeVO) {
		if(backNoticeVO!=null) {
			noticeDAO.insertNotice(backNoticeVO);
		}
	}

	@Override
	public void updateNotice(BackNoticeVO backNoticeVO) {
		if(backNoticeVO!=null) {
			noticeDAO.updateNotice(backNoticeVO);
		}
	}
	
	@Override
	public void deleteNotice(int backNoticeVO) {
			noticeDAO.deleteNotice(backNoticeVO);
	}

	@Override
	public List<BackNoticeVO> getNotice() {
		
		System.out.println(noticeDAO.getNotice().toString());
		
		return noticeDAO.getNotice();
	}

}
