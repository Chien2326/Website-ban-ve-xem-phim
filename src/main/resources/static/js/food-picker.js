const FoodPicker = (function () {
    const selected = new Map();

    function init(seatTotal, showtimeId, showSeatIds) {
        const root = document.getElementById('food-picker');
        if (!root) {
            return;
        }

        root.querySelectorAll('.product-qty-btn').forEach(function (btn) {
            btn.addEventListener('click', function () {
                const productId = btn.dataset.productId;
                const price = parseFloat(btn.dataset.price || '0');
                const action = btn.dataset.action;
                const qtyEl = root.querySelector('[data-qty-for="' + productId + '"]');
                let qty = parseInt(qtyEl.textContent || '0', 10);

                if (action === 'inc') {
                    qty += 1;
                } else if (action === 'dec' && qty > 0) {
                    qty -= 1;
                }

                qtyEl.textContent = String(qty);
                if (qty > 0) {
                    selected.set(productId, { price: price, qty: qty });
                } else {
                    selected.delete(productId);
                }
                updateTotals(seatTotal, showtimeId, showSeatIds);
            });
        });

        updateTotals(seatTotal, showtimeId, showSeatIds);
    }

    function updateTotals(seatTotal, showtimeId, showSeatIds) {
        let productTotal = 0;
        selected.forEach(function (item) {
            productTotal += item.price * item.qty;
        });

        const total = seatTotal + productTotal;
        const totalEl = document.getElementById('food-total');
        const productTotalEl = document.getElementById('food-product-total');
        const continueBtn = document.getElementById('food-continue');

        if (productTotalEl) {
            productTotalEl.textContent = formatVnd(productTotal);
        }
        if (totalEl) {
            totalEl.textContent = formatVnd(total);
        }
        if (continueBtn && showtimeId && showSeatIds) {
            const params = new URLSearchParams();
            params.set('showtimeId', showtimeId);
            params.set('showSeatIds', showSeatIds);
            selected.forEach(function (item, productId) {
                params.append('productIds', productId);
                params.append('quantities', item.qty);
            });
            continueBtn.dataset.continueUrl = '/thanh-toan?' + params.toString();
        }
    }

    function formatVnd(amount) {
        return new Intl.NumberFormat('vi-VN').format(Math.round(amount)) + ' \u0111';
    }

    return { init: init };
})();
