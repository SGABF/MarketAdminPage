<!-- 템플릿 페이지(실질적인 메인 페이지) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
    // indexControl.jsp로부터 데이터 수신
    String CONTROL = request.getParameter("CONTROL");
    String PAGE_NUM = request.getParameter("PAGE_NUM");
    System.out.println("CONTROL: " + CONTROL + ", PAGE_NUM: " + PAGE_NUM);
 
    /* CONTROL, PAGE_NUM 값을 이용해서 실제 경로 작성 */     
    // 왼쪽 메뉴
    String left = "/module/" + CONTROL + "Left.jsp";
 
    // 본문 내용
    String content = "/view/" + CONTROL + "_" + PAGE_NUM + ".jsp";
%>
</head>
<body>
 
   <div style="text-align: center;">
        <!-- 페이지 전체를 table로 구성 -->
        <table width="600" height="600" border="1" cellpadding="2" cellspacing="0">
 
            <!--  상단 메뉴 -->
            <tr height="50" width="100%">
                <td colspan="2">
                <jsp:include page="/WEB-INF/view/main/top.jsp" flush="false" />
                </td>
            </tr>
 
            <!--  왼쪽 메뉴, 메인 컨텐츠 -->
            <tr height="300" width="100%">
 
                <!--  왼쪽 메뉴 -->
                <td width="150" valing="top">
                <jsp:include page="/WEB-INF/view/main/left.jsp" flush="false" />
                </td>
 
                <!-- 메인 컨텐츠 -->
                <td>
                <jsp:include page="/WEB-INF/view/main/middle.jsp" flush="false" />
                </td>
            </tr>
 
            <!--  하단 메뉴 -->
            <tr height="45">
                <td colspan="2">
                <jsp:include page="/WEB-INF/view/main/bottom.jsp" flush="false" />
                </td>
            </tr>
 
        </table>
   </div>
 
</body>
</html>
