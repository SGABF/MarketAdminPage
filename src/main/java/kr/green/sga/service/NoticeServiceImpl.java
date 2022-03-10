/**
 * 
 */
package kr.green.sga.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.sga.dao.NoticeDAO;
import kr.green.sga.vo.BackNoticeVO;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNotice(BackNoticeVO backNoticeVO) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void deleteNotice(BackNoticeVO backNoticeVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BackNoticeVO> getNotice() throws Exception {
		
		System.out.println(noticeDAO.getNotice().toString());
		// TODO Auto-generated method stub
		return noticeDAO.getNotice();
	}

}
