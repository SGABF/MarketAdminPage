package kr.sga.gkmarket.qna.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.sga.gkmarket.qna.dao.BackQnaFileDAO;
import kr.sga.gkmarket.qna.vo.BackQnaFileVO;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service("backQnaFileService")
public class BackQnaFileServiceImpl implements BackQnaFileService {

	@Autowired
	public BackQnaFileDAO backQnaFileDAO;

	@Override
	public void insert(BackQnaFileVO backQnaFileVO) {
		log.info("{}의 insert 호출", this.getClass().getName(), backQnaFileVO);
		if (backQnaFileVO != null) {
			backQnaFileDAO.insert(backQnaFileVO);
		}
	}

	@Override
	public void updateInsert(BackQnaFileVO backQnaFileVO) {
		log.info("{}의 updateInsert 호출", this.getClass().getName(), backQnaFileVO);
		if (backQnaFileVO != null) {
			backQnaFileDAO.updateInsert(backQnaFileVO);
		}
	}

	@Override
	public void delete(int ref, String realPath) {
		log.info("{}의 delete 호출", this.getClass().getName(), ref, realPath);
		BackQnaFileVO fileVO = null;
		fileVO = backQnaFileDAO.selectFiles(ref);
		if (fileVO != null) {
			File file = new File(realPath + fileVO.getBack_Qnafile_SaveName());
			log.info("{}의 realPath 호출", this.getClass().getName(), realPath + fileVO.getBack_Qnafile_SaveName());
			
			if(file!=null) {
				file.delete();
			}
			backQnaFileDAO.deleteByRef(ref);
		}
	}

	@Override
	public List<BackQnaFileVO> selectList() {
		log.info("{}의 selectList 호출", this.getClass().getName());
			List<BackQnaFileVO> list = backQnaFileDAO.selectList();
		return list;
	}

}
