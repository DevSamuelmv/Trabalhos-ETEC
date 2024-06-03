document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('salesForm');
    const resultDiv = document.getElementById('result');
    let submissionCount = 0;

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submissionCount++;

        const carPriceInput = document.getElementById('carPrice');
        const downPaymentInput = document.getElementById('downPayment');
        const loanTermInput = document.getElementById('loanTerm');
        const paymentTypeInput = document.getElementById('paymentType');

        const carPrice = parseFloat(carPriceInput.value);
        const paymentType = paymentTypeInput.value;

        let downPayment = 0;
        let loanTerm = 0;

        if (paymentType === 'financed') {
            downPayment = parseFloat(downPaymentInput.value);
            loanTerm = parseFloat(loanTermInput.value);
            const loanAmount = carPrice - downPayment;
            const monthlyInterestRate = 0.0159; // 1.59% ao mês
            const numberOfPayments = loanTerm;
            const monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
            resultDiv.innerHTML = `<p>O pagamento será ${loanTerm} vezes de R$ ${monthlyPayment.toFixed(2)}.</p>`;
        } else if (paymentType === 'cash') {
            const discount = 0.05; // 5% de desconto à vista
            const finalPrice = carPrice - (carPrice * discount);
            resultDiv.innerHTML = `<p>O preço final com desconto de 5% será R$ ${finalPrice.toFixed(2)}.</p>`;
        }
    });

    const paymentTypeInput = document.getElementById('paymentType');
    if (paymentTypeInput) {
        const downPaymentContainer = document.getElementById('downPaymentContainer');
        const loanTermContainer = document.getElementById('loanTermContainer');

        paymentTypeInput.addEventListener('change', function() {
            if (paymentTypeInput.value === 'cash') {
                if (downPaymentContainer) downPaymentContainer.style.display = 'none';
                if (loanTermContainer) loanTermContainer.style.display = 'none';
            } else {
                if (downPaymentContainer) downPaymentContainer.style.display = 'block';
                if (loanTermContainer) loanTermContainer.style.display = 'block';
            }
        });
    }
});
