package kr.sga.gkmarket.qna.service;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.qna.dao.BackQnaDAO;
import kr.sga.gkmarket.qna.dao.BackQnaFileDAO;
import kr.sga.gkmarket.qna.vo.BackQnaFileVO;
import kr.sga.gkmarket.qna.vo.BackQnaVO;
import kr.sga.gkmarket.vo.CommVO;
import kr.sga.gkmarket.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("backQnaService")
public class BackQnaServiceImpl implements BackQnaService {

	@Autowired
	public BackQnaDAO backQnaDAO;

	@Autowired
	public BackQnaFileDAO backQnaFileDAO;

	@Override
	public void insert(BackQnaVO backQnaVO) {
		if (backQnaVO != null) {
			backQnaDAO.insert(backQnaVO);
		}
	}

	@Override
	public void update(BackQnaVO backQnaVO, String[] delFiles, String realPath) {
		log.info("{}의 update 호출 : {}", this.getClass().getName(), backQnaVO + "\n" + Arrays.toString(delFiles) + "\n" + realPath);
		BackQnaVO dbVO = backQnaDAO.selectByIdx(backQnaVO.getBack_Qna_Idx());
			// 1. 글수정
			backQnaDAO.update(backQnaVO);
			// 2. 새롭게 첨부된 첨부 파일의 정보도 저장해 주어야 한다.
			List<BackQnaFileVO> list = backQnaVO.getFileList();
			if(list!=null && list.size()>0) {
				for(BackQnaFileVO vo : list) {
					vo.setBack_Qna_Idx(backQnaVO.getBack_Qna_Idx()); // 원본글번호
					backQnaFileDAO.insert(vo);
				}
			} 
			// 3, 이미 첨부되었던 파일 삭제
			log.info("{}의 update delFiles : {}", this.getClass().getName(), delFiles);
			if(delFiles!=null && delFiles.length>0) {
				for(String t : delFiles ) {
					int idx = Integer.parseInt(t);
					if(idx>0) {
						// 실제 파일을 삭제하려면
						// 1. 해당 글번호의 글을 읽어와서
						BackQnaFileVO backQnaFileVO = backQnaFileDAO.selectFiles(idx);
						if(backQnaFileVO!=null) {
							// 2. 실제 서버의 파일을 삭제해 주어야 한다.
							File file = new File(realPath + File.separator + backQnaFileVO.getBack_Qnafile_SaveName());
							file.delete(); // 실제 파일삭제
							backQnaFileDAO.deleteByIdx(idx); // DB에서만 삭제된다.
						}
					}
				}
			}

		
	}

	@Override
	public void delete(BackQnaVO backQnaVO) {
		if (backQnaVO != null) {
			backQnaDAO.deleteByIdx(backQnaVO.getBack_Qna_Idx());

		}
	}

	@Override
	public BackQnaVO selectByIdx(int idx) {
		log.info("{}의 selectByIdx 호출 : {}", this.getClass().getName(), idx);
		BackQnaVO backQnaVO = backQnaDAO.selectByIdx(idx); // 글 1개를 가져온다.
		// 그 글에 해당하는 첨부파일의 정보를 가져온다.
		if (backQnaVO != null) {
			List<BackQnaFileVO> list = backQnaFileDAO.selectList(idx);
			backQnaVO.setFileList(list);
		}
		log.info("{}의 selectByIdx 리턴 : {}", this.getClass().getName(), backQnaVO);
		return backQnaVO;
	}

	@Override
	public PagingVO<BackQnaVO> selectList(CommVO commVO) {
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
			// 해당글들의 첨부파일 정보를 넣어준다.
			if (list != null && list.size() > 0) {
				for (BackQnaVO vo : list) {
					// 해당글의 첨부파일 목록을 가져온다.
					List<BackQnaFileVO> fileList = backQnaFileDAO.selectList(vo.getBack_Qna_Idx());
					// vo에 넣는다.
					vo.setFileList(fileList);
				}
			}
			// 완성된 리스트를 페이징 객체에 넣는다.

			pagingVO.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagingVO;
	}

	@Override
	public BackQnaFileVO selectFiles(int idx) {
		@SuppressWarnings("unused")
		BackQnaFileVO backQnaFileVO = backQnaFileDAO.selectFiles(idx);
		return null;
	}
}
