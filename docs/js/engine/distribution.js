function calculateDistribution(diceCount) {

    const distribution = [];

    for(let successes = 0;
        successes <= diceCount;
        successes++){

        distribution.push({

            successes,

            chance:
                binomialProbability(
                    diceCount,
                    successes)

        });

    }

    return distribution;

}