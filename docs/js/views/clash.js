class ClashCalculator extends Calculator {

    constructor() {
        super("Clash Roll");
    }

    render(container) {

        container.innerHTML = `

            <label>Your Dice (Minimum)</label>
            <input id="playerMin" type="number" min="1" value="5">

            <label>Your Dice (Maximum)</label>
            <input id="playerMax" type="number" min="1" value="5">

            <label>Enemy Dice (Minimum)</label>
            <input id="enemyMin" type="number" min="0" value="3">

            <label>Enemy Dice (Maximum)</label>
            <input id="enemyMax" type="number" min="0" value="8">

            <label>Mode</label>

            <select id="clashMode">

                <option value="offensive">Offensive</option>
                <option value="defensive">Defensive</option>

            </select>

        `;
    }

    calculate(results) {

        const playerMin = number("playerMin");
        const playerMax = number("playerMax");

        const enemyMin = number("enemyMin");
        const enemyMax = number("enemyMax");

        const defensive =
            select("clashMode") === "defensive";

        const answer = calculateClash(
            playerMin,
            playerMax,
            enemyMin,
            enemyMax,
            defensive
        );

        let html = "";

        answer.forEach(player => {

            html += `
            <h3>
                Your Dice: ${player.player}
                (${defensive ? "Defensive" : "Offensive"})
            </h3>

            <table>

                <tr>
                    <th>Enemy Dice</th>
                    <th>Win Chance</th>
                    <th>Crit Chance</th>
                </tr>
        `;

            player.enemies.forEach(enemy => {

                html += `
                <tr>
                    <td>${enemy.enemy}</td>
                    <td>${(enemy.winChance * 100).toFixed(2)}%</td>
                    <td>${(enemy.critChance * 100).toFixed(2)}%</td>
                </tr>
            `;

            });

            html += "</table><br>";

        });

        results.innerHTML = html;

    }

}