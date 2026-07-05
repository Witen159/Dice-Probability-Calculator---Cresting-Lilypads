const buttons = document.querySelectorAll(".mode-button");

buttons.forEach(button => {

    button.addEventListener("click", () => {

        buttons.forEach(b => b.classList.remove("active"));

        button.classList.add("active");

    });

});