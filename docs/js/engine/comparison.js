function calculateComparison(diceCount, successCount, comparison) {

    let chance = 0;

    switch (comparison) {

        case "moreEqual":

            for (let i = successCount; i <= diceCount; i++) {
                chance += binomialProbability(diceCount, i);
            }

            break;

        case "lessEqual":

            for (let i = 0; i <= successCount; i++) {
                chance += binomialProbability(diceCount, i);
            }

            break;

        case "equal":

            chance = binomialProbability(diceCount, successCount);

            break;

    }

    return chance;

}