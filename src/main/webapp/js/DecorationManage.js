$(function(){
    $.ajax({
        url:"/getDRecordByS.do",
        type:"POST",
        contentType:"application/json",
        success: function(jsonResult){
            var item;
            var status;
            var s;
            $.each($.parseJSON(jsonResult),function (i,result) {
                if(result['DecorationApplication_Status']==1){
                    s="未审核";
                }
                if(result['DecorationApplication_Status']==2){
                     s="未通过";
                 }
                 if(result['DecorationApplication_Status']==3){
                     s="通过";
                 }
                item="<tr><td>"+(i+1)+"</td><td>"+result['DecorationApplication_Id']+"</td><td>"+result['logname']+"</td><td>"+result['DecorationApplication_Place']+"</td>";
                item+="<td>"+result['DecorationApplication_Phone']+"</td><td>"+s+"</td><td>"+result['DecorationApplication_StartTime']+"</td>";
                item+="<td>"+result['DecorationApplication_EndTime']+"</td><td><a class='update' style='cursor:pointer'>处理</a></td></tr>";
                $("#dTableBody").append(item);
            });

            btnBind();
        },
        error:function (jsonResult) {
            console.log("error");
            console.log(jsonResult);
        }
    });

});
window.onload=function () {
    btnBind();
}
function doneClick(id) {
    var tr=document.getElementById(id).parentNode.parentNode;
    var did=tr.cells[1];
    var Status=tr.cells[5];
    var updateBtn=tr.cells[8];
    var s="3";
    $.ajax({
        url:"/editD.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        data:JSON.stringify({
            "DecorationApplication_Id":did.innerHTML,
            "DecorationApplication_Status":s,
        }),
        success:function (jsonResult) {
            console.log(jsonResult);

            if(jsonResult == true) {
                alert("修改成功");
                window.location.reload();
            }
            else {
                alert("修改失败");
                window.location.reload();
            }
        },
        error:function (jsonResult) {
            alert("修改失败");
            window.location.reload();
        }
    })
}
function cancelClick(id) {
    var tr=document.getElementById(id).parentNode.parentNode;
    var rid=tr.cells[1];
    var Status=tr.cells[5];
    var updateBtn=tr.cells[8];
    var s="2";
    $.ajax({
        url:"/editD.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        data:JSON.stringify({
            "DecorationApplication_Id":rid.innerHTML,
            "DecorationApplication_Status":s
        }),
        success:function (jsonResult) {
            console.log(jsonResult);

            if(jsonResult == true) {
                alert("修改成功");
                window.location.reload();
            }
            else {
                alert("修改失败");
                window.location.reload();
            }
        },
        error:function (jsonResult) {
            alert("修改失败");
            window.location.reload();
        }
    })
}
function btnBind() {
    $(".update").click(function () {
        $(".update").unbind();
        var tr = $(this).closest("tr");
        var Status = tr.find("td:eq(5)");
        var updateBtn=tr.find("td:eq(8)");
        var done = document.createElement("a");
        done.setAttribute("class", "done");
        done.setAttribute("id", "doneBtn");
        done.innerHTML = "通过";
        done.setAttribute("onclick", "doneClick('doneBtn')");

        var cancel = document.createElement("a");
        cancel.setAttribute("class", "cancel");
        cancel.setAttribute("id", "cancelBtn")
        cancel.innerHTML = "拒绝";
        cancel.setAttribute("onclick", "cancelClick('cancelBtn');");

        updateBtn.empty();
        updateBtn.append(done);
        updateBtn.append(cancel);
    })

}
function queryDecoration() {
    alert("yes");
    var dDateS=$("input[name=dDateS]").val();
    var dDateE=$("input[name=dDateE]").val();
    var dId=$("input[name=dId]").val();
    var dName=$("input[name=dName]").val();
    var dStatus=$("select[name=dStatus] option:selected").val();
    var dPhone=$("input[name=dPhone]").val();
    alert(dName);
    $.ajax({
        type:"POST",
        url:"/queryDecoration.do",
        contentType: "application/json",
        data:JSON.stringify({
            DecorationApplication_Id:dId,
            DecorationApplication_Status:dStatus,
            DecorationApplication_Phone:dPhone,
            DecorationApplication_StartTime:dDateS,
            DecorationApplication_EndTime:dDateE,
            Logname:dName
        }),
        success:function (jsonResult) {
            $.each($.parseJSON(jsonResult),function (i,result) {
                if(result['DecorationApplication_Status']==1){
                    s="未审核";
                }
                if(result['DecorationApplication_Status']==2){
                    s="未通过";
                }
                if(result['DecorationApplication_Status']==3){
                    s="通过";
                }
                item="<tr><td>"+(i+1)+"</td><td>"+result['DecorationApplication_Id']+"</td><td>"+result['logname']+"</td><td>"+result['DecorationApplication_Place']+"</td>";
                item+="<td>"+result['DecorationApplication_Phone']+"</td><td>"+s+"</td><td>"+result['DecorationApplication_StartTime']+"</td>";
                item+="<td>"+result['DecorationApplication_EndTime']+"</td><td><a class='update' style='cursor:pointer'>处理</a></td></tr>";
                $("#dTableBody").append(item);
            });
        },
        error:function (jsonResult) {
            alert("查询出错");
        }
    })
}
// function queryDecoration(){
//     if($("#decorationApplicationManage").hasClass("dataTable")){
//         $("#decorationApplicationManage").dataTable().fnClearTable();
//         $("#decorationApplicationManage").dataTable().fnDestroy();
//     }
//     $("#decorationApplicationManage").DataTable({
//         "ajax":{
//             type:"POST",
//             url:"/queryDecoration.do",
//             contentType:"application/json",
//             async:false,
//             data:function (d) {
//                 d.dDateS=$("input[name=dDateS]").val();
//                 d.dDateE=$("input[name=dDateE]").val();
//                 d.dId=$("input[name=dId]").val();
//                 d.dName=$("input[name=dName]").val();
//                 d.dStatus=$("select[name=dStatus] option:selected").val();
//                 d.dPhone=$("input[name=dPhone]").val();
//                 return JSON.stringify(d);
//             }
//         },
//         "columns":[
//             {"data": "dId", "bSortable": false},
//             {"data": "dDateS"},
//             {"data": "dDateE"},
//             {"data": "dId", "bSortable": false},
//             {"data": "dName", "bSortable": false},
//             {"data": "dStatus", "bSortable": false},
//             {"data": "dPhone", "bSortable": false},
//         ],
//         "columnDefs":[
//             {
//                 "targets": [1],
//                 "data": "dDateS",
//                 "render": function(data, type, full) {
//                     var dDateS = $.formatDate("yyyy-MM-dd",new Date(data));
//                     return dDateS;
//                 }
//             },
//             {
//                 "targets": [2],
//                 "data": "dDateE",
//                 "render": function(data, type, full) {
//                     var dDateE = $.formatDate("yyyy-MM-dd",new Date(data));
//                     return dDateE;
//                 }
//             },
//             {
//                 "targets": [5],
//                 "data": "dStatus",
//                 "render": function(data, type, row, full) {
//                     var dStatus;
//                     switch (data){
//                         case 0:
//                             dStatus = '全部';
//                             break;
//                         case 1:
//                             dStatus = '未审核';
//                             break;
//                         case 2:
//                             dStatus = '审核未通过';
//                             break;
//                         case 3:
//                             dStatus = '通过';
//                             break;
//                     }
//                     return dStatus;
//                 }
//             }
//         ]
//     });
// }