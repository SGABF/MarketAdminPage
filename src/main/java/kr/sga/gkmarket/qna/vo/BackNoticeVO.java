package kr.sga.gkmarket.qna.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class BackNoticeVO {
	private int backNoticeIdx;
	private String back_Notice_Subject;
	private String back_Notice_Content;
	private String back_Notice_RegDate;
	private String back_Notice_Col1;
	private String back_Notice_Col2;
	private String back_Notice_Col3;
	}