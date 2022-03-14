package kr.sga.gkmarket.qna.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class BackQnaReplyVO {
	private int back_Qna_Reply_Idx;
	private String back_Qna_Reply_Content;
	private int back_Qna_Idx;
	private String back_Qna_Reply_Col1;
	private String back_Qna_Reply_Col2;
	private String back_Qna_Reply_Col3;
	}
