class ComparisonCalculator extends Calculator {

    constructor() {
        super("Success Comparison");
    }

    render(container) {

        container.innerHTML = `

            <label>Number of Dice</label>

            <input
                id="diceCount"
                type="number"
                value="10"
                min="1">

            <label>Number of Successes</label>

            <input
                id="successes"
                type="number"
                value="3"
                min="0">

            <label>Comparison</label>

            <select id="comparison">

                <option value="moreEqual">
                    More or Equal
                </option>

                <option value="lessEqual">
                    Less or Equal
                </option>

                <option value="equal">
                    Equal
                </option>

            </select>

        `;
    }

    calculate(results) {

        const dice = number("diceCount");
        const successes = number("successes");

        const comparison = select("comparison");

        const chance = calculateComparison(
            dice,
            successes,
            comparison
        );

        results.innerHTML = `
        <b>Chance</b>

        <br><br>

        ${(chance * 100).toFixed(2)}%
    `;

    }

}