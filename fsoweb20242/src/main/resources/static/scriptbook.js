(function () {
  $("#tablebooks").on("click", ".js-delete", function () {
    let clickedButton = $(this);
    $("#btnsim").attr("data-id", clickedButton.attr("data-id"));
    $("#btnsim").attr("data-csrf", clickedButton.attr("data-csrf"));
    $("#modalbook").modal("show");
  });

  $(document).on("click", "#btnsim", function () {
    let clickedButton = $(this);
    let id = clickedButton.attr("data-id");
    let csrfToken = clickedButton.attr("data-csrf");
    $.ajax({
      url: "/book/delete/" + id,
      method: "DELETE",
      headers: {
        "X-CSRF-TOKEN": csrfToken,
      },
      success: function () {
        window.location.href = "/book";
      },
    });
  });
})();
