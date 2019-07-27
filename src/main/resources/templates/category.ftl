<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/plugins/open-iconic/font/css/open-iconic-bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/left.css"/>
    <link rel="stylesheet" href="/static/css/middle.css"/>
    <link rel="stylesheet" href="/static/css/footer.css"/>
    <link rel="stylesheet" href="/static/css/category.css"/>
    <script src="/static/plugins/jquery/jquery.min.js"></script>
    <script src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
    <meta name="keywords" content="分类,category"/>
    <link rel="icon" href="${Application.setting.head}"/>
    <title>${title}</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="left">
                <img src="${Application.setting.head}" class="rounded-circle">
                <div class="name">${Application.setting.name?html}</div>
                <hr/>
                <div class="signature">${Application.setting.signature?html}</div>
                <div class="menu">
                    <div id="item" class="hidden">
                    <#list Application.menu as menu>
                        <a class="item btn <#if menu.url == select>select</#if>"
                           href="${menu.url}">${menu.name?html}</a>
                    </#list>
                    </div>
                    <a class="btn-item btn">
                        <span class="oi oi-menu" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-9">
            <div class="middle">
                <div class="header">
                    文章分类
                </div>
                <div class="body">
                    <#list categoryList as category>
                        <div class="category">
                            <a class="btn btn-secondary" href="/category/${category.code}">
                                <span>${category.name}</span>
                                <span class="badge badge-light">${category.articleCount}</span>
                            </a>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="padding">
            </div>
        </div>
    </div>
</div>
<footer>${Application.setting.copyright}</footer>
<script src="/static/js/left.js"></script>
</body>
</html>