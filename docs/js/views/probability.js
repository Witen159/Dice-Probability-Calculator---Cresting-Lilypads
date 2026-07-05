class ProbabilityCalculator extends Calculator {

    constructor() {
        super("Probability Calculator");
    }

    render(container) {

        container.innerHTML = `

            <label>Expression</label>

            <input
                id="expression"
                type="text"
                placeholder="Example: 5d3-2d2<5">

        `;
    }

    calculate(results) {

        const expression = text("expression");

        results.innerHTML = `
        Expression entered: <code>${expression}</code>
        <br><br>
        You'll have to wait until I can rewrite everything for JS...
    `;

    }

}