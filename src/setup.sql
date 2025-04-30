-- Game Library Platform Database Schema

-- 1. USERS
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       avatar_url VARCHAR(255),
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2. GAMES
CREATE TABLE games (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       description TEXT,
                       developer VARCHAR(100),
                       release_date DATE,
                       price DECIMAL(10,2) DEFAULT 0.00,
                       cover_url VARCHAR(255)
);

-- 3. SALES (Purchases)
CREATE TABLE sales (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       game_id INT NOT NULL,
                       purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                       price_at_purchase DECIMAL(10,2),
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (game_id) REFERENCES games(id)
);

-- 4. LIBRARIES (User's Owned Games)
CREATE TABLE libraries (
                           user_id INT,
                           game_id INT,
                           added_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (user_id, game_id),
                           FOREIGN KEY (user_id) REFERENCES users(id),
                           FOREIGN KEY (game_id) REFERENCES games(id)
);

-- 5. FRIENDSHIPS
CREATE TABLE friends (
                         user_id INT,
                         friend_id INT,
                         status ENUM('pending', 'accepted', 'blocked') DEFAULT 'pending',
                         requested_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (user_id, friend_id),
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (friend_id) REFERENCES users(id)
);

-- 6. CHAT ROOMS (1-on-1 or Group)
CREATE TABLE chat_rooms (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100),
                            is_group BOOLEAN DEFAULT FALSE,
                            created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 7. CHAT ROOM MEMBERS
CREATE TABLE chat_room_members (
                                   room_id INT,
                                   user_id INT,
                                   joined_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   PRIMARY KEY (room_id, user_id),
                                   FOREIGN KEY (room_id) REFERENCES chat_rooms(id),
                                   FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 8. CHAT MESSAGES
CREATE TABLE chat_messages (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               room_id INT,
                               sender_id INT,
                               message TEXT,
                               sent_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (room_id) REFERENCES chat_rooms(id),
                               FOREIGN KEY (sender_id) REFERENCES users(id)
);

-- 9. GAME REVIEWS
CREATE TABLE game_reviews (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              user_id INT NOT NULL,
                              game_id INT NOT NULL,
                              rating TINYINT CHECK (rating BETWEEN 1 AND 10),
                              review TEXT,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(id),
                              FOREIGN KEY (game_id) REFERENCES games(id),
                              UNIQUE (user_id, game_id)
);
