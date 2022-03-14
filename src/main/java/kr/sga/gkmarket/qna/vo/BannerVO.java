package kr.sga.gkmarket.qna.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class BannerVO {
	private int banner_Idx;
	private int banner_Show;
	private String banner_SaveName;
	private String banner_OriName;
	private String banner_Col1;
	private String banner_Col2;
	private String banner_Col3;
	}
