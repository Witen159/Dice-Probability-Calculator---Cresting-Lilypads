class PercentileCalculator extends Calculator {

    constructor() {
        super("Total Percentile");
    }

    render(container) {

        container.innerHTML = `

            <label>Number of Dice</label>

            <input
                id="diceCount"
                type="number"
                value="5"
                min="1">

            <label>Target Total</label>

            <input
                id="target"
                type="number"
                value="25">

        `;
    }

    calculate(results) {

        const dice = number("diceCount");
        const target = number("target");

        const percentile =
            calculatePercentile(dice, target);

        results.innerHTML = `
        <h3>${target} or less</h3>

        <br>

        ${(percentile * 100).toFixed(2)}%
    `;

    }

}