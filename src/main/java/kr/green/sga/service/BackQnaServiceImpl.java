package kr.green.sga.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.sga.dao.BackQnaDAO;
import kr.green.sga.vo.BackQnaVO;

@Service
public class BackQnaServiceImpl implements BackQnaService  {

	@Autowired
	public BackQnaDAO backQnaDAO;
	
	@Override
	public void insert(BackQnaVO backQnaVO) throws SqlSessionException {
		if(backQnaVO!=null) {
			try {
				backQnaDAO.insert(backQnaVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(BackQnaVO backQnaVO) throws SqlSessionException {
		if(backQnaVO!=null) {
			try {
				backQnaDAO.update(backQnaVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(BackQnaVO backQnaVO) throws SqlSessionException {
		if(backQnaVO!=null) {
			try {
				backQnaDAO.deleteByIdx(backQnaVO.getBack_Qna_Idx());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
