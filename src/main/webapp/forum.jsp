<%@ page import="forum.pojo.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.pojo.Category" %>
<%@ page import="forum.pojo.User" %>
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

            <%--            <div class="cloneBlock category">--%>
            <%--            </div>--%>

            <br/>
            <br/>

            <div class="category">
                <div class="text">我的最愛</div>
                <div class="cloneBlock">
                    <%
                        User user = (User) session.getAttribute("user");
                        if (user != null) {
                            ArrayList<Category> CCs = (ArrayList<Category>) request.getAttribute("CCs");
                            System.out.println(CCs);
                            for (Category cc : CCs) {
                    %>
                    <div class="items category<%=cc.getId()%>">
                        <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=cc.getId()%>">
                            <span class="<%=cc.getImg()%> item"> <%=cc.getCategory()%></span>
                        </a>
                        <span class="addCollections" data-value="<%=cc.getId()%>">收藏</span>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                </br>
                <%
                    ArrayList<Category> LCs = (ArrayList<Category>) request.getAttribute("LCs");
                    ArrayList<Category> HCs = (ArrayList<Category>) request.getAttribute("HCs");
                %>
                <div class="text">幼兒生</div>
                <% for (Category lc : LCs) {%>
                <div class="items category<%=lc.getId()%>">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=lc.getId()%>">
                        <span class="<%=lc.getImg()%> item"> <%=lc.getCategory()%></span>
                    </a>
                    <span class="addCollections" data-value="<%=lc.getId()%>">收藏</span>
                </div>
                <%}%>

                <div class="text">大學生</div>
                <% for (Category hc : HCs) {%>
                <div class="items category<%=hc.getId()%>">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=hc.getId()%>">
                        <span class="<%=hc.getImg()%> item"> <%=hc.getCategory()%></span>
                    </a>
                    <span class="addCollections" data-value="<%=hc.getId()%>">收藏</span>
                </div>
                <%}%>
            </div>
        </section>

        <section class="right">
            <%
                ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
            %>
            <% for (Post p : posts) { %>
            <article>
                <div class="d1">
                    <div class="user"><%=p.getUserName()%>
                    </div>
                    </br>
                    <div class="date"><%=p.getTimestamp()%>(<%=p.getPostId()%>)
                    </div>
                </div>
                <div class="d2" data-value="<%=p.getPostId()%>">
                    <div class="category">《<%=p.getCategory()%>》</div>
                    <div class="topic"><%=p.getTopic()%>
                    </div>
                    <p class="content"><%=p.getContent()%>
                    </p>
                </div>
                <hr/>
                <div class="d3">
                    <div class="like" data-value="<%=p.getPostId()%>">
                        <i class="fa-solid fa-thumbs-up"></i>
                        <span class="showLike"><%=p.getLike()%></span>
                    </div>
                    <!--                    <img src=""/>-->
                </div>
            </article>
            <%}%>
            <template id="post-template">
                <article>
                    <div class="d1">
                        <div class="user"></div>
                        </br>
                        <div class="date"></div>
                    </div>
                    <div class="d2" data-value="">
                        <div class="category">《》</div>
                        <div class="topic"></div>
                        <p class="content"></p>
                    </div>
                    <hr/>
                    <div class="d3">
                        <div class="like" data-value="">
                            <i class="fa-solid fa-thumbs-up"></i>
                            <span class="showLike"></span>
                        </div>
                        <!--                    <img src=""/>-->
                    </div>
                </article>
            </template>


        </section>
        <div class="test"></div>
    </main>
</div>

<form action="/forumServlet">

</form>
</body>

<script>
    likeListener();

    //讓like添加監聽器click
    function likeListener() {
        const likes = document.querySelectorAll(".like");
        // console.log(likes);
        for (const like of likes) {
            like.addEventListener("click", () => {
                const showLike = like.querySelector(".showLike");
                // console.log(showLike);
                // console.log(like.dataset.value);
                fetch("http://localhost:8080/elitebaby/forum/likeclick?postId=" + like.dataset.value)
                    .then(response => response.text())
                    .then(text => JSON.parse(text))
                    .then(data => {
                        // console.log(data);
                        if (data === "login") {
                            window.location.href = "http://localhost:8080/elitebaby/login.jsp";
                        }
                        if (typeof data === "number") {
                            showLike.innerText = data;
                        }
                    })
            })
        }
    }

    //收藏版塊，點選自己移除
    const cloneBlock = document.querySelector(".cloneBlock");

    function removeCloneItem() {
        // console.log("remove監聽器產生");
        const cloneItems = cloneBlock.querySelectorAll(".items");
        for (let cloneItem of cloneItems) {
            cloneItem.addEventListener("click", () => {
                cloneItem.remove();
            })
        }
    }

    removeCloneItem();
    //category產生收藏功能
    const collections = document.querySelectorAll(".addCollections")
    for (const coll of collections) {
        coll.addEventListener("click", () => {
            fetch("http://localhost:8080/elitebaby/forum/addCategoryCollect?categoryId=" + coll.dataset.value)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    // console.log(data);
                    if (data === "login") {
                        window.location.href = "http://localhost:8080/elitebaby/login.jsp";
                    }
                    if (data === true) {
                        // window.alert("成功收藏")
                        //產生收藏文章 clone
                        const cloneCateId = ".category" + coll.dataset.value;
                        const cloneCate = cloneBlock.querySelector(cloneCateId);
                        if (cloneCate == null) {
                            const collClone = coll.parentElement.cloneNode(true);
                            cloneBlock.append(collClone);
                            removeCloneItem();
                        }
                    }
                    if (data === false) {
                        // window.alert("取消收藏")
                        //移除收藏文章 clone
                        const cloneCateId = ".category" + coll.dataset.value;
                        const cloneCate = cloneBlock.querySelector(cloneCateId);
                        if (cloneCate != null) {
                            cloneCate.remove();
                        }
                    }
                })
        })
    }
    //點文章d2區塊，可打開文章
    const template = document.querySelector("#post-template").content;//抓取文章模板
    const postsD2 = document.querySelectorAll(".d2")//選取所有文章的d2區塊
    for (const p of postsD2) {
        p.addEventListener("click", () => {
            fetch("http://localhost:8080/elitebaby/forum/postBean?postId=" + p.dataset.value)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    console.log(data); //OK
                    const post = data.post;
                    const msgs = data.msgs;
                    const panel = document.querySelector(".right");
                    panel.innerText = '';
                    var postClone = template.cloneNode(true);
                    postClone.querySelector(".user").textContent = post.userName;
                    postClone.querySelector(".date").textContent = post.timestamp;
                    postClone.querySelector(".d2").setAttribute('data-value', post.postId);
                    postClone.querySelector(".category").textContent = "《"+post.category+"》";
                    postClone.querySelector(".topic").textContent = post.topic;
                    postClone.querySelector(".content").textContent = post.content;
                    postClone.querySelector(".d3").setAttribute('data-value', post.postId);
                    postClone.querySelector(".like").setAttribute('data-value', post.postId);
                    postClone.querySelector(".showLike").textContent = post.like;
                    panel.appendChild(postClone);
                    likeListener();
                })
        })
    }
</script>
</html>
