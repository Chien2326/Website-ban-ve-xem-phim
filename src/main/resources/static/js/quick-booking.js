const QuickBooking = (function () {
    const STEP_ORDER = ['movie', 'cinema', 'date', 'showtime'];
    const DEFAULT_LABELS = {
        movie: 'Chọn Phim',
        cinema: 'Chọn Rạp',
        date: 'Chọn Ngày',
        showtime: 'Chọn Suất',
    };

    let movies = [];
    let showtimes = [];
    let state = {
        movieId: null,
        cinemaId: null,
        dateKey: null,
        showtimeId: null,
    };

    let root;
    let submitBtn;
    let openPanel = null;

    function init(movieData, showtimeData) {
        console.log('=== QuickBooking.init called ===');
        console.log('movieData:', movieData);
        console.log('showtimeData:', showtimeData);
        
        movies = movieData || [];
        showtimes = showtimeData || [];
        root = document.getElementById('quick-booking');
        submitBtn = document.getElementById('qb-submit');
        
        console.log('root element:', root);
        console.log('submitBtn element:', submitBtn);
        
        if (!root || !submitBtn) {
            console.error('Missing root or submitBtn elements!');
            return;
        }

        bindTriggers();
        document.addEventListener('click', onDocumentClick);
        resetAll();
        renderMovies();
    }

    function bindTriggers() {
        STEP_ORDER.forEach(function (step) {
            const trigger = root.querySelector('[data-trigger="' + step + '"]');
            if (trigger) {
                trigger.addEventListener('click', function (event) {
                    event.stopPropagation();
                    onTriggerClick(step);
                });
            }
        });
    }

    function onDocumentClick() {
        closeAllPanels();
    }

    function onTriggerClick(step) {
        const stepEl = getStepEl(step);
        if (stepEl.classList.contains('locked')) {
            return;
        }
        const panel = stepEl.querySelector('.qb-panel');
        const isOpen = !panel.classList.contains('hidden');
        closeAllPanels();
        if (!isOpen) {
            panel.classList.remove('hidden');
            openPanel = panel;
        }
    }

    function closeAllPanels() {
        root.querySelectorAll('.qb-panel').forEach(function (panel) {
            panel.classList.add('hidden');
        });
        openPanel = null;
    }

    function getStepEl(step) {
        return root.querySelector('[data-step="' + step + '"]');
    }

    function setStepLocked(step, locked) {
        const stepEl = getStepEl(step);
        if (locked) {
            stepEl.classList.add('locked');
        } else {
            stepEl.classList.remove('locked');
        }
    }

    function setStepLabel(step, text) {
        const label = getStepEl(step).querySelector('.qb-label-text');
        if (label) {
            label.textContent = text;
        }
    }

    function resetFrom(stepIndex) {
        for (let i = stepIndex; i < STEP_ORDER.length; i++) {
            const step = STEP_ORDER[i];
            if (step === 'movie') {
                state.movieId = null;
            } else if (step === 'cinema') {
                state.cinemaId = null;
            } else if (step === 'date') {
                state.dateKey = null;
            } else if (step === 'showtime') {
                state.showtimeId = null;
            }
            setStepLabel(step, DEFAULT_LABELS[step]);
            setStepLocked(step, i > 0);
        }
        updateSubmit();
    }

    function resetAll() {
        resetFrom(0);
        setStepLocked('movie', false);
    }

    function updateSubmit() {
        if (state.showtimeId) {
            submitBtn.href = '/dat-ve?showtimeId=' + state.showtimeId;
            submitBtn.classList.remove('opacity-50', 'pointer-events-none', 'cursor-not-allowed');
            submitBtn.removeAttribute('aria-disabled');
        } else {
            submitBtn.href = '#';
            submitBtn.classList.add('opacity-50', 'pointer-events-none', 'cursor-not-allowed');
            submitBtn.setAttribute('aria-disabled', 'true');
        }
    }

    function renderMovies() {
        console.log('=== renderMovies() called ===');
        const list = root.querySelector('[data-list="movie"]');
        console.log('movie list element:', list);
        list.innerHTML = '';

        const movieIds = new Set(showtimes.map(function (st) { return st.movieId; }));
        console.log('Movie IDs from showtimes:', Array.from(movieIds));
        
        const available = movies.filter(function (m) { return movieIds.has(m.movieId); });
        console.log('Available movies to render:', available);

        if (available.length === 0) {
            list.innerHTML = '<li class="px-4 py-2 text-gray-400 italic">Chưa có phim</li>';
            return;
        }

        available.forEach(function (movie) {
            const li = document.createElement('li');
            li.className = 'px-4 py-2 hover:bg-blue-50 hover:text-blue-600 cursor-pointer';
            li.textContent = movie.title;
            li.addEventListener('click', function (event) {
                event.stopPropagation();
                selectMovie(movie);
            });
            list.appendChild(li);
        });
        console.log('Rendered', available.length, 'movies into list');
    }

    function selectMovie(movie) {
        state.movieId = movie.movieId;
        setStepLabel('movie', movie.title);
        closeAllPanels();
        resetFrom(1);
        setStepLocked('cinema', false);
        renderCinemas();
    }

    function renderCinemas() {
        const list = root.querySelector('[data-list="cinema"]');
        list.innerHTML = '';

        const filtered = showtimes.filter(function (st) { return st.movieId === state.movieId; });
        const cinemaMap = new Map();
        filtered.forEach(function (st) {
            if (!cinemaMap.has(st.cinemaId)) {
                cinemaMap.set(st.cinemaId, { cinemaId: st.cinemaId, cinemaName: st.cinemaName });
            }
        });

        const cinemas = Array.from(cinemaMap.values()).sort(function (a, b) {
            return a.cinemaName.localeCompare(b.cinemaName, 'vi');
        });

        if (cinemas.length === 0) {
            list.innerHTML = '<li class="px-4 py-2 text-gray-400 italic">Chưa có rạp</li>';
            return;
        }

        cinemas.forEach(function (cinema) {
            const li = document.createElement('li');
            li.className = 'px-4 py-2 hover:bg-blue-50 hover:text-blue-600 cursor-pointer';
            li.innerHTML = '<span class="block truncate">' + escapeHtml(cinema.cinemaName) + '</span>';
            li.addEventListener('click', function (event) {
                event.stopPropagation();
                selectCinema(cinema);
            });
            list.appendChild(li);
        });
    }

    function selectCinema(cinema) {
        state.cinemaId = cinema.cinemaId;
        setStepLabel('cinema', cinema.cinemaName);
        closeAllPanels();
        resetFrom(2);
        setStepLocked('date', false);
        renderDates();
    }

    function renderDates() {
        const list = root.querySelector('[data-list="date"]');
        list.innerHTML = '';

        const filtered = showtimes.filter(function (st) {
            return st.movieId === state.movieId && st.cinemaId === state.cinemaId;
        });

        const dateKeys = [];
        const dateSet = new Set();
        filtered.forEach(function (st) {
            if (!dateSet.has(st.dateKey)) {
                dateSet.add(st.dateKey);
                dateKeys.push(st.dateKey);
            }
        });
        dateKeys.sort();

        if (dateKeys.length === 0) {
            list.innerHTML = '<li class="px-4 py-2 text-gray-400 italic">Chưa có lịch chiếu</li>';
            return;
        }

        dateKeys.forEach(function (dateKey) {
            const li = document.createElement('li');
            li.className = 'px-4 py-2.5 hover:bg-gray-50 cursor-pointer';
            li.textContent = formatDateLabel(dateKey);
            li.addEventListener('click', function (event) {
                event.stopPropagation();
                selectDate(dateKey);
            });
            list.appendChild(li);
        });
    }

    function selectDate(dateKey) {
        state.dateKey = dateKey;
        setStepLabel('date', formatDateLabel(dateKey));
        closeAllPanels();
        resetFrom(3);
        setStepLocked('showtime', false);
        renderShowtimes();
    }

    function renderShowtimes() {
        const list = root.querySelector('[data-list="showtime"]');
        list.innerHTML = '';

        const filtered = showtimes.filter(function (st) {
            return st.movieId === state.movieId
                && st.cinemaId === state.cinemaId
                && st.dateKey === state.dateKey;
        }).sort(function (a, b) {
            return a.startTime.localeCompare(b.startTime);
        });

        if (filtered.length === 0) {
            list.innerHTML = '<li class="px-4 py-2 text-gray-400 italic">Chưa có suất chiếu</li>';
            return;
        }

        filtered.forEach(function (st) {
            const li = document.createElement('li');
            li.className = 'px-4 py-2.5 hover:bg-gray-50 cursor-pointer';
            const format = st.formatType ? ' · ' + st.formatType : '';
            li.innerHTML = '<span class="font-semibold">' + escapeHtml(st.timeLabel) + '</span>'
                + '<span class="text-gray-600">' + escapeHtml(format) + '</span>';
            li.addEventListener('click', function (event) {
                event.stopPropagation();
                selectShowtime(st);
            });
            list.appendChild(li);
        });
    }

    function selectShowtime(st) {
        state.showtimeId = st.showtimeId;
        setStepLabel('showtime', st.timeLabel + (st.formatType ? ' · ' + st.formatType : ''));
        closeAllPanels();
        updateSubmit();
    }

    function formatDateLabel(dateKey) {
        const parts = dateKey.split('-');
        if (parts.length !== 3) {
            return dateKey;
        }
        return parts[2] + '/' + parts[1] + '/' + parts[0];
    }

    function escapeHtml(text) {
        if (text == null) {
            return '';
        }
        return String(text)
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;');
    }

    return { init: init };
})();
