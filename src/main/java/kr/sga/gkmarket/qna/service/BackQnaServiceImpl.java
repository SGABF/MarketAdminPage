package kr.sga.gkmarket.qna.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.qna.dao.BackQnaDAO;
import kr.sga.gkmarket.qna.dao.BackQnaFileDAO;
import kr.sga.gkmarket.qna.dao.BackQnaReplyDAO;
import kr.sga.gkmarket.qna.vo.BackQnaFileVO;
import kr.sga.gkmarket.qna.vo.BackQnaReplyVO;
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
	
	@Autowired
	public BackQnaReplyDAO backQnaReplyDAO;

	
	@Override
	public void insert(BackQnaVO backQnaVO) {
		if (backQnaVO != null) {
			backQnaDAO.insert(backQnaVO);
		}
	}

	@Override
	public void update(BackQnaVO backQnaVO, int delFiles, String realPath) {
		// 게시글 수정
		// 1. 글수정  -- 2. 파일추가 -- 3. 기존파일 삭제 
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
				BackQnaFileVO qnaFileVO = backQnaFileDAO.previousSelect(delFiles);// 1. 해당 글번호(delFiles) 의 예전파일을 읽어와서
				System.out.println("qnaFileVO : " + qnaFileVO + "\n");
				if(qnaFileVO!=null) {
					//2. 실제 서버의 파일을 삭제해 주어야 한다.
					File file = new File(realPath + File.separator+ qnaFileVO.getBack_Qnafile_SaveName());
					System.out.println("qnaFileVO : " + qnaFileVO.getBack_Qnafile_SaveName() + "\n");
					file.delete(); // 실제 파일 삭제 
					System.out.println("file:  " + file);
					backQnaFileDAO.deleteByIdx(delFiles); // db 에서 삭제
					System.out.println("qnaFileVO : " + qnaFileVO + "\n");

				}
			}
	}

	@Override
	public void delete(BackQnaVO backQnaVO, String realPath) {
		BackQnaVO qnaVO = backQnaDAO.selectByIdx(backQnaVO.getBack_Qna_Idx());
		System.out.println(qnaVO);
		log.info("{}의 delete 호출 : {}", this.getClass().getName(), backQnaVO + "\n" + realPath);
		//파일이 있으면 파일 삭제
		BackQnaFileVO file = backQnaFileDAO.selectFiles(qnaVO.getBack_Qna_Idx());
		if(file!=null) {
		log.info("{}의backQnaVO.getBack_Qna_Idx() : {}", qnaVO.getBack_Qna_Idx());
		//db파일 삭제
		backQnaFileDAO.deleteByIdx(file.getBack_Qna_Idx());
		log.info("{}의file.getBack_Qna_Idx() : {}",file.getBack_Qna_Idx());
		//실제 파일 삭제
		File realFile = new File(realPath + File.separator + file.getBack_Qnafile_SaveName());
		realFile.delete();
		}
		// 댓글이 있으면 댓글 삭제
		BackQnaReplyVO replyVO = backQnaReplyDAO.selectComment(qnaVO.getBack_Qna_Idx());
		if(replyVO!=null) {
		log.info("{}의qnaVO.getBack_Qna_Idx() : {}  "+ qnaVO.getBack_Qna_Idx() + " 번게시물의 댓글 삭제");
		backQnaReplyDAO.delete(qnaVO.getBack_Qna_Idx());
		}
		// 게시글 삭제
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
	public List<BackQnaVO> selectList() {
		log.info("backQnaService selectList() 호출 : ");
		List<BackQnaVO> list = null;
		list = backQnaDAO.selectList();
		if(list==null) {
			log.info("backQnaService selectList() 등록된 글이 없음.");
			list = new ArrayList<BackQnaVO>();
		}
		
		log.info("backQnaService selectList() 리턴. : " + list);
		return list;
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

	@Override
	public void doneComment(int idx) {
		log.info("BackQnaService 의 doneComment 실행 ");
		backQnaDAO.replyDone(idx);
	}

	@Override
	public void notYetComment(int idx) {
		log.info("BackQnaService 의 notYetComment 실행 ");
		backQnaDAO.replyDone(idx);
	}

	@Override
	public void updateQna(BackQnaVO backQnaVO) {
		log.info("BackQnaService 의 updateQna 실행 ");
		backQnaDAO.update(backQnaVO);
	}
}
