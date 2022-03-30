package kr.sga.gkmarket.qna.vo;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class BackQnaFileVO {
	private int back_Qnafile_Idx;
	private String back_Qnafile_SaveName;
	private String back_Qnafile_OriName;
	private int back_Qna_Idx;
	private String back_Qna_Col1;
	private String back_Qna_Col2;
	private String backvQna_Col3;
	}
