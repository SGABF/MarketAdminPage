package kr.green.sga.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class AdminVO {
	private int adminIdx;
	private String admin_Id;
	private String admin_Password;
	private String admin_Name;
	private String amin_RoleGroup;
	private String admin_Col1; 
	private String admin_Col2; 
	private String admin_Col3; 
}
