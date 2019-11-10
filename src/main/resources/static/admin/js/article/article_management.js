layui.use(['table', 'jquery', 'layer'], function () {
    var table = layui.table;
    var $ = layui.$;
    var layer = layui.layer;

    var parameter = {
        id: 'article-table',
        elem: '#article-table',
        url: '/admin/article/list_article',
        method: 'get',
        page: true,
        toolbar: 'default',
        parseData: function (response) {
            return {
                code: response.status,
                message: response.message,
                count: response.data.count,
                data: response.data.items
            }
        },
        cols: [[
            {type: 'checkbox'},
            {field: 'title', title: '标题', sort: true},
            {field: 'author', title: '作者', sort: true},
            {field: 'categoryName', title: '分类', sort: true},
            {field: 'url', title: 'URL', sort: true},
            {field: 'likes', title: '点赞', sort: true},
            {field: 'hits', title: '点击量', sort: true},
            {field: 'commentCount', title: '评论量', sort: true},
            {field: 'visibleStr', title: '是否显示'},
            {field: 'createDate', title: '创建日期', sort: true},
            {field: 'modifyDate', title: '修改日期', sort: true},
            {fixed: 'right', width: 270, title: '操作', toolbar: '#toolbar-col'}
        ]]
    };

    var tableIns = table.render(parameter);

    table.on('tool(article-table-1)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'show') {
            var param = [];
            param.push(data.id);
            showHiddenAjax(param, 1);
        } else if (layEvent === 'hidden') {
            var param = [];
            param.push(data.id);
            showHiddenAjax(param, 0);
        } else if (layEvent === 'export') {
            window.location.href = "/admin/article/export_article?id=" + data.id;
        } else if (layEvent === 'edit') {
            layer.open({
                type: 2,
                title: '更新文章',
                shadeClose: true,
                shade: 0.8,
                area: ['90%', '90%'],
                maxmin: true,
                content: '/admin/article/article_editor?id=' + data.id,
                cancel: function (index, layero) {
                    tableIns.reload(parameter);
                }
            });
        }
    });

    table.on('toolbar(article-table-1)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        if (obj.event === 'add') {
            layer.open({
                type: 2,
                title: '新增文章',
                shadeClose: true,
                shade: 0.8,
                area: ['90%', '90%'],
                maxmin: true,
                content: '/admin/article/article_editor',
                cancel: function (index, layero) {
                    tableIns.reload(parameter);
                }
            });
        } else if (obj.event === 'update') {
            if (checkStatus.data.length === 1) {
                layer.open({
                    type: 2,
                    title: '更新文章',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['90%', '90%'],
                    maxmin: true,
                    content: '/admin/article/article_editor?id=' + checkStatus.data[0].id,
                    cancel: function (index, layero) {
                        tableIns.reload(parameter);
                    }
                });
            } else {
                layer.open({
                    type: 0,
                    content: "只能选中一篇进行编辑"
                });
            }
        } else if (obj.event === 'delete') {
            if (checkStatus.data.length === 0) {
                layer.open({
                    type: 0,
                    content: "至少选中一条"
                });
            } else {
                layer.confirm('确认删除该文章吗', function (index) {
                    var data = [];
                    for (var i = 0; i < checkStatus.data.length; i++) {
                        data.push(checkStatus.data[i].id);
                    }
                    deleteArticleAjax(data);
                    layer.close(index);
                });
            }
        }
    });

    function deleteArticleAjax(param) {
        $.ajax({
            url: "/admin/article/delete_article",
            type: "post",
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.status === 0) {
                    tableIns.reload(parameter);
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                } else {
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                }
            },
            error: function (reponse) {
                layer.open({
                    type: 0,
                    content: "请求失败"
                });
            }
        });
    }

    function showHiddenAjax(param, visible) {
        $.ajax({
            url: "/admin/article/visible_article?visible=" + visible,
            type: "post",
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.status === 0) {
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                } else {
                    layer.open({
                        type: 0,
                        content: response.message
                    });
                }
                tableIns.reload(parameter);
            },
            error: function (response) {
                layer.open({
                    type: 0,
                    content: "服务器错误"
                });
            }
        });
    }

});