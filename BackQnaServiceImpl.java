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
import kr.sga.gkmarket.qna.vo.QnaPagingVO;
import kr.sga.gkmarket.qna.vo.QnaUserNameVO;
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
	public void update(BackQnaVO backQnaVO, int delFiles, String realPath) {
		log.info("{}의 update 호출 : {}", this.getClass().getName(), backQnaVO + "\n" + delFiles + "\n" + realPath);
			// 1. 글수정
			backQnaDAO.update(backQnaVO);
			 
			// 2. 새롭게 첨부된 첨부 파일의 정보도 저장해 주어야 한다.
			BackQnaFileVO fileVO = backQnaVO.getFile();
			fileVO.setBack_Qna_Idx(backQnaVO.getBack_Qna_Idx());
			backQnaFileDAO.insert(fileVO);
			// 3. 이미 첨부되었던 파일 삭제
			log.info("{}의  delFiles : {}", this.getClass().getName(), delFiles);
			
			if(delFiles>0) {
				//실제파일을 삭제하려면
				// 1. 해당 글번호의 예전파일을 읽어와서
				BackQnaFileVO qnaFileVO = backQnaFileDAO.previousSelect(delFiles);
				System.out.println("qnaFileVO : " + qnaFileVO + "\n");
				if(qnaFileVO!=null) {
					//2. 실제 서버의 파일을 삭제해 주어야 한다.
					File file = new File(realPath + File.separator+ qnaFileVO.getBack_Qnafile_SaveName());
					System.out.println("qnaFileVO : " + qnaFileVO.getBack_Qnafile_SaveName() + "\n");
					file.delete(); // 실제 파일 삭제
					backQnaFileDAO.deleteByIdx(delFiles);
					System.out.println("qnaFileVO : " + qnaFileVO + "\n");

				}
			}
	}

	@Override
	public void delete(BackQnaVO backQnaVO, String realPath) {
		BackQnaVO qnaVO = backQnaDAO.selectByIdx(backQnaVO.getBack_Qna_Idx());
		System.out.println(qnaVO);
		log.info("{}의 delete 호출 : {}", this.getClass().getName(), backQnaVO + "\n" + realPath);
		BackQnaFileVO file = backQnaFileDAO.selectFiles(qnaVO.getBack_Qna_Idx());
		log.info("{}의backQnaVO.getBack_Qna_Idx() : {}", qnaVO.getBack_Qna_Idx());
		//db파일 삭제
		backQnaFileDAO.deleteByIdx(file.getBack_Qna_Idx());
		log.info("{}의file.getBack_Qna_Idx() : {}",file.getBack_Qna_Idx());
		//실제 파일 삭제
		File realFile = new File(realPath + File.separator + file.getBack_Qnafile_SaveName());
		realFile.delete();
		
		if (qnaVO != null) {
			System.out.println("qnaVO.getBack_Qna_Idx()   :  " + qnaVO.getBack_Qna_Idx() );
			backQnaDAO.delete(qnaVO.getBack_Qna_Idx());
			System.out.println("qnaVO.getBack_Qna_Idx()" + qnaVO.getBack_Qna_Idx());
		}
	}

	@Override
	public BackQnaVO selectByIdx(int idx) {
		log.info("{}의 selectByIdx 호출 : {}", this.getClass().getName(), idx);
		BackQnaVO backQnaVO = backQnaDAO.selectByIdx(idx); // 글 1개를 가져온다.
		// 그 글에 해당하는 첨부파일의 정보를 가져온다.
		if (backQnaVO != null) {
			BackQnaFileVO list = backQnaFileDAO.selectFiles(idx);
			backQnaVO.setFile(list);
		}
		log.info("{}의 selectByIdx 리턴 : {}", this.getClass().getName(), backQnaVO);
		return backQnaVO;
	}

	@Override
	public QnaPagingVO<BackQnaVO> selectList(CommVO commVO) {
		log.info("{}의 selectList 호출 : {}", this.getClass().getName(), commVO);
		System.out.println(backQnaDAO.selectCount());
		QnaPagingVO<BackQnaVO> pagingVO = null;
		try {
			// 전체 개수구하기
			int totalCount = backQnaDAO.selectCount();
			// 페이지 계산
			pagingVO = new QnaPagingVO<>(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize(), totalCount);
			// 글 읽어오기
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("startNo", pagingVO.getStartNo());
			map.put("pageSize", pagingVO.getPageSize());
			List<BackQnaVO> list = backQnaDAO.selectList(map);
			// 해당글들의 첨부파일 정보를 넣어준다.
			if (list != null && list.size() > 0) {
				for (BackQnaVO vo : list) {
					// 해당글의 첨부파일 목록을 가져온다.
					BackQnaFileVO fileList = backQnaFileDAO.selectFiles(commVO.getIdx());
					// vo에 넣는다.
					vo.setFile(fileList);
				}
			}
			// 완성된 리스트를 페이징 객체에 넣는다.

			pagingVO.setList(list);
			pagingVO.setNamelist(backQnaDAO.selectUserName());
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

	@Override
	public List<QnaUserNameVO> selectUserName() {
		log.info("{}의 selectByIdx 호출", this.getClass().getName());
		List<QnaUserNameVO> userNameList = null;
		userNameList = backQnaDAO.selectUserName();
		log.info("{}의 selectByIdx 리턴 : {}", this.getClass().getName(), userNameList);
		return userNameList;
	}

	@Override
	public int selectSeq() {
		int seq = 0;
		seq = backQnaDAO.selectSeq();
		return seq;
	}
}
