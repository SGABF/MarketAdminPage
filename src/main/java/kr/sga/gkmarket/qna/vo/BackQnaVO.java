package kr.sga.gkmarket.qna.vo;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class BackQnaVO {
	private int back_Qna_Idx;
	private String back_Qna_Name;
	private String back_Qna_Content;
	private int back_Qna_Question;
	private Date back_Qna_RegDate;
	private int user_Idx;
	private String back_Qna_Col1;
	private String back_Qna_Col2;
	private String back_Qna_Col3;

	private BackQnaFileVO file;
}
