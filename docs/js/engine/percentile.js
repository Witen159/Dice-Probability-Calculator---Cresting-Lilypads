function calculatePercentile(diceCount, target) {

    const maxSum = diceCount * 10;

    let dp = new Array(maxSum + 1).fill(0);
    dp[0] = 1;

    for (let dice = 1; dice <= diceCount; dice++) {

        const next = new Array(maxSum + 1).fill(0);

        for (let sum = 0; sum <= maxSum; sum++) {

            if (dp[sum] === 0)
                continue;

            for (let roll = 1; roll <= 10; roll++) {

                next[sum + roll] += dp[sum] / 10;

            }

        }

        dp = next;

    }

    let percentile = 0;

    for (let sum = diceCount;
         sum <= Math.min(target, maxSum);
         sum++) {

        percentile += dp[sum];

    }

    return percentile;

}