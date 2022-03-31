package kr.sga.gkmarket.qna.service;

import java.util.List;

import kr.sga.gkmarket.qna.vo.BackQnaFileVO;

public interface BackQnaFileService {
	
	// 첨부파일 추가
	void insert(BackQnaFileVO backQnaFileVO);
	// 첨부파일 수정시 저장
	void updateInsert(BackQnaFileVO backQnaFileVO);
	// 첨부파일 삭제
	void delete(int ref, String realPath);
	// 파일 목록 가져오기
	List<BackQnaFileVO> selectList();
}
