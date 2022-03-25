/**
 * 
 */
package kr.sga.gkmarket.notice.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.sga.gkmarket.notice.dao.NoticeFileDAO;
import kr.sga.gkmarket.notice.vo.BackNoticeFileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("noticeFileService")

 class NoticeFileServiceImpl implements NoticeFileService {

	@Autowired
	private NoticeFileDAO noticeFileDAO;
	
	
	
	@Override
	public List<BackNoticeFileVO> getNoticeFileList() {
		
		List<BackNoticeFileVO> list = noticeFileDAO.getNoticeFileList();
		
		return list;
	}

	@Override
	public void insertFile(BackNoticeFileVO backNoticeFileVO ) {
		log.info("{}의 insertFile 호출 : {}", this.getClass().getName(),backNoticeFileVO);
		if(backNoticeFileVO != null) {
			noticeFileDAO.insertFile(backNoticeFileVO);
		}

	}

	@Override
	public void deleteFile(int notice_Idx, String realPath) {
		BackNoticeFileVO vo =null;
		vo = noticeFileDAO.selectByIdx(notice_Idx);
		
		if(vo != null) {
			File file = new File(realPath + vo.getBack_Noticefile_SaveName());
			
			if(file != null) {
				file.delete();
			}
			noticeFileDAO.deleteFile(notice_Idx);
		}
		
		

	}

	@Override
	public List<BackNoticeFileVO> selectRefList(int ref) {
		
		List<BackNoticeFileVO> list = noticeFileDAO.selectRefList();
		
		return list;
	}

}
