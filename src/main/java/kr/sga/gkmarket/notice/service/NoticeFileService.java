package kr.sga.gkmarket.notice.service;

import java.util.List;

import kr.sga.gkmarket.notice.vo.BackNoticeFileVO;
import kr.sga.gkmarket.notice.vo.BackNoticeVO;

public interface NoticeFileService {
	
	List<BackNoticeFileVO> getNoticeFileList();
	
	void insertFile(BackNoticeFileVO backNoticeFileVO);
	
	void deleteFile(int notice_Idx, String realPath);
	
	List<BackNoticeFileVO> selectRefList(int ref);
}
