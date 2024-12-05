document.addEventListener("DOMContentLoaded", function () {
  const deleteButtons = document.querySelectorAll(".js-delete");
  const modal = new bootstrap.Modal(document.getElementById("modallibrary"));
  const btnYes = document.getElementById("btnsim");
  const userSelect = document.getElementById("userSelect");
  const addBookBtn = document.getElementById("addBookBtn");

  deleteButtons.forEach((button) => {
    button.addEventListener("click", function () {
      const libraryId = this.getAttribute("data-id");
      btnYes.setAttribute("data-id", libraryId);
      modal.show();
    });
  });

  btnYes.addEventListener("click", function () {
    const libraryId = this.getAttribute("data-id");
    fetch(`/library/delete/${libraryId}`, {
      method: "DELETE",
    }).then(() => {
      modal.hide();
      location.reload();
    });
  });

  function updateLibraryTable(userId) {
    fetch(`/library/user/${userId}`)
      .then((response) => response.json())
      .then((data) => {
        // Update the table with the user's libraries
        const tableBody = document.querySelector("#tabelalibraries tbody");
        tableBody.innerHTML = "";
        data.forEach((library) => {
          const row = document.createElement("tr");
          row.innerHTML = `
            <td>${library.user.name}</td>
            <td>${library.book.title}</td>
            <td>${library.status}</td>
            <td>${new Date(library.creationDate).toLocaleString()}</td>
            <td>
              <a href="/library/alterar/${
                library.id
              }" class="btn btn-warning">Edit</a>
              <button class="btn btn-danger js-delete" data-id="${
                library.id
              }">Delete</button>
            </td>
          `;
          tableBody.appendChild(row);
        });
      });
  }

  userSelect.addEventListener("change", function () {
    const userId = this.value;
    updateLibraryTable(userId);
  });

  if (addBookBtn) {
    addBookBtn.addEventListener("click", function () {
      const userId = userSelect.value;
      if (userId) {
        window.location.href = `/library/addBook/${userId}`;
      }
    });
  }

  if (userSelect.value) {
    updateLibraryTable(userSelect.value);
  }
});
