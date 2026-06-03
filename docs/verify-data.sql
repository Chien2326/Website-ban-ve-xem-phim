USE banvexemphim;

-- Verify Roles
SELECT '=== Roles ===' AS info;
SELECT * FROM Roles;

-- Verify Users
SELECT '=== Users ===' AS info;
SELECT user_id, full_name, email, password_hash, role_id FROM Users;

-- Verify Roles join Users
SELECT '=== Users with Roles ===' AS info;
SELECT u.user_id, u.full_name, u.email, u.password_hash, r.role_name
FROM Users u
JOIN Roles r ON u.role_id = r.role_id;
