let currentClashData = [];

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

        currentClashData = calculateClash(
            playerMin,
            playerMax,
            enemyMin,
            enemyMax,
            defensive
        );

        let html = `
        <div class="dice-tabs-container">

            <div class="dice-tabs-header">
                Your Dice
            </div>

            <div class="dice-tabs">
        `;
        currentClashData.forEach((player, index) => {

            html += `
            <button
                class="dice-tab ${index === 0 ? "active" : ""}"
                data-index="${index}">

                ${player.player}

            </button>
        `;

        });

        html += ` 
            </div>
        </div>
        <div id="clashTable"></div>
        `;

        results.innerHTML = html;

        document.querySelectorAll(".dice-tab").forEach(button => {

            button.addEventListener("click", () => {

                document.querySelectorAll(".dice-tab")
                    .forEach(b => b.classList.remove("active"));

                button.classList.add("active");

                showClashTable(parseInt(button.dataset.index));

            });

        });

        showClashTable(0);

    }

}

function showClashTable(index) {

    const player = currentClashData[index];

    let html = `
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

    html += `</table>`;

    document.getElementById("clashTable").innerHTML = html;

}