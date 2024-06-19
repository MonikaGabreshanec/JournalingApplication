document.addEventListener("DOMContentLoaded", function() {
    var dueDates = document.querySelectorAll('.due-date span');

    dueDates.forEach(function(element) {
        var dueDate = new Date(element.textContent.trim());
        var currentDate = new Date();

        var timeDiff = dueDate.getTime() - currentDate.getTime();
        var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

        if (diffDays === 1) {
            var todoItem = element.closest('.todo-item');
            var checkbox = todoItem.querySelector('input[type="checkbox"]');

            if (checkbox && !checkbox.checked) {
                showPopup(todoItem.querySelector('span').textContent.trim());
            }
        }
    });
});

function showPopup(taskTitle) {
    var popup = document.createElement("div");
    popup.classList.add("popup");

    var card = document.createElement("div");
    card.classList.add("card");

    var cardBody = document.createElement("div");
    cardBody.classList.add("card-body");

    var popupTitle = document.createElement("h5");
    popupTitle.classList.add("card-title");
    popupTitle.textContent = "Task '" + taskTitle + "'";

    var popupMessage = document.createElement("p");
    popupMessage.classList.add("card-text");
    popupMessage.textContent = "is due tomorrow!";

    cardBody.appendChild(popupTitle);
    cardBody.appendChild(popupMessage);

    var closeButton = document.createElement("button");
    closeButton.setAttribute("type", "button");
    closeButton.classList.add("btn-close");
    closeButton.setAttribute("aria-label", "Close");
    closeButton.innerHTML = "&times;";
    closeButton.addEventListener("click", function() {
        popup.classList.remove("show");
    });

    card.appendChild(cardBody);
    card.appendChild(closeButton);

    popup.appendChild(card);

    document.body.appendChild(popup);

    setTimeout(function() {
        popup.classList.add("show");
    }, 500);
}
