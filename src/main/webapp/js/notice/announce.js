/**
 * Created by Colin on 2018/4/13.
 */
$(document).ready(function () {
    var p = $("#add-popup").prompt21();
    queryAnnounce();
    $(".add-popup").on("click", function () {
        p.getData(function (err, data) {
            console.log(err);
            console.log(data);
            $.ajax({
                url:"/notice/pubAnnounce.do",
                type:"POST",
                contentType: "application/json", // 参数类型，必须有415
                data:JSON.stringify({
                    announceType:data.name.type,
                    announceTitle:data.name.title,
                    announceContent:data.name.content
                }),
                success:function (jsonResult) {
                    if(jsonResult == true){
                        alert("发布成功");
                        location.reload();
                    }else{
                        alert("发布失败");
                    }
                }
            })
        });
    });

});
function queryAnnounce() {
    if($("#announceQuery").hasClass("dataTable")){
        $("#announceQuery").dataTable().fnClearTable();
        $("#announceQuery").dataTable().fnDestroy();
    }
    $("#announceQuery").DataTable({
        // 是否允许检索
        "searching": false,
        // 是否允许排序
        "ordering": true,
        // 件数选择功能 默认true
        "lengthChange": false,
        // 件数选择下拉框内容
        "lengthMenu": [10, 25, 50, 75, 100],
        // 每页的初期件数 用户可以操作lengthMenu上的值覆盖
        "pageLength": 10,
        // 是否表示 "processing" 加载中的信息，这个信息可以修改
        "processing": true,
        // 服务器端处理方式
        "serverSide": true,
        // ajax选项 可以直接简单指定成请求的文件
        //"ajax": "data.json",
        // 也可以用对象来配置，更加灵活
        "ajax": {
            type:"POST",
            // url可以直接指定远程的json文件，或是MVC的请求地址 /Controller/Action
            url:"/notice/queryAnnounce.do",
            contentType:"application/json",
            async:false,
            // 传给服务器的数据，可以添加我们自己的查询参数
            data:function(d){
                d.id = "123";
                d.dateBegin = $("input[name=dateBegin]").val();
                d.dateEnd = $("input[name=dateEnd]").val();
                d.type = $("select[name=announceType] option:selected").attr("value");
                d.userName = $("input[name=pubUserName]").val();
                d.keyWords = $("input[name=keyWords]").val();
                return JSON.stringify(d);
            }
        },
        "columns": [
            {"data": "id", "bSortable": false},
            {"data": "type"},
            {"data": "date"},
            {"data": "title", "bSortable": false},
            {"data": "content", "bSortable": false},
            {"data": "userName"}
        ],
        "columnDefs": [
            {
                "targets": [2],
                "data": "pubDate",
                "render": function(data, type, full) {
                    var date = $.formatDate("yyyy/MM/dd hh:mm:ss",new Date(data));
                    return date;
                }
            },
            {
                "targets":[1],
                "data": "type",
                "render": function(data, type, full) {
                    var result;
                    switch (data){
                        case 1:
                            result = '紧急';
                            break;
                        case 2:
                            result = '一般';
                            break;
                    }
                    return result;
                }
            }
        ]
    });
}
