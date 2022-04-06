package kr.sga.gkmarket.main.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

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
	
	//유저 가입 정보
	private int userInsertDate;
	private List<Integer> userAge;
	private int month;
	

}
