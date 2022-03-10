package kr.green.sga.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class xmlroleVO {
	private int xmlRole_Idx;
	private String xmlRole_XmliFlePath;
	private String xmlRole_Col1;
	private String xmlRole_Col2;
	private String xmlRole_Col3;
	}
