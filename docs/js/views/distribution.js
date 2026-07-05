class DistributionCalculator extends Calculator {

    constructor() {
        super("Success Distribution");
    }

    render(container) {

        container.innerHTML = `

            <label>Number of Dice</label>

            <input
                id="diceCount"
                type="number"
                value="10"
                min="1">

        `;
    }

    calculate(results) {

        const dice = number("diceCount");

        const distribution =
            calculateDistribution(dice);

        let html = `
        <table>

            <tr>
                <th>Successes</th>
                <th>Chance</th>
            </tr>
    `;

        distribution.forEach(entry => {

            html += `
            <tr>
                <td>${entry.successes}</td>
                <td>${(entry.chance * 100).toFixed(4)}%</td>
            </tr>
        `;

        });

        html += "</table>";

        results.innerHTML = html;

    }

}