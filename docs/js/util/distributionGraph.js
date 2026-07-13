let distributionChart = null;

function drawDistributionChart(distribution) {

    console.log("Drawing chart...");

    const labels = distribution.map(d => d.successes);

    const data = distribution.map(d => d.chance * 100);

    const ctx =
        document.getElementById("distributionChart");

    if (distributionChart !== null) {
        distributionChart.destroy();
    }

    distributionChart = new Chart(ctx, {

        type: "bar",

        data: {

            labels: labels,

            datasets: [{

                label: "Chance (%)",

                data: data

            }]

        },

        options: {

            responsive: true,

            plugins: {

                legend: {

                    display: false

                }

            },

            scales: {

                y: {

                    beginAtZero: true,

                    title: {

                        display: true,

                        text: "Chance (%)"

                    }

                },

                x: {

                    title: {

                        display: true,

                        text: "Successes"

                    }

                }

            }

        }

    });

}