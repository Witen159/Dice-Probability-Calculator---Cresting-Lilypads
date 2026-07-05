const title = document.getElementById("calculator-title");
const inputs = document.getElementById("calculator-inputs");
const results = document.getElementById("results");
const calculateButton = document.getElementById("calculate-button");

let currentCalculator = null;

const calculators = new Map([
    ["uncontested", new UncontestedCalculator()],
    ["clash", new ClashCalculator()],
    ["comparison", new ComparisonCalculator()],
    ["percentile", new PercentileCalculator()],
    ["distribution", new DistributionCalculator()],
    ["probability", new ProbabilityCalculator()]
]);

function loadCalculator(name) {

    currentCalculator = calculators.get(name);

    title.textContent = currentCalculator.title;

    currentCalculator.render(inputs);

    results.innerHTML = "Waiting for calculation...";
}

document.querySelectorAll(".nav-button").forEach(button => {

    button.addEventListener("click", () => {

        document.querySelectorAll(".nav-button")
            .forEach(b => b.classList.remove("active"));

        button.classList.add("active");

        loadCalculator(button.dataset.mode);

    });

});

calculateButton.addEventListener("click", () => {

    if (currentCalculator) {
        currentCalculator.calculate(results);
    }

});

loadCalculator("uncontested");