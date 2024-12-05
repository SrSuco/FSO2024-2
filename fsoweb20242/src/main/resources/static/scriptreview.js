(function () {
  $("#tabelareview").on("click", ".js-delete", function () {
    let clickedButton = $(this);
    $("#btnyes").attr("data-id", clickedButton.attr("data-id"));
    $("#modalreview").modal("show");
  });

  $(document).on("click", "#btnyes", function () {
    let clickedButton = $(this);
    let id = clickedButton.attr("data-id");
    $.ajax({
      url: "/review/delete/" + id,
      method: "GET",
      success: function () {
        window.location.href = "/review";
      },
    });
  });
})();
