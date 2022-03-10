package kr.green.sga.vo;

import java.util.List;

public class PagingVO<T> {
	private int currentPage;
	private int pageSize;
	private int blockSize;
	private int totalCount;

	private int totalPage;
	private int startNo;
	private int endNo;
	private int startPage;
	private int endPage;

	private List<T> list;

	public PagingVO(int currentPage, int pageSize, int blockSize, int totalCount) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		this.totalCount = totalCount;
		calc();
	}

	private void calc() {
		if (currentPage <= 0) currentPage = 1;
		if (pageSize < 2) pageSize = 10;
		if (blockSize < 2) blockSize = 10;

		if (totalCount > 0) {
			totalPage = (totalCount - 1) / pageSize + 1;
			if (currentPage > totalPage) currentPage = 1;

			startNo = (currentPage - 1) * pageSize + 1; // 오라클의 경우 rownum으로 조회하므로 1부터 시작되어야 한다. 그래서 +1을 꼭해야 한다.
			endNo = startNo + pageSize - 1;
			if (endNo > totalCount) endNo = totalCount; // 이 부분도 바뀐다. 마지막 번호는 글의 개수와 같다. -1을 하면 안된다.

			startPage = (currentPage - 1) / blockSize * blockSize + 1;
			endPage = startPage + blockSize - 1;
			if (endPage > totalPage) endPage = totalPage;
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

	@Override
	public String toString() {
		return "PagingVO [currentPage=" + currentPage + ", pageSize=" + pageSize + ", blockSize=" + blockSize
				+ ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", startNo=" + startNo + ", endNo="
				+ endNo + ", startPage=" + startPage + ", endPage=" + endPage + ", list=" + list + "]";
	}

	// 페이지 상단에 표시할 메서드를 추가하자
	public String getPageInfo() {
		String result = "전체 : " + totalCount + "개";
		if (totalCount > 0)
			result += "(" + currentPage + "/" + totalPage + "Page)";
		return result;
	}

	// 하단의 페이지 리스트
	public String getPageList() {
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class='pagination pagination-sm justify-content-center'>");
		// 이전
		if(startPage>1) {
			sb.append("<li class='page-item'>");
			sb.append("<a class='page-link' href='#' aria-label='Previous' onclick='sendPost(\"?\", {\"p\":"+(startPage-1)+",\"s\":"+pageSize+",\"b\":"+blockSize+"})'>");
			sb.append("<span aria-hidden='true'>&laquo;</span>");
			sb.append("</a>");
			sb.append("</li>");
		}
		// 페이지 리스트
		for(int i=startPage;i<=endPage;i++) {
			// 현재 페이지는 링크를 걸지 않는다.
			if(i==currentPage) {
				sb.append("<li class='page-item active' aria-current='page'>");
				sb.append("<a class='page-link' href='#'>");
				sb.append(i);
				sb.append("</a>");
				sb.append("</li>");				
			}else {
				sb.append("<li class='page-item'>");
				sb.append("<a class='page-link' href='#' onclick='sendPost(\"?\", {\"p\":" + (i) + ",\"s\":"+pageSize+",\"b\":"+blockSize+"})'>");
				sb.append(i);
				sb.append("</a>");
				sb.append("</li>");				
			}
		}
		// 다음
		if(endPage<totalPage) {
			sb.append("<li class='page-item'>");
			sb.append("<a class='page-link' href='#' aria-label='Next' onclick='sendPost(\"?\", {\"p\":"+(endPage+1)+",\"s\":"+pageSize+",\"b\":"+blockSize+"})'>");
			sb.append("<span aria-hidden='true'>&raquo;</span>");
			sb.append("</a>");
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}


}
