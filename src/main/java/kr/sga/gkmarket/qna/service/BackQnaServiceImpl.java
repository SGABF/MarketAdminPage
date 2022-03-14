package kr.sga.gkmarket.qna.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.qna.dao.BackQnaDAO;
import kr.sga.gkmarket.qna.vo.BackQnaVO;
import kr.sga.gkmarket.qna.vo.CommVO;
import kr.sga.gkmarket.qna.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("backQnaService")
public class BackQnaServiceImpl implements BackQnaService {

	@Autowired
	public BackQnaDAO backQnaDAO;

	@Override
	public void insert(BackQnaVO backQnaVO) throws SQLException {
		if (backQnaVO != null) {
			backQnaDAO.insert(backQnaVO);
		}
	}

	@Override
	public void update(BackQnaVO backQnaVO) throws SQLException {
		if (backQnaVO != null) {
			backQnaDAO.update(backQnaVO);
		}
	}

	@Override
	public void delete(BackQnaVO backQnaVO) throws SQLException {
		if (backQnaVO != null) {
			backQnaDAO.deleteByIdx(backQnaVO.getBack_Qna_Idx());

		}
	}

	@Override
	public BackQnaVO selectByIdx(int idx) throws SQLException {
		BackQnaVO backQnaVO = backQnaDAO.selectByIdx(idx);
		return backQnaVO;
	}

	@Override
	public PagingVO<BackQnaVO> selectList(CommVO commVO) throws SqlSessionException {
		log.info("{}의 selectList 호출 : {}", this.getClass().getName(), commVO);
		System.out.println(backQnaDAO.selectCount());
		PagingVO<BackQnaVO> pagingVO = null;
		try {
			// 전체 개수구하기
			int totalCount = backQnaDAO.selectCount();
			// 페이지 계산
			pagingVO = new PagingVO<>(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize(), totalCount);
			// 글 읽어오기
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("startNo", pagingVO.getStartNo());
			map.put("pageSize", pagingVO.getPageSize());
			List<BackQnaVO> list = backQnaDAO.selectList(map);
			pagingVO.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagingVO;
	}
}
