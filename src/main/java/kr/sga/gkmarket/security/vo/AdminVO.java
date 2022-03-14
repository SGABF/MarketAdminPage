package kr.sga.gkmarket.security.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
public class AdminVO {
	private int admin_idx;
	private String admin_id;
	private String admin_password;
	private String admin_name;
	private String admin_roleGroup;
	private String admin_col1;
	private String admin_col2;
	private String admin_col3;
}
