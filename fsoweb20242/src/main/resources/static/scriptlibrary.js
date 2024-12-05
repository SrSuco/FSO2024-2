(function () {
  const csrfToken = $("meta[name='_csrf']").attr("content");
  const csrfHeader = $("meta[name='_csrf_header']").attr("content");

  $("#tabelalibraries").on("click", ".js-delete", function () {
    let clickedButton = $(this);
    $("#btnsim").attr("data-id", clickedButton.attr("data-id"));
    $("#btnsim").attr("data-csrf", clickedButton.attr("data-csrf"));
    $("#modallibrary").modal("show");
  });

  $(document).on("click", "#btnsim", function () {
    let clickedButton = $(this);
    let id = clickedButton.attr("data-id");
    let csrfToken = clickedButton.attr("data-csrf");
    $.ajax({
      url: `/library/delete/${id}`,
      method: "DELETE",
      headers: {
        "X-CSRF-TOKEN": csrfToken,
      },
      success: function () {
        window.location.href = "/library";
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
              <a href="/library/edit/${
                library.id
              }" class="btn btn-warning">Edit</a>
              <button class="btn btn-danger js-delete" data-id="${
                library.id
              }" data-bs-toggle="modal" data-bs-target="#modallibrary">Delete</button>
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
