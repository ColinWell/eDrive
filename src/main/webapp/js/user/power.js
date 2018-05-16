/**
 * Created by Colin on 2018/3/28.
 */
$(document).ready(function () {
    var p = $(".popup").prompt21();
    queryPower();
    $(".add-popup").on("click", function () {
        p.getData(function (err, data) {
            console.log(err);
            console.log(data);
            $.ajax({
                url:"/user/addUser.do",
                type:"POST",
                contentType: "application/json", // 参数类型，必须有415
                data:JSON.stringify({
                    userId:data.name.id,
                    userName:data.name.name,
                    userPassword:data.name.password,
                    userRole:data.name.role
                }),
                success:function (jsonResult) {
                    if(jsonResult.result == "true"){
                        alert("添加成功");
                        location.reload();
                    }else{
                        if(jsonResult.reason == "userId"){
                            alert("添加失败，用户ID已存在！");
                        }
                    }
                }
            })
        });
    });
});
function queryPower() {
    if($("#powerQuery").hasClass("dataTable")){
        $("#powerQuery").dataTable().fnClearTable();
        $("#powerQuery").dataTable().fnDestroy();
    }
    $("#powerQuery").DataTable({
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
        // "language": {
        //     "processing": "DataTables is loading",
        //     // 当前页显示多少条
        //     "lengthMenu": "Display _MENU_ records",
        //     // _START_（当前页的第一条的序号） ,_END_（当前页的最后一条的序号）,_TOTAL_（筛选后的总件数）,
        //     // _MAX_(总件数),_PAGE_(当前页号),_PAGES_（总页数）
        //     "info": "Showing page _PAGE_ of _PAGES_",
        //     // 没有数据的显示（可选），如果没指定，会用zeroRecords的内容
        //     "emptyTable": "No data available in table",
        //     // 筛选后，没有数据的表示信息，注意emptyTable优先级更高
        //     "zeroRecords": "No records to display",
        //     // 千分位的符号，只对显示有效，默认就是","  一般不要改写
        //     //"thousands": "'",
        //     // 小数点位的符号，对输入解析有影响，默认就是"." 一般不要改写
        //     //"decimal": "-",
        //     // 翻页按钮文字控制
        //     "paginate": {
        //         "first": "First page",
        //         "last": "Last page",
        //         "next": "Next",
        //         "previous": "Previous"
        //     },
        //     // Client-Side用，Server-Side不用这个属性
        //     "loadingRecords": "Please wait - loading..."
        // },
        // 服务器端处理方式
        "serverSide": true,
        // ajax选项 可以直接简单指定成请求的文件
        //"ajax": "data.json",
        // 也可以用对象来配置，更加灵活
        "ajax": {
            type:"POST",
            // url可以直接指定远程的json文件，或是MVC的请求地址 /Controller/Action
            url:"/user/queryPower.do",
            contentType:"application/json",
            async:false,
            // 传给服务器的数据，可以添加我们自己的查询参数
            data:function(d){
                d.userId = $("input[name=userId]").val();
                d.userName = $("input[name=userName]").val();
                return JSON.stringify(d);
            }
            //用于处理服务器端返回的数据。 dataSrc是DataTable特有的
            // dataSrc: function (json) {
            //     // if (myJson.timeout) {
            //     //     return "";
            //     // }
            //     console.log("dataSrc "+json);
            //     return json;
            // }
            // success:function (result) {
            //     console.log(result);
            //     return result;
            // }
        },
        "columns": [
            {"data": "id", "bSortable": false},
            {"data": "userId", "bSortable": false},
            {"data": "userName", "bSortable": false},
            {"data": "userRole", "bSortable": false}
        ],
        "columnDefs": [
            {
                "targets": [3],
                "data": "userRole",
                "render": function(data, type, row, full) {
                    console.log(data);
                    var result = "<select id='"+row.id+"' selectRole='"+row.userRole.join(',')+"' multiple='multiple'>";
                    if(data.indexOf("ROLE_USER") != -1){
                        result += "<option value='ROLE_USER' selected='selected'>普通管理员</option>";
                    }
                    else{
                        result += "<option value='ROLE_USER' >普通管理员</option>";
                    }
                    if(data.indexOf('ROLE_ADMIN') != -1){
                        result+="<option value='ROLE_ADMIN' selected='selected'>超级管理员</option>";
                    }
                    else{
                        result+="<option value='ROLE_ADMIN'>超级管理员</option>";
                    }
                    result+="</select>";
                    return result;
                }
            },
            {
                "targets": [4],
                "data": "id",
                "render": function(data, type, row, full) {
                    $("#"+row.id).multipleSelect({
                        width:190
                    });
                    return "<a  class='edit-popup' onclick='editRole("+row.id+")'>修改</a>";
                }
            }
        ]
    });
}
function editRole(id) {
    // var selectVal = "";
    // $("#"+id+" option:selected").each(function(){
    //     alert($(this).text()); // 文本值
    //     alert($(this).val()); // value
    //     if(selectVal == ""){
    //         selectVal += $(this).val();
    //     }
    //     else {
    //         selectVal += "," + $(this).val();
    //     }
    // });
    var defaultRole = $("#"+id).attr("selectRole");
    var change = $("#"+id).multipleSelect("getSelects");
    if(defaultRole == change){
        alert("请注意，该用户权限无变化");
        return;
    }else {
        //alert("Selected texts: " + $("#" + id).multipleSelect("getSelects", "text"));
        $.post("/user/editRole.do",{id:id,userRole:change.join(',')},function(jsonResult){
            if(jsonResult == true){
                alert("修改成功");
                //$("#powerQuery").DataTable().ajax.reload(null,false);
                queryPower();
            }
            else{
                alert("请注意，该用户权限无变化");
            }
        });
    }
}