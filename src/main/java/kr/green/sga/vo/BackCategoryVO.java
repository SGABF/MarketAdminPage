package kr.green.sga.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class BackCategoryVO {
	private int back_Category_Tdx;
	private String back_Category_Name;
	private int back_Category_Use;
	private String back_Category_Col1;
	private String back_Category_Col2;
	private String back_Category_Col3;
	}
