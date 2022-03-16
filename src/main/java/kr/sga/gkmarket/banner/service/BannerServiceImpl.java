package kr.sga.gkmarket.banner.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.sga.gkmarket.banner.dao.BannerDAO;
import kr.sga.gkmarket.banner.vo.BannerVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("bannerService")
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private BannerDAO bannerDAO;
	
	@Override
	public List<BannerVO> selectBannerList() {
		log.info("{}의 selectList 호출", this.getClass().getName());
		
		List<BannerVO> list = bannerDAO.selectBannerList();
		
		log.info("{}의 selectList 리턴 : {}", this.getClass().getName(), list);
		return list;
	}

	@Override
	public void BannerAdd(BannerVO bannerVO) {
		log.info("{}의 BannerAdd 호출 : {}", this.getClass().getName(), bannerVO);
		
		if(bannerVO != null) {
			bannerDAO.insert(bannerVO);
		}		
	}

	@Override
	public void BannerDelete(int bannerId, String realPath) {
		log.info("{}의 BannerDelete 호출 : {}", this.getClass().getName(), bannerId, realPath);
		BannerVO tmpVO = null;
		tmpVO = bannerDAO.selectByIdx(bannerId);
		
		if(tmpVO != null) {
			File file = new File(realPath + File.separator + tmpVO.getBanner_saveName());
			log.info("file realpath : " + realPath);
//			file.delete();
//			bannerDAO.delete(bannerId);
		}
	}
	

}
