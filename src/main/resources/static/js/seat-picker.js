const SeatPicker = (function () {
    const selected = new Map();

    function init(showtimeId) {
        const root = document.getElementById('seat-picker');
        if (!root) {
            return;
        }

        root.querySelectorAll('.seat-btn').forEach(function (btn) {
            btn.addEventListener('click', function () {
                if (btn.disabled || btn.classList.contains('seat-sold')) {
                    return;
                }
                toggleSeat(btn);
                updateSummary(showtimeId);
            });
        });

        updateSummary(showtimeId);
    }

    function toggleSeat(btn) {
        const id = btn.dataset.showSeatId;
        const price = parseFloat(btn.dataset.price || '0');

        if (selected.has(id)) {
            selected.delete(id);
            btn.classList.remove('seat-selected', 'bg-orange-500', 'text-white', 'border-orange-500');
            btn.classList.add('bg-white', 'text-gray-600');
        } else {
            selected.set(id, price);
            btn.classList.add('seat-selected', 'bg-orange-500', 'text-white', 'border-orange-500');
            btn.classList.remove('bg-white', 'text-gray-600');
        }
    }

    function updateSummary(showtimeId) {
        const totalEl = document.getElementById('booking-total');
        const countEl = document.getElementById('booking-seat-count');
        const continueBtn = document.getElementById('booking-continue');

        let total = 0;
        selected.forEach(function (price) {
            total += price;
        });

        if (totalEl) {
            totalEl.textContent = formatVnd(total);
        }
        if (countEl) {
            countEl.textContent = selected.size + ' ghế';
        }
        if (continueBtn) {
            if (selected.size > 0) {
                const ids = Array.from(selected.keys()).join(',');
                continueBtn.href = '/thuc-an?showtimeId=' + showtimeId + '&showSeatIds=' + ids;
                continueBtn.classList.remove('opacity-50', 'pointer-events-none', 'cursor-not-allowed');
                continueBtn.removeAttribute('aria-disabled');
            } else {
                continueBtn.href = '#';
                continueBtn.classList.add('opacity-50', 'pointer-events-none', 'cursor-not-allowed');
                continueBtn.setAttribute('aria-disabled', 'true');
            }
        }
    }

    function formatVnd(amount) {
        return new Intl.NumberFormat('vi-VN').format(Math.round(amount)) + ' \u0111';
    }

    return { init: init };
})();
