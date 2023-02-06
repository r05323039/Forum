<%@ page import="forum.pojo.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.pojo.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Forum</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
            integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    />
    <link type="text/css" rel="stylesheet" href="http://localhost:8080/elitebaby/forum.css"/>
</head>
<body>
<header>Header</header>

<div class="sticky">
    <div class="search">
        <a href="http://localhost:8080/elitebaby/forum/like">GetLike</a>
        <a href="http://localhost:8080/elitebaby/forum/likeclean">CleanLike</a>
        <input class="input-text" type="text" placeholder="文字搜尋"/>
        <span type="submit" id="search-text">搜尋按鈕</span>
    </div>
    <% boolean order = (boolean) request.getAttribute("order");
        String show_order = (order) ? "熱門" : "最新";
    %>
    <div class="switch">文章排序:<a href="http://localhost:8080/elitebaby/forum/home?order=${order}&switch=1"><%=show_order%>
    </a></div>
    <div class="post"><img src="" alt="圖"/>我要發文</div>
    <div class="follow"><img src="" alt="圖"/>收藏</div>
</div>
</div>
<div class="before-main">
    <main>
        <section class="left">
            <div class="collection">
                <div class="text">追蹤看板</div>
                <%--                <%--%>
                <%--                    ArrayList<Category> svgs = (ArrayList<Category>) request.getAttribute("images");--%>
                <%--                    for (Category svg : svgs) {--%>
                <%--                %>--%>
                <div class="item">
                    <%--                    這邊要做收藏的，但邏輯一樣--%>
                    <%--                    <span class="<%=svg.getImg()%>"><%=svg.getCategory()%></span>--%>
                </div>
                <%--                <%}%>--%>
            </div>

            <br/>
            <br/>
            <%
                ArrayList<Category> LCs = (ArrayList<Category>) request.getAttribute("LCs");
                ArrayList<Category> HCs = (ArrayList<Category>) request.getAttribute("HCs");
            %>
            <div class="category">
                <div class="text">幼兒生</div>

                <% for (Category lc : LCs) {%>
                <div class="item">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=${order}&categoryid=<%=lc.getId()%>">
                        <span class="<%=lc.getImg()%> items"> <%=lc.getCategory()%></span>
                        <button>收藏</button>
                    </a>
                </div>
                <%}%>

                <div class="text">大學生</div>

                <% for (Category hc : HCs) {%>
                <div class="item">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=${order}&categoryid=<%=hc.getId()%>">
                        <span class="<%=hc.getImg()%> items"> <%=hc.getCategory()%></span>
                        <button>收藏</button>
                    </a>
                </div>
                <%}%>

            </div>
        </section>

        <section class="right">
            <%
                ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
            %>
            <% for (Post p : posts) { %>
            <article id="post">
                <div class="d1">
                    <div class="category" id="category">《<%=p.getCategory()%>》</div>
                    <div class="topic" id="topic"><%=p.getTopic()%>--<%=p.getPostId()%>
                    </div>
                </div>
                <div class="d2">
                    <div class="user" id="user"><%=p.getUserId()%>
                    </div>
                    <div class="date" id="date"><%=p.getTimestamp()%>
                    </div>
                    <div class="like" id="like">
                        <a id = "" href="http://localhost:8080/elitebaby/forum/likeclick?postid=<%=p.getPostId()%>">
                            <i class="fa-solid fa-thumbs-up"></i>
                            <%=p.getLike()%>
                        </a>
                    </div>
                </div>
                <hr/>
                <div class="d3">
                    <p class="content" id="content"><%=p.getContent()%>
                    </p>
                    <!--                    <img src=""/>-->
                </div>
            </article>
            <%}%>
            <div id="post-container"></div>
        </section>
    </main>
</div>

<form action="/forumServlet">

</form>
</body>

<script>
<%--    &lt;%&ndash;文章排序&ndash;%&gt;--%>
<%--    const swi = document.getElementById("switch");--%>
<%--    swi.addEventListener("click", function () {--%>
<%--        if (swi.innerText === "最新") {--%>
<%--            swi.href = "http://localhost:8080/elitebaby/forum/popular";--%>
<%--        } else {--%>
<%--            swi.href = "http://localhost:8080/elitebaby/forum/home";--%>
<%--        }--%>
<%--    });--%>

<%--jsp每篇迴圈產生的category及post要藏id(p.getPostId())，方便JS抓--%>
<%--按like時，js抓postId組網址href，fetch(forum/click) --%>
<%--後端  返回最新讚數   前端再把最新數字填入--%>

<%--寫一個 ajaxFilter，過濾登入，@WebFilter("/forum/likeclick")，
失敗時不要直接 response.sendRedirect("http://localhost:8080/elitebaby/login.jsp");
而是返回JSON通知失敗訊息，再fetch寫出返回失敗訊息時，從前端導頁 href="http://localhost:8080/elitebaby/login.jsp")
</script>
</html>
