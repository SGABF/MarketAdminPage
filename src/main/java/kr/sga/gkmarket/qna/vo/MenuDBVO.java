package kr.sga.gkmarket.qna.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class MenuDBVO {
	private int menuDB_Idx;
	private String menuDB_Name;
	private String menuDB_Use;
	private String menuDB_Col1;
	private String menuDB_Col2;
	private String menuDB_Col3;
	}
