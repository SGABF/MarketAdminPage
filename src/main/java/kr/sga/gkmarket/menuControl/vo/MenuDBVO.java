package kr.sga.gkmarket.menuControl.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class MenuDBVO {
	private int menuDB_idx;
	private String menuDB_name;
	private String menuDB_use;
	private String menuDB_col1;
	private String menuDB_col2;
	private String menuDB_col3;
	}
