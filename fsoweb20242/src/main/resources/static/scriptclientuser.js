(function () {
  $("#tableusers").on("click", ".js-delete", function () {
    let clickedButton = $(this);
    $("#btnyes").attr("data-id", clickedButton.attr("data-id"));
    $("#btnyes").attr("data-csrf", clickedButton.attr("data-csrf"));
  });

  $(document).on("click", "#btnyes", function () {
    let clickedButton = $(this);
    let id = clickedButton.attr("data-id");
    let csrfToken = clickedButton.attr("data-csrf");
    $.ajax({
      url: "/users/delete/" + id,
      method: "DELETE",
      headers: {
        "X-CSRF-TOKEN": csrfToken,
      },
      success: function () {
        window.location.href = "/users";
      },
    });
  });
})();
