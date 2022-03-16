package kr.sga.gkmarket.userbanned.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class UserBannedVO {
	private int user_Idx;
	private String user_Id;
	private int user_banned;
}