const SUCCESS_CHANCE = 0.3;

function combination(n, r) {

    if (r > n || r < 0)
        return 0;

    if (r === 0 || r === n)
        return 1;

    r = Math.min(r, n - r);

    let result = 1;

    for (let i = 0; i < r; i++) {

        result *= (n - i);
        result /= (i + 1);

    }

    return result;

}

function binomialProbability(dice, successes) {

    return combination(dice, successes)
        * Math.pow(SUCCESS_CHANCE, successes)
        * Math.pow(1 - SUCCESS_CHANCE, dice - successes);

}