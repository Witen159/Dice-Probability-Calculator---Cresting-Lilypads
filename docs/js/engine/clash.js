function calculateSingleClash(playerDice,
                              enemyDice,
                              defensive) {

    let winChance = 0;
    let critChance = 0;

    for (let playerSuccesses = 0;
         playerSuccesses <= playerDice;
         playerSuccesses++) {

        const playerProb =
            binomialProbability(
                playerDice,
                playerSuccesses);

        for (let enemySuccesses = 0;
             enemySuccesses <= enemyDice;
             enemySuccesses++) {

            const enemyProb =
                binomialProbability(
                    enemyDice,
                    enemySuccesses);

            const probability =
                playerProb * enemyProb;

            let win;

            if (defensive)
                win =
                    playerSuccesses >= enemySuccesses + 1;
            else
                win =
                    playerSuccesses >= enemySuccesses;

            win = win && playerSuccesses >= 1;

            const crit =
                playerSuccesses >= enemySuccesses + 2;

            if (win)
                winChance += probability;

            if (crit)
                critChance += probability;

        }
    }

    return {

        winChance,

        critChance

    };

}

function calculateClash(playerMin,
                        playerMax,
                        enemyMin,
                        enemyMax,
                        defensive){

    const table = [];

    for(let player = playerMin;
        player <= playerMax;
        player++){

        const row = {

            player,

            enemies:[]

        };

        for(let enemy = enemyMin;
            enemy <= enemyMax;
            enemy++){

            const answer =
                calculateSingleClash(
                    player,
                    enemy,
                    defensive);

            row.enemies.push({

                enemy,

                winChance:
                answer.winChance,

                critChance:
                answer.critChance

            });

        }

        table.push(row);

    }

    return table;

}