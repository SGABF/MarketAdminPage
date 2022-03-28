package kr.sga.gkmarket.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.notice.vo.BackNoticeFileVO;

@Mapper
public interface NoticeFileDAO {
	BackNoticeFileVO selectByIdx(int idx);
	
	List<BackNoticeFileVO> getNoticeFileList();

	void insertFile(BackNoticeFileVO backNoticeFileVO);
	
	void deleteFile(int ref);
}
