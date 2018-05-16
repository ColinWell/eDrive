/**
 * Created by Colin on 2018/3/18.
 */
$(function () {
   queryPaymentInfo();
});

function queryPaymentInfo() {
    if($("#paymentQuery").hasClass("dataTable")){
        $("#paymentQuery").dataTable().fnClearTable();
        $("#paymentQuery").dataTable().fnDestroy();
    }
    $("#paymentQuery").DataTable({
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

        "serverSide": true,

        "ajax": {
            type:"POST",
            url:"/payment/queryPaymentInfo.do",
            contentType:"application/json",
            async:false,
            // 传给服务器的数据，可以添加我们自己的查询参数
            data:function(d){
                d.dateBegin=$("input[name=dateBegin]").val();
                d.dateEnd=$("input[name=dateEnd]").val();
                d.paymentType=$("select[name=paymentType] option:selected").attr("value");
                d.paymentState=$("select[name=paymentState] option:selected").attr("value");
                d.roomId=$("input[name=roomId]").val();
                d.roomName=$("input[name=roomName]").val();
                return JSON.stringify(d);
            }
        },
        "columns": [
            {"data": "id", "bSortable": false},
            {"data": "roomId", "bSortable": false},
            {"data": "roomName", "bSortable": false},
            {"data": "date"},
            {"data": "type", "bSortable": false},
            {"data": "state", "bSortable": false}
        ],
        "columnDefs": [
            {
                "targets": [3],
                "data": "date",
                "render": function(data, type, full) {
                    var date = $.formatDate("yyyy/MM/dd",new Date(data));
                    return date;
                }
            },
            {
                "targets": [4],
                "data": "type",
                "render": function(data, type, row, full) {
                    var type;
                    switch (data){
                        case 0:
                            type = '全部';
                            break;
                        case 1:
                            type = '物业费';
                            break;
                        case 2:
                            type = '公共水电费';
                            break;
                        case 3:
                            type = '停车费';
                            break;
                    }
                    return type;
                }
            },
            {
                "targets": [5],
                "data": "state",
                "render": function(data, type, row, full) {
                    var state;
                    switch (data){
                        case 0:
                            state = '未缴费';
                            break;
                        case 1:
                            state = '已缴费';
                            break;
                    }
                    return state;
                }
            }
        ]
    });
}
