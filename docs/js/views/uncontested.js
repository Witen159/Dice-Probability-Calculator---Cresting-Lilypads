class UncontestedCalculator extends Calculator {

    constructor() {
        super("Uncontested Roll");
    }

    render(container) {

        container.innerHTML = `

            <label>Number of Dice</label>

            <input
                id="diceCount"
                type="number"
                min="1"
                value="5">

        `;
    }

    calculate(results) {

        const dice = number("diceCount");

        const answer = calculateUncontested(dice);

        results.innerHTML = `
        <b>Win Chance:</b>
        ${(answer.winChance * 100).toFixed(2)}%

        <br><br>

        <b>Crit Win Chance:</b>
        ${(answer.critChance * 100).toFixed(2)}%
    `;

    }

}