-- Insert cart item
INSERT INTO cart_items (user_id, item_id) VALUES (?, ?)
;
-- Get cart item by id
SELECT * FROM cart_items WHERE id = ?
;
-- Get all cart items
SELECT * FROM cart_items
;
-- Get cart item by user id
SELECT * FROM cart_items WHERE user_id = ?
;
-- Remove cart item by id
DELETE FROM cart_items WHERE id = ?
;

-- Insert user
INSERT INTO users (email, password_hash, nickname, avatar_id) VALUES (?, ?, ?, ?)
;
-- Select all users
SELECT * FROM users
;
-- Select user by id
SELECT * FROM users WHERE id = ?
;
-- Select user by email
SELECT * FROM users WHERE email = ?
;
-- Update user
UPDATE users SET email = ?, password_hash = ?, nickname = ?, avatar_id = ? WHERE id = ?
;

-- Insert avatar
INSERT INTO avatars (file_name, content_type,
                     size, original_file_name, uploader_id) VALUES (?, ?, ?, ?, ?) RETURNING id
;
-- Get avatar by id
SELECT * FROM avatars WHERE id = ?
;
-- Update avatar
UPDATE avatars SET file_name = ?, content_type = ?,
                   size = ?, original_file_name = ?, uploader_id = ? WHERE id = ?
;
-- Delete avatar by id
DELETE FROM avatars WHERE id = ?
;

-- Insert session
INSERT INTO sessions (user_id, session_key) VALUES (?, ?)
;
-- Get session by user id
SELECT * FROM sessions WHERE user_id = ?
;
-- Get session by session key
SELECT * FROM sessions WHERE session_key = ?
;
-- Update session by user id
UPDATE sessions SET session_key = ? WHERE user_id = ?
;
-- Delete session by user id
DELETE FROM sessions WHERE user_id = ?
;
-- Delete session by session key
DELETE FROM sessions WHERE session_key = ?
;

-- Insert item
INSERT INTO items (name_ru, name_en, image_name, image_name_det, description_ru, description_en, wiki_link, price_rub) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
;
-- Get item by id
SELECT * FROM items WHERE id = ?
;
-- Get all items
SELECT * FROM items
;