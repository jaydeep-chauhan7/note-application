select * from notes;
select * from users;

INSERT INTO users (create_time, email, last_update_time, password) VALUES
  (NOW(6), 'user1@example.com', NOW(6), 'pass123A!'),
  (NOW(6), 'user2@example.com', NOW(6), 'Qwerty456$'),
  (NOW(6), 'user3@example.com', NOW(6), 'Secure789@'),
  (NOW(6), 'user4@example.com', NOW(6), 'MyPwd321#'),
  (NOW(6), 'user5@example.com', NOW(6), 'Alpha999%');

INSERT INTO notes (content, create_time, last_update_time, title, user_id) VALUES
  ('This is a note about Java.', NOW(6), NOW(6), 'Java Notes', 1),
  ('A quick reminder to study Spring Boot.', NOW(6), NOW(6), 'Spring Boot', 1),

  ('Thoughts on architecture patterns.', NOW(6), NOW(6), 'Design Notes', 2),
  ('My grocery list: milk, bread, eggs.', NOW(6), NOW(6), 'Grocery List', 2),

  ('Meeting notes from team sync.', NOW(6), NOW(6), 'Team Sync', 3),
  ('Vacation ideas for summer.', NOW(6), NOW(6), 'Travel Plans', 3),

  ('Security best practices.', NOW(6), NOW(6), 'Security Notes', 4),
  ('Books to read: Clean Code, Effective Java.', NOW(6), NOW(6), 'Book List', 4),

  ('Ideas for a side project.', NOW(6), NOW(6), 'Project Ideas', 5),
  ('Workout plan: Monday - Cardio, Tuesday - Weights.', NOW(6), NOW(6), 'Fitness Plan', 5);