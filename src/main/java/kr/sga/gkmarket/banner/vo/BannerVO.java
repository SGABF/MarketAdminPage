package kr.sga.gkmarket.banner.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class BannerVO {
	private int banner_idx;
	private int banner_show;
	private String banner_saveName;
	private String banner_oriName;
	private String banner_Col1;
	private String banner_Col2;
	private String banner_Col3;
	}
