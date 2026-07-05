function calculateUncontested(diceCount) {

    let winChance = 0;
    let critChance = 0;

    for (let successes = 0; successes <= diceCount; successes++) {

        const probability =
            binomialProbability(diceCount, successes);

        if (successes >= 1)
            winChance += probability;

        if (successes >= 2)
            critChance += probability;

    }

    return {
        winChance,
        critChance
    };

}