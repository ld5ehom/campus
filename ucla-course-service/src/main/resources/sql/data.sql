-- (COURSES)
INSERT INTO COURSES (course_id, title, description, professor_id, created_at)
VALUES (100, 'Java', 'Introduction to Java', 100, '2024-01-07 00:00:00'),
       (200, 'Python', 'Introduction to Python', 200, '2024-01-07 00:00:00');

-- (COURSE_SESSIONS)
INSERT INTO COURSE_SESSIONS (session_id, course_id, title)
VALUES (100, 100, 'Java '),
       (200, 100, 'Java Class'),
       (300, 200, 'Encapsulation'),
       (400, 200, 'Inheritance');

-- (COURSE_RATINGS)
INSERT INTO COURSE_RATINGS (rating_id, course_id, user_id, rating, comment, created_at)
VALUES (100, 100, 100, 5, 'Excellent!', '2024-01-09 12:00:00'),
       (200, 100, 101, 4, 'Good', '2024-01-09 12:00:00'),
       (300, 200, 102, 5, 'Perfect', '2024-01-09 12:00:00'),
       (400, 200, 103, 3, 'Nice', '2024-01-09 12:00:00');
