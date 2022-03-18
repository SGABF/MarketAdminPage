package kr.sga.gkmarket.qna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.qna.vo.BackQnaFileVO;

@Mapper
public interface BackQnaFileDAO {
	// 글 하나의 첨부파일 가져오기
	BackQnaFileVO selectFiles(int ref);
	// 첨부파일 저장 
	void insert(BackQnaFileVO vo);
	// 수정시 첨부파일 저장 : 이때는 원본글의 ref가 별도로 존재한다. -->
	void insertUpdate(BackQnaFileVO vo);
	// 첨부파일 삭제 -->
	void deleteByIdx(int idx);
	// 원본글의 첨부파일 모두 읽기 -->
	List<BackQnaFileVO> selectList(int ref);
	// 원본글의 첨부파일 모두 삭제하기 -->
	void deleteByRef(int ref);
}
