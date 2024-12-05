(function () {
  $("#tabelalibraries").on("click", ".js-delete", function () {
    let clickedButton = $(this);
    $("#btnsim").attr("data-id", clickedButton.attr("data-id"));
    $("#modallibrary").modal("show");
  });

  $(document).on("click", "#btnsim", function () {
    let clickedButton = $(this);
    let id = clickedButton.attr("data-id");
    $.ajax({
      url: "/library/delete/" + id,
      method: "DELETE",
      success: function () {
        $("#modallibrary").modal("hide");
        location.reload();
      },
    });
  });

  function updateLibraryTable(userId) {
    $.getJSON(`/library/user/${userId}`, function (data) {
      const tableBody = $("#tabelalibraries tbody");
      tableBody.empty();
      data.forEach((library) => {
        const statusText =
          library.status === 1
            ? "Whishing to read"
            : library.status === 2
            ? "Currently Reading"
            : "Finished";
        const row = `
          <tr>
            <td>${library.book.title}</td>
            <td>${statusText}</td>
            <td>${new Date(library.creationDate).toLocaleString()}</td>
            <td>
              <a href="/library/edit/${library.userId}/${
          library.book.id
        }" class="btn btn-warning">Edit</a>
              <button class="btn btn-danger js-delete" data-id="${
                library.id
              }">Delete</button>
            </td>
          </tr>
        `;
        tableBody.append(row);
      });
    });
  }

  $("#userSelect").change(function () {
    const userId = $(this).val();
    updateLibraryTable(userId);
  });

  $("#addBookBtn").click(function () {
    const userId = $(this).attr("data-user-id");
    if (userId) {
      window.location.href = `/library/new?userId=${userId}`;
    }
  });

  if ($("#userSelect").val()) {
    updateLibraryTable($("#userSelect").val());
  }
})();
