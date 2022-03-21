package kr.sga.gkmarket.notice.service;

import java.util.List;

import kr.sga.gkmarket.notice.vo.BackNoticeFileVO;

public interface NoticeFileService {
	
	List<BackNoticeFileVO> getNoticeFileList();
	
	void insertFile(BackNoticeFileVO backNoticeFileVO);
	
	void deleteFile(int noticeFileId, String realPath);

}
