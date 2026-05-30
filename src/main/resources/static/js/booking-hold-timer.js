const BookingHoldTimer = (function () {
    const DEADLINE_KEY = 'bookingHoldDeadline';
    const SESSION_KEY = 'bookingHoldSessionKey';
    const DURATION_MS = 7 * 60 * 1000;

    let intervalId = null;

    function buildSessionKey(showtimeId, showSeatIds) {
        return String(showtimeId) + ':' + String(showSeatIds);
    }

    function readStored() {
        const key = sessionStorage.getItem(SESSION_KEY);
        const raw = sessionStorage.getItem(DEADLINE_KEY);
        const deadline = raw ? parseInt(raw, 10) : NaN;
        if (!key || !Number.isFinite(deadline)) {
            return null;
        }
        return { key: key, deadline: deadline };
    }

    function saveSession(key, deadline) {
        sessionStorage.setItem(SESSION_KEY, key);
        sessionStorage.setItem(DEADLINE_KEY, String(deadline));
    }

    function ensureDeadline(sessionKey, allowStart) {
        const stored = readStored();
        if (stored && stored.key === sessionKey) {
            return stored.deadline;
        }
        if (!allowStart) {
            return stored ? stored.deadline : null;
        }
        const deadline = Date.now() + DURATION_MS;
        saveSession(sessionKey, deadline);
        return deadline;
    }

    function formatRemaining(ms) {
        const totalSec = Math.max(0, Math.floor(ms / 1000));
        const min = Math.floor(totalSec / 60);
        const sec = totalSec % 60;
        return String(min).padStart(2, '0') + ':' + String(sec).padStart(2, '0');
    }

    function init(options) {
        const el = document.getElementById(options.elementId || 'booking-hold-timer');
        if (!el || !options.showtimeId || !options.showSeatIds) {
            return;
        }

        if (intervalId) {
            clearInterval(intervalId);
            intervalId = null;
        }

        const sessionKey = buildSessionKey(options.showtimeId, options.showSeatIds);
        const allowStart = options.allowStart === true;
        let deadline = ensureDeadline(sessionKey, allowStart);

        function onExpired() {
            el.textContent = '00:00';
            el.classList.add('text-red-600');
            el.classList.remove('text-orange-500');
            if (typeof options.onExpired === 'function') {
                options.onExpired();
            }
        }

        function tick() {
            if (!Number.isFinite(deadline)) {
                onExpired();
                return;
            }
            const remaining = deadline - Date.now();
            if (remaining <= 0) {
                onExpired();
                if (intervalId) {
                    clearInterval(intervalId);
                    intervalId = null;
                }
                return;
            }
            el.textContent = formatRemaining(remaining);
            el.classList.remove('text-red-600');
            el.classList.add('text-orange-500');
        }

        tick();
        intervalId = setInterval(tick, 1000);
    }

    function clear() {
        sessionStorage.removeItem(DEADLINE_KEY);
        sessionStorage.removeItem(SESSION_KEY);
        if (intervalId) {
            clearInterval(intervalId);
            intervalId = null;
        }
    }

    return { init: init, clear: clear };
})();
