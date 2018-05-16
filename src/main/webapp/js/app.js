$(function() {
  $(".navbar-expand-toggle").click(function() {
    $(".app-container").toggleClass("expanded");
    return $(".navbar-expand-toggle").toggleClass("fa-rotate-90");
  });
  return $(".navbar-right-expand-toggle").click(function() {
    $(".navbar-right").toggleClass("expanded");
    return $(".navbar-right-expand-toggle").toggleClass("fa-rotate-90");
  });
});

$(function() {
  return $('select').select2();
});

$(function() {
  return $('.toggle-checkbox').bootstrapSwitch({
    size: "small"
  });
});

$(function() {
  return $('.match-height').matchHeight();
});

// $(function() {
//   return $('.datatable').DataTable({
//     "processing" : true,//显示“处理中...”
//     "serverSide" : true,//开启服务器模式。
//     //     {
//     //   "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
//     // }
//
//   });
// });

$(function() {
  return $(".side-menu .nav .dropdown").on('show.bs.collapse', function() {
    return $(".side-menu .nav .dropdown .collapse").collapse('hide');
  });
});

$('#logoutBtn').click(function() {
  $.ajax({
    type: "POST",
    url: "/logout",
    contentType: "application/json", //必须有
    dataType: "json", //表示返回值类型，不必须

    success: function (jsonResult) {
      console.log(jsonResult,"jsonResult");
      console.log(JSON.stringify(jsonResult));
      console.log(jsonResult.loginResult);
      window.location.href="/jsp/login.jsp";
    },
    error: function (jsonResult) {
      console.log("what");
      console.log(jsonResult);
    }
  });
});