package kr.sga.gkmarket.main.vo;

import javax.xml.bind.annotation.XmlRootElement;

import kr.sga.gkmarket.qna.vo.BackQnaReplyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class MainViewVO {
	private int boardCount;
	private int soldOut;
	private int forSale;
	private int replyDone;
}
