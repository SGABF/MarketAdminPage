package kr.sga.gkmarket.qna.vo;
// 리스트의 페이지 계산을 하는 VO
// 모든 리스트의 페이징 방법은 동일하므로 제네릭으로 만들자.

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QnaPagingVO<T> {
	private List<T> list; // 1페이지 분량의 데이터를 담을 리스트
	private List<QnaUserNameVO> namelist;
	
	// 3개는 생성자로 넘겨 받는다.
	private int currentPage;  	// 현재페이지
	private int pageSize;		// 페이지당 글의 개수
	private int blockSize;		// 하단에 표시할 페이지 개수
	// 1개는 DB에서 알아온다.
	private int totalCount;		// 전체 글의 수
	
	// 나머지는 위의 값으로 계산을 한다.
	private int totalPage;		// 전체 페이지 수
	private int startNo;		// 시작 글번호
	private int endNo;			// 끝 글번호 --- 오라클에서만 사용
	private int startPage;		// 시작 페이지 번호
	private int endPage;		// 끝 페이지 번호 
	
	public QnaPagingVO(int currentPage, int pageSize, int blockSize, int totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		this.totalCount = totalCount;
		calc();
	}

	private void calc() {
		if(currentPage<1) currentPage = 1;
		if(pageSize<2) pageSize = 10;
		if(blockSize<2) blockSize = 10;
		
		if(totalCount>0) {
			totalPage = (totalCount-1)/pageSize + 1;
			if(currentPage>totalPage) currentPage = 1;
			
			startNo = (currentPage-1) * pageSize; // 0, 10, 20, .....
			endNo   = startNo + pageSize - 1;
			if(endNo>=totalCount) {
				endNo = totalCount-1;
			}
			
			startPage = (currentPage-1)/blockSize * blockSize + 1; // 1, 11, 21 ...
			endPage = startPage + blockSize - 1;
			if(endPage>totalPage) {
				endPage = totalPage;
			}
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	//------------------------------------------------------------------------------------
	// 몇개의 필요한 메서드를 추가한다.
	// 1. 페이지 상단의 페이지 정보를 출력해주는 메서드
	//    전체 ??개(?/?Page)
	public String getPageInfo() {
		String message = "전체 : " + totalCount + "개";
		if(totalCount>0) {
			message += "(" + currentPage + "/" + totalPage + "Page)";
		}
		return message;
	}
	
	// 2. 페이지 하단의 페이지번호들 출력해주는 메서드
	public String getPageList() {
		String message = "<nav>";
		message += "<ul class='pagination pagination-sm justify-content-center'>";
		// <%-- 이전 : 시작 페이지가 1보다 크다면 이전이 있다 --%>
		if(startPage>1) {
			message += "<li class='page-item'>";
			message += "<a class='page-link' href='#' onclick='SendPost(\"?\",{\"p\":\""+ (startPage-1) +"\",\"s\":\""+pageSize+"\",\"b\":\""+blockSize+"\"})' aria-label='Previous'>";
			message += "<span aria-hidden='true'>&laquo;</span>";
			message += "</a>";
			message += "</li>";
		}
		//  <%-- 페이지 : 시작페이지 번호부터 끝페이지 번호까지 페이지 번호 출력 --%>
		for(int i=startPage;i<=endPage;i++) {
			if(i==currentPage) {
				message += "<li class='page-item active' aria-current='page'><span class='page-link'>" + i + "</span></li>";
			}else {
				message += "<li class='page-item'><a class='page-link' href='#' onclick='SendPost(\"?\",{\"p\":\""+ (i) +"\",\"s\":\""+pageSize+"\",\"b\":\""+blockSize+"\"})'>" + i + "</a></li>";
			}
		}
		// <%-- 다음 : 마지막 페이지가 전체페이지보다 적다면 다음이 있다 --%>
		if(endPage<totalPage) {
			message += "<li class='page-item'>";
			message += "<a class='page-link' href='#' onclick='SendPost(\"?\",{\"p\":\""+ (endPage+1) +"\",\"s\":\""+pageSize+"\",\"b\":\""+blockSize+"\"})' aria-label='Next'>";
			message += "<span aria-hidden='true'>&raquo;</span>";
			message += "</a>";
			message += "</li>";
		}
		message += "</ul>";
		message += "</nav>";
		return message;
	}
	
	//------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "PagingVO [list=" + list + ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", blockSize="
				+ blockSize + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", startNo=" + startNo
				+ ", endNo=" + endNo + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

	public List<QnaUserNameVO> getNamelist() {
		return namelist;
	}

	public void setNamelist(List<QnaUserNameVO> namelist) {
		this.namelist = namelist;
	}
	
	

}
