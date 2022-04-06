package kr.sga.gkmarket.main.vo;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class UserAgeVO {
	private int age_20;
	private int age_30;
	private int age_40;
	private int age_50;
	private int age_60;
}
