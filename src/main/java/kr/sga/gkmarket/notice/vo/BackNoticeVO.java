package kr.sga.gkmarket.notice.vo;

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
public class BackNoticeVO {
	//공지사항
	private int back_Notice_Idx;
	private String back_Notice_Subject;
	private String back_Notice_Content;
	private Date back_Notice_RegDate;
	private String back_Notice_Col1;
	private String back_Notice_Col2;
	private String back_Notice_Col3;
	// 파일 
	private int back_Noticefile_Idx;
	private String back_Noticefile_SaveName;
	private String back_Noticefile_OriName;
	private int back_Notice_Ref;
	
	
	}
