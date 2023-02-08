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
<% boolean order = (boolean) request.getAttribute("order");
    String showOrder = (order) ? "熱門" : "最新";
    String categoryId = (String) request.getAttribute("categoryId");
%>


<div class="sticky">
    <div class="search">
        <a href="http://localhost:8080/elitebaby/forum/like" style="display: none">GetLike</a>
        <a href="http://localhost:8080/elitebaby/forum/likeclean" style="display: none">CleanLike</a>
        <span>歡迎 USERNAME</span>
        <span>積分: 100</span>
    </div>

    <div class="switch">文章排序:<a
            href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&switch=1"><%=showOrder%>
    </a></div>
    <div class="post"><img src="" alt="圖"/>我要發文</div>
    <div class="follow"><img src="" alt="圖"/>收藏</div>
</div>
</div>
<div class="before-main">
    <main>
        <section class="left">
            <form style="display: inline-block" action="http://localhost:8080/elitebaby/forum/home">
                <input class="input-text" type="text" placeholder="文字搜尋" name="topic"/>
                <input type="hidden" name="order" value="<%=order%>">
                <input type="hidden" name="categoryId" value="<%=categoryId%>">
                <input type="submit" value="標題搜尋">
            </form>
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
                <div class="item category<%=lc.getId()%>">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=lc.getId()%>">
                        <span class="<%=lc.getImg()%> items"> <%=lc.getCategory()%></span>
                    </a>
                    <span class="addCollections" id="<%=lc.getId()%>">收藏</span>
                </div>
                <%}%>

                <div class="text">大學生</div>

                <% for (Category hc : HCs) {%>
                <div class="item category<%=hc.getId()%>">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=hc.getId()%>">
                        <span class="<%=hc.getImg()%> items"> <%=hc.getCategory()%></span>
                    </a>
                    <span class="addCollections" id="<%=hc.getId()%>">收藏</span>
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
                    <div class="topic" id="topic"><%=p.getTopic()%>
                    </div>
                </div>
                <div class="d2">
                    <div class="user" id="user"><%=p.getUserId()%>
                    </div>
                    <div class="date" id="date"><%=p.getTimestamp()%>(<%=p.getPostId()%>)
                    </div>
                    <div class="like">
                        <span class="spanlike like<%=p.getPostId()%>" id="<%=p.getPostId()%>">
                            <i class="fa-solid fa-thumbs-up"></i>
                            <span><%=p.getLike()%></span>
                        </span>
                    </div>
                </div>
                <hr/>
                <div class="d3 post<%=p.getPostId()%>">
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
    <%--1.jsp每篇迴圈產生的category及post要藏id(p.getPostId())，方便JS抓--%>
    // const cate5 = document.querySelector(".category5");
    // console.log(cate5);
    // const cate8 = document.querySelector(".category8");
    // console.log(cate8);
    // const post18 = document.querySelector(".post18");
    // console.log(post18);
    // const like18 = document.querySelector(".like18");
    // console.log(like18);

    <%--2.按like時，JS抓postId組網址，fetch(forum/likeclick)，後端JSON字串傳遞結果，false移轉登入，number更新讚數 --%>
    const likes = document.querySelectorAll(".spanlike");
    for (const like of likes) {
        const secondSpan = like.querySelector(":nth-child(2)");
        like.addEventListener("click", () => {

            fetch("http://localhost:8080/elitebaby/forum/likeclick?postId=" + like.id)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    console.log(data);
                    if (data === "login") {
                        window.location.href = "http://localhost:8080/elitebaby/login.jsp";
                    }
                    if (typeof data === "number") {
                        secondSpan.innerText = data;
                    }
                })
        })
    }//end

    //3.category板塊for迴圈產生收藏功能
    const collections = document.querySelectorAll(".addCollections")
    for (const coll of collections) {
        // console.log(coll.id);//ID為板塊編號
        coll.addEventListener("click", () => {
            fetch("http://localhost:8080/elitebaby/forum/categoryCollect?categoryId=" + coll.id)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    console.log(data);
                    if (data === "login") {
                        window.location.href = "http://localhost:8080/elitebaby/login.jsp";
                    }
                    if (data === true) {
                        window.alert("成功收藏")
                        //合併true跟false，fetch(forum/getCollections->後端回傳最新的收藏posts)
                        //讓收藏版塊重新載入
                    }
                    if (data === false){
                        window.alert("取消收藏")
                    }
                })
        })
    }

</script>
</html>
